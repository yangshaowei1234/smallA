package com.yangshaowei.www.dao;

import com.yangshaowei.www.pojo.Product;
import com.yangshaowei.www.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 13
 */ //这个代码风格得改
public class ProductDao {

    //获取系统所有商品
    public void getAllProducts() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
                conn = JDBCUtil.getConnection();
                String sql = "select * from product";
                ps = conn.prepareStatement(sql);
                rs= ps.executeQuery();

                while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("time"),
                        rs.getInt("merchant_id"),
                        rs.getInt("sales")
                );
                productList.add(product);
            }
            for (Product product : productList) {
                System.out.println("商品 ID: " + product.getId());
                System.out.println("商品 名称: " + product.getName());
                System.out.println("商品 类别: " + product.getCategory());
                System.out.println("商品 价格: " + product.getPrice());
                System.out.println("商品 库存: " + product.getStock());
                System.out.println("商品 时间: " + product.getTime());
                System.out.println("商品 销量: " + product.getSales());
                System.out.println("------------------------------");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,rs );
        }

    }
    //按商品名查询
    public void getProductsByName(String productName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM product WHERE name = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, productName);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                //将从数据库中获取的数据放在上面的product对象中
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getInt("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setTime(rs.getString("time"));
                product.setMerchantId(rs.getInt("merchant_id"));
                productList.add(product);
            }
            printProduct(productList);
            //一开始想用break跳出，发现编译报错
            //break;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        //好像用不上返回值，但先不管了后面再说（idea说没用上）
        // return productList;
    }
    //按发布时间排序
    public List<Product> orderByTime(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String order = "SELECT * FROM product ORDER BY time ";
            ps = conn.prepareStatement(order);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getInt("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setTime(rs.getString("time"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return productList;

    }
    //按销量排序
    public List<Product> orderByStock(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String order = "SELECT * FROM product ORDER BY stock desc ";
            ps = conn.prepareStatement(order);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getInt("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setTime(rs.getString("time"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return productList;

    }
    //浏览商品2
    public List<Product> browseProduct(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String order = "SELECT * FROM product  ";
            ps = conn.prepareStatement(order);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getInt("category"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                product.setTime(rs.getString("time"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return productList;

    }

    //打印商品（单独写个函数防止重复代码捏）
    public static void printProduct(List<Product> productList){
        if (productList == null || productList.isEmpty()) {
            System.out.println("未查询到任何商品信息。");
        } else {
            for (Product product : productList) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Product Name: " + product.getName());
                System.out.println("Product Category: " + product.getCategory());
                System.out.println("Product Price: " + product.getPrice());
                System.out.println("Product Stock: " + product.getStock());
                System.out.println("Product Time: " + product.getTime());
                System.out.println("------------------------------");
            }
        }
   }
    //添加商品（商家独有）
    public void addProduct(String name, int category, double pricre, int stock,int merchantId){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            String sql = "insert into product (name, category, price,stock,merchant_id) values (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,category);
            ps.setDouble(3,pricre);
            ps.setInt(4,stock);
            ps.setInt(5,merchantId);
            int row = ps.executeUpdate();
            if (row > 0){
                System.out.println("上架成功");
                conn.commit();
            }else {
                System.out.println("上架失败");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接和释放资源
            JDBCUtil.close(conn, ps);
        }

    }
    //查看商品销量
    public void checkProductSales(int userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from product where merchant_id = ?";
            /*ps.setInt(1,merchantId);
            ps = conn.prepareStatement(sql);*///写反了哈哈


            ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);

            rs= ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getString(2),
                        rs.getInt(8));

                productList.add(product);
            }
            for (Product product : productList) {
                //System.out.println("商品 ID: " + product.getId());
                System.out.println("商品 名称: " + product.getName());
               // System.out.println("商品 类别: " + product.getCategory());
               // System.out.println("商品 价格: " + product.getPrice());
               // System.out.println("商品 库存: " + product.getStock());
                //System.out.println("商品 时间: " + product.getTime());
                System.out.println("商品 销量: " + product.getSales());
                System.out.println("------------------------------");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,rs );
        }
    }
    //管理我的商品,先打印出来我有什么商品吧
    public void getMyProduct(int merchantId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from product where merchant_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,merchantId);

            rs= ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("time"),
                        rs.getInt("merchant_id"),
                        rs.getInt("sale")
                );
                productList.add(product);
            }
            for (Product product : productList) {
                 System.out.println("商品 ID: " + product.getId());
                 System.out.println("商品 名称: " + product.getName());
                 System.out.println("商品 类别: " + product.getCategory());
                 System.out.println("商品 价格: " + product.getPrice());
                 System.out.println("商品 库存: " + product.getStock());
                 System.out.println("商品 时间: " + product.getTime());
                 System.out.println("商品 销量: " + product.getSales());
                 System.out.println("------------------------------");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            JDBCUtil.close(conn,ps,rs );
        }



    }
    //修改我的商品信息
    public void alterMyProduct(String newName, double newPrice, int newStock ,int productId){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            //设置sql
            String sql = "UPDATE product SEt name = ?,price = ?, stock = ? where id = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,newName);

            ps.setDouble(2,newPrice);

            ps.setInt(3,newStock);

            ps.setInt(4,productId);
            int row= ps.executeUpdate();

            if (row > 0) {
                System.out.println("橱窗商品信息更新成功！");
                // 手动提交事务
                conn.commit();
            } else {
                System.out.println("用户信息更新失败！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps);
        }
    }
    //修改商品库存
    public void alterStock(int newStock,int produceId){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            //设置sql
            String sql = "UPDATE product SEt stock = ? where id = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1,newStock);

            ps.setInt(2,produceId);
            int row= ps.executeUpdate();

            if (row > 0) {
                System.out.println("橱窗商品库存更新成功！");
                // 手动提交事务
                conn.commit();
            } else {
                System.out.println("商品库春更新失败！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps);
        }
    }

}



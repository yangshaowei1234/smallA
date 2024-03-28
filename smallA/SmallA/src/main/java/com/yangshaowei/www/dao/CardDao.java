package com.yangshaowei.www.dao;

import com.yangshaowei.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardDao {
    //添加商品到购物车
    //这里后期增加显示商品名
    public void addProductToCard(int productId, int quantity,int userId){
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            String sql = "insert into cart (product_id,quantity,user_id) values (?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,productId);
            ps.setInt(2,quantity);
            ps.setInt(3,userId);
            int row = ps.executeUpdate();
            if (row > 0){
                System.out.println("购物车添加成功");
                conn.commit();
            }else {
                System.out.println("添加失败");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接和释放资源
            JDBCUtil.close(conn, ps);
        }
    }

    //管理购物车(删除，或更改商品数量)我考虑用重载方法完成
    //删除购物车中的商品
    public void manageMyCart(int productId,int userId){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            //设置sql
            String sql = "delete from cart where product_id = ? and user_id = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1,productId);

            ps.setInt(2,userId);

            int row= ps.executeUpdate();

            if (row > 0) {
                System.out.println("购物车商品添加成功！");
                // 手动提交事务
                conn.commit();
            } else {
                System.out.println("失败！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps);
        }
    }
    //增减购物车中商品的数量
    public void manageMyCart(int cartId,int newQuantity, int userId){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            //设置sql
            String sql = "update cart set quantity = ? where id = ? and user_id =?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1,newQuantity);

            ps.setInt(2,cartId);

            ps.setInt(3,userId);

            int row= ps.executeUpdate();

            if (row > 0) {
                System.out.println("购物车商品数量修改成功！");
                // 手动提交事务
                conn.commit();
            } else {
                System.out.println("修改失败！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps);
        }
    }
}


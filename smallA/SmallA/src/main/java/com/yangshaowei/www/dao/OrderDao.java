package com.yangshaowei.www.dao;

import com.yangshaowei.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 13
 * 这是我写过最难的数据库操作
 * 1 查询用户购物车的信息
 * 2，将购物车信息插入订单
 * 3 再将与product表连接获取其中商品信息
 * 4 限定在某个与用户id下进行操作
 */
public class OrderDao {
        //生成全部订单
        public void createOrder(int userId){
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                //获取连接
                conn = JDBCUtil.getConnection();
                //设置手动提交
                conn.setAutoCommit(false);
                //设置sql。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。（上面有解释）
                String sql = "insert into `order` (user_id, product_id, quantity, total_price, order_date, status) " +
                        "SELECT cart.user_id, cart.product_id, cart.quantity, (cart.quantity * p.price) as total_price, current_timestamp as order_date, 'pending' as status " +
                        "FROM cart " +
                        "JOIN db1.product p ON p.id = cart.product_id " +
                        "WHERE user_id = ?";

                ps = conn.prepareStatement(sql);

                ps.setInt(1,userId);
                //执行插入操作捏
                int row= ps.executeUpdate();

                if (row > 0) {
                    System.out.println("生成订单成功！");
                    // 手动提交事务
                    conn.commit();
                } else {
                    System.out.println("下单失败！");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.close(conn, ps);
            }
        }
        //刚开始犯了错误，product.price这里识别不了，因为后面贪图方便表用了别名 所以全部地方都得是别民
  /*  String sql = "insert into order (user_id, product_id, quantity, total_price, order_date, status) " +
            "SELECT cart.user_id,cart.product_id,cart.quantity,(cart.quantity * product.price) as total_price,current_timestamp as order_date, 'pending' as status " +
            "from cart " +
            "join db1.product p on p.id = cart.product_id " +
            "where user_id = ?";*/
        //结算订单（还要添加一个sql语句更新订单中的商品的销量）（未完成）
        public void tallyOrder(int userId){

            Connection conn = null;
            PreparedStatement ps1 = null;
            PreparedStatement ps2 = null;
            ResultSet rs = null;
            try {
                //获取连接
                conn = JDBCUtil.getConnection();
                //设置手动提交
                conn.setAutoCommit(false);
                //设置sql(idea 提示order是保留关键字要加``为什么之前没有提示？)
                String sql1 = "update `order` set status = 'shipped' where user_id = ?";
                String sql2 = "select total_price from `order` where user_id = ?";

                ps1 = conn.prepareStatement(sql1);

                ps2 = conn.prepareStatement(sql2);

                ps1.setInt(1,userId);

                ps2.setInt(1,userId);

                rs = ps2.executeQuery();

                int row= ps1.executeUpdate();

                if (rs.next()) {
                    System.out.println("您已支付:"+rs.getString("total_price"));
                    System.out.println("订单结算成功！");
                    if (row > 0){
                        System.out.println("该订单清除！");
                    }
                    // 手动提交事务
                    conn.commit();
                } else {
                    System.out.println("结算失败！");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.close(conn, ps1);
                JDBCUtil.close(conn, ps2);
            }
        }

}


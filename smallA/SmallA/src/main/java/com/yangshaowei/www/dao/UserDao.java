package com.yangshaowei.www.dao;

import com.yangshaowei.www.pojo.User;
import com.yangshaowei.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 13 用户数据操作
 */
public class UserDao {
    //修改个人信息（这里暂时只能修改名字。。。。）
    public static void updateUser(String newName, String password, int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            //设置sql
            String sql = "UPDATE user SEt username = ? WHERE password = ? AND  id = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,newName);

            ps.setString(2, password);

            ps.setInt(3,id);

            int row= ps.executeUpdate();

            if (row > 0) {
                System.out.println("用户信息更新成功！");
                // 手动提交事务
                conn.commit();
            } else {
                System.out.println("用户信息更新失败，请检查密码和用户ID！");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps);
        }
    }



    //查询是否为本人不然不可以修改数据
    public static boolean validateUser(int id, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = JDBCUtil.getConnection();
            //查询是否有该数据的sql
            String sql = "SELECT * FROM user WHERE id = ? AND password = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, password);
            rs = ps.executeQuery();
            //如果能在数据库中有对应则返回ture 反则false
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }finally {
            // 关闭连接和释放资源
            JDBCUtil.close(conn, ps, rs);

        }

    }
//用户登录
    public  User loginUser(String userName, String passWord){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                // 获取数据库连接
                conn = JDBCUtil.getConnection();
                //查询是否有该数据的sql
                String sql = "select * from user where username = ?and password = ?";
                //执行sql
                ps = conn.prepareStatement(sql);

                ps.setString(1, userName);

                ps.setString(2, passWord);
                rs = ps.executeQuery();
                //判断是否创建成功并返回该用户类型
                if (rs.next()) {
                    User user = new User(rs.getInt(1),
                                            userName,
                                            passWord,
                                            rs.getInt(4),
                                            rs.getInt(5) );
                    return user;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                // 关闭连接和释放资源
                JDBCUtil.close(conn, ps, rs);
            }

            return null;





    }
        //用户注册
    public  User register(String usename, String passwoid, int role){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            User user = new User();
            try{
                conn = JDBCUtil.getConnection();
                //设置手动提交
                conn.setAutoCommit(false);
                String sql = "insert into user (username,password,role) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1,usename);
                ps.setString(2,passwoid);
                ps.setInt(3,role);
                int row = ps.executeUpdate();
                if (row > 0){
                    System.out.println("注册成功");
                    user.setId(rs.getInt(1));
                    user.setUsername(usename);
                    user.setPassword(passwoid);
                    user.setRole(role);
                    user.setPoint(rs.getInt(5));

                    conn.commit();
                }else {
                    System.out.println("注册失败");

                }
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                // 关闭连接和释放资源
                JDBCUtil.close(conn, ps);
            }
            return user;
        }
     //充值积分
 public void rechargePoint(int point,int userId){
     Connection conn = null;
     PreparedStatement ps = null;
     try {
         //获取连接
         conn = JDBCUtil.getConnection();
         //设置手动提交
         conn.setAutoCommit(false);
         //设置sql
         String sql = "UPDATE user SEt points = ? where id = ?";

         ps = conn.prepareStatement(sql);

         ps.setInt(1,point);

         ps.setInt(2,userId);
         int row= ps.executeUpdate();

         if (row > 0) {
             System.out.println("充值成功！");
             // 手动提交事务
             conn.commit();
         } else {
             System.out.println("充值失败！");
         }

     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         JDBCUtil.close(conn, ps);
     }
 }
}


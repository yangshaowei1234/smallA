package com.yangshaowei.www.dao;


import com.yangshaowei.www.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao {
    //添加收货地址
    public void addAdress(String address, byte isDefault, int userId) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            String sql = "insert into address (address,is_default,user_id) values (?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, address);

            ps.setByte(2, isDefault);

            ps.setInt(3, userId);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("收货地址添加成功");
                conn.commit();
            } else {
                System.out.println("收货地址添加失败失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接和释放资源
            JDBCUtil.close(conn, ps);
        }

    }

    //修改收货地址
    public void alterAddress(String newAddress, byte newDefault,String oldAdrsss, int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取连接
            conn = JDBCUtil.getConnection();
            //设置手动提交
            conn.setAutoCommit(false);
            //设置sql（哎语法错误）
            String sql = "UPDATE address SET address  = ?,is_default = ? WHERE user_id = ? AND address = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, newAddress);

            ps.setByte(2, newDefault);

            ps.setInt(3, userId);

            ps.setString(4,oldAdrsss);


            int row = ps.executeUpdate();

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

    //获取默认地址
    public String getDefaultAddress(int userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String address = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM address WHERE id = ? and is_default = 1 ";
            ps = conn.prepareStatement(query);
            ps.setInt(1,userId);
            //获取sql查询的结果集（一般都设有默认地址就不判断了）
            rs = ps.executeQuery();

            address = rs.getString(3);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return address;
    }

}

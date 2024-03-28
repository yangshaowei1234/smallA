package com.yangshaowei.www.dao;



import com.yangshaowei.www.util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author 13
 */
public class MerchantDao{
    //修改商户信息（这是直接粘贴userDao中基本差不多的注册方法，改改sql语句便可）

    public void updateMerchant(int userId, String newName, String newAddress, String newBusiness){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "update merchant set  name = ?, address = ?, business = ?where user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setString(2, newAddress);
            ps.setString(3,newBusiness);
            ps.setInt(4, userId);
            //
            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("修改成功");
                conn.commit();
            } else {
                System.out.println("修改失败");
            }
        }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                // 关闭连接和释放资源
                JDBCUtil.close(conn, ps);
            }


    }
    //获取用户所属的商店id（这里还是实现一人一店，但数据库的设计可以实现一人多店，但我实在没有精力。但我想应该可以用一个数组（个人持有的店数量有限这个符合实际）或集合（无线）去装商店id）
    public static int getMerchantId(int userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int merchantId = 0;

        try {
            conn = JDBCUtil.getConnection();
            String order = "SELECT id FROM merchant where user_id = ?";
            ps = conn.prepareStatement(order);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()){
                //这里一人一店了直接赋值merchantId了
                merchantId = rs.getInt(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return merchantId;
    }

}


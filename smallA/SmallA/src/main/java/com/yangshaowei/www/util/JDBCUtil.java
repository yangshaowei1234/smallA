package com.yangshaowei.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JDBCUtil {
    //获取本地连接
    //final修饰变量不能修改哟！
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/db1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    //私有化构造方法，不准创建该实类
    private JDBCUtil() {
    }

    //还是获取连接，但为静态，加载jdbcUtil类时候就已经调用了该方法，直接获取了与mysql数据库的连接，且无需创建该实例，而且创建不了（上面唯一的构造方法私有化了）
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

   /* //获取执行sql语句的对象的方法
    public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }*/


    //释放资源（一般用）
    public static void close(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //释放资源（重载）（—查询用—）
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

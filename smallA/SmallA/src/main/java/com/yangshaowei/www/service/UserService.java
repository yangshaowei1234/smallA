package com.yangshaowei.www.service;

import com.yangshaowei.www.dao.UserDao;
import com.yangshaowei.www.pojo.User;
import com.yangshaowei.www.util.JDBCUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * @author 13
 */
public class UserService {
    //修改个人信息
    public void updateUser (String newName, String password, int id) {
        //先进行判断是否为本人，不同用户密码可以相同，但id不会相同
        if (UserDao.validateUser(id, password)) {
            UserDao.updateUser(newName, password, id);
        } else {
            System.out.println("密码错误，不准修改");
        }

    }
    //用户注册
    public User registerUser(String usename, String passwoid, int role){
       return new UserDao().register(usename,passwoid,role);
    }

    //用户登录
    public User loginUSer(String userName, String passWord){

        return new UserDao().loginUser(userName,passWord);
    }
    //充值积分
    public void rechargePoint(int point, int userId){
        new UserDao().rechargePoint(point,userId);
    }

}

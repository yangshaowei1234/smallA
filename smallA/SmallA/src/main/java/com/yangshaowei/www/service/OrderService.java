package com.yangshaowei.www.service;

import com.yangshaowei.www.dao.OrderDao;
import com.yangshaowei.www.dao.UserDao;

/**
 * @author 13
 * 订单操作业务逻辑
 * 用户交互不方便而且没有很好完成业务逻辑，如结算的订单其所在的商品销量信息没有更新
 */
public class OrderService {
    //一键生成订单（因为不用输入什么东西啊哈哈哈）
    public void createOrder(int userId){
        new OrderDao().createOrder(userId);
    }
    //结算订单(也算是有点业务逻辑，结算得付密码)
    public void tallyOrder(int userId,String password){
        if (UserDao.validateUser(userId,password)){
            new OrderDao().tallyOrder(userId);
        }else {
            System.out.println("密码错误");
        }
    }

}

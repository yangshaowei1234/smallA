package com.yangshaowei.www.service;

import com.yangshaowei.www.dao.CardDao;

/**
 * @author 13
 * 购物车业务逻辑处理
 * 用户交互不方便
 */
public class CardService {
    //添加商品到购物车
    public void addProductToCard(int productId, int quantity,int userId){
        new CardDao().addProductToCard(productId,quantity,userId);
    }
    //删除购物车中的商品
    public void manageMyCart(int productId,int userId){
        new CardDao().manageMyCart(productId,userId);
    }
    //修改商品数量
    public void manageMyCart(int cartId,int newQuantity,int userId){
        new CardDao().manageMyCart(cartId,newQuantity,userId);
    }
    //
}

package com.yangshaowei.www.service;

import com.yangshaowei.www.dao.MerchantDao;
import com.yangshaowei.www.dao.ProductDao;

/**
 * @author 13
 * 商家业务逻辑处理了
 */
public class MerchantService {
    //修改商店信息
    public void updata(int userId, String newName, String newAddress, String newBusiness){
        new MerchantDao().updateMerchant(userId,newName,newAddress,newBusiness);
    }
    //橱窗添加商品
    public void addProduct(String name, int category, double price, int stock,int userId){
        int merchantId = MerchantDao.getMerchantId(userId);
        new ProductDao().addProduct(name,category,price,stock,merchantId);
    }
    //查看小黄车
    public void checkProductSales(int userId){
        //获取用户的商店id
        int merchantId = MerchantDao.getMerchantId(userId);
        //查看该店的商品销量
        new ProductDao().checkProductSales(merchantId);
    }
    //修改橱窗
    public void alterMyProduct(String newName, double newPrice, int newStock ,int productId,int merchantId){
        new ProductDao().getMyProduct(merchantId);
        new ProductDao().alterMyProduct(newName,newPrice,newStock,productId);
    }
    // 管理商品库存
    public void alterStock(int newStock,int produceId,int merchantId){
        new ProductDao().getMyProduct(merchantId);
        new ProductDao().alterStock(newStock,produceId);
    }



}

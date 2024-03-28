package com.yangshaowei.www.service;

import com.yangshaowei.www.dao.AddressDao;

/**
 * @author 13
 * 收货地址业务逻辑处理
 * 用户交互还不方便
 */
public class AddressService {
    //修改收货地址
    public void alterAddress(String newAddress, byte newDefault,String oldAdrsss, int userId){
        new AddressDao().alterAddress(newAddress,newDefault,oldAdrsss,userId);
    }
    //添加收货地址
    public void addAdress(String address,byte isDefault,int userId){
        new AddressDao().addAdress(address,isDefault,userId);
    }
    //获取默认地址
    public String getDefaultAddress(int userId){
       return new AddressDao().getDefaultAddress(userId);
    }
}

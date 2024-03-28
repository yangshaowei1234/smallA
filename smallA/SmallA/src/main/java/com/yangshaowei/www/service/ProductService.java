package com.yangshaowei.www.service;


import com.yangshaowei.www.dao.ProductDao;
import com.yangshaowei.www.pojo.Product;

import java.util.List;

public class ProductService {
    //按商品名查询
    public void searchProductByName(String name){
        //调用商品dao里的查询方法
        new ProductDao().getProductsByName(name);
    }

    //按发布时间排序商品
    public void orderProduceByTime(){
        //或许我直接可以在dao包里直接打印不用在这里输出了
        List<Product> productList = new ProductDao().orderByTime();
        ProductDao.printProduct(productList);
    }
    //按销量排序（aesc）降序
    public void orderProductByStock(){
        List<Product> productList = new ProductDao().orderByStock();
        ProductDao.printProduct(productList);
    }
    //管理，修改商品信息（后面转移到merchant包里了，这样或许更独立点）

}




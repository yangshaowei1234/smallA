package com.yangshaowei.www.util;

import com.yangshaowei.www.pojo.Product;

import java.util.List;

/**
 * @author 13
 * 这里我尝试用上接口这个语法，但不知道从何用起，故打算先把大概项目完成后在来考虑。 */
public interface ProductDaoInterface {
    List<Product> getAllProducts();
    List<Product> getProductsByName(String productName);
    List<Product> orderByTime();
    List<Product> orderByStock();
    List<Product> browseProduct();
}

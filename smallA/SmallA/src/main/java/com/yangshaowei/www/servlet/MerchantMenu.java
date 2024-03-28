package com.yangshaowei.www.servlet;

import com.yangshaowei.www.pojo.User;
import com.yangshaowei.www.service.MerchantService;

import java.util.Scanner;

/**
 * @author 13
 */
public class MerchantMenu {
    public void merchantMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("商家控制台");
            System.out.println("1. 修改商户个人信息");
            System.out.println("2. 添加商品");
            System.out.println("3. 修改商品信息");
            System.out.println("4. 查看商品销量");
            System.out.println("5. 上下架商品");
            System.out.println("6. 商品库存管理");
            System.out.println("7. 退出");
            System.out.print("请选择操作：");

            choice = scanner.nextInt();
//            scanner.next();//也是以为要什么清除缓存
            switch (choice) {
                case 1:
                    // 修改商店信息
                    System.out.println("想修改的店名：");
                    String newName = scanner.next();
                    System.out.println("想修改的地址：");
                    String newAddress = scanner.next();
                    System.out.println("想修改的主营业务：");
                    String newBusiness = scanner.next();
                    new MerchantService().updata(user.getId(),newName,newAddress,newBusiness);
                    break;
                case 2:
                    // 添加商品到店
                    System.out.println("添加的商品名称");
                    String name = scanner.next();
                    System.out.println("商品类别");
                    System.out.println("1默认 2服装 3首饰 4数码 5文具");
                    int category = scanner.nextInt();
                    System.out.println("商品价格");
                    double price = scanner.nextDouble();
                    System.out.println("商品在货量");
                    int stock = scanner.nextInt();
                    new MerchantService().addProduct( name, category, price, stock,user.getId());
                    break;
                case 3:
                    // 修改商品信息
                    System.out.println("要修改的商品id：");
                    int id = scanner.nextInt();
                    System.out.println("要修改商品的昵称（标题党不是）");
                    String changedName = scanner.next();
                    System.out.println("要修改商品的价格（亲民点,哥）");
                    double changedPrice = scanner.nextDouble();
                    System.out.println("要修改商品的库存量");
                    int changedStock = scanner.nextInt();
                    new MerchantService().alterMyProduct(changedName,changedPrice,changedStock,id, user.getId());
                    break;
                case 4:
                    // 查看商品销量
                    new MerchantService().checkProductSales(user.getId());
                    break;
                case 5:
                    // 编写上下架商品的逻辑()


                    break;
                case 6:
                    // 编写商品库存管理的逻辑
                    System.out.println("要修改的商品id");
                    int stockId = scanner.nextInt();
                    System.out.println("要修改的商品库存");
                    int modifiedStock = scanner.nextInt();
                    new MerchantService().alterStock(modifiedStock,stockId, user.getId());
                    break;
                case 7:
                    System.out.println("感谢您的使用，再见！");
                    break;
                default:
                    System.out.println("无效的选择，请重新选择");
                    break;
            }
        }
    }

}

package com.yangshaowei.www.servlet;

import com.yangshaowei.www.dao.ProductDao;
import com.yangshaowei.www.pojo.User;
import com.yangshaowei.www.service.AddressService;
import com.yangshaowei.www.service.CardService;
import com.yangshaowei.www.service.OrderService;
import com.yangshaowei.www.service.UserService;

import java.util.Scanner;

/**
 * @author 13
 *
 */
public class UserMenu {
    public void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("##顾客表##");
            System.out.println("1. 修改个人信息");
            System.out.println("2. 修改个人收货地址");
            System.out.println("3. 充值积分");
            System.out.println("4. 将商品添加到购物车");
            System.out.println("5. 管理购物车");
            System.out.println("6. 将购物车中所有商品生成订单");
            System.out.println("7. 结算订单");
            System.out.print("请选择操作：");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // 修改个人信息
                    System.out.println("新的昵称：");
                    String newName = scanner.next();
                    System.out.println("需要密码验证,请输入你账户密码：");
                    String password = scanner.next();
                    new UserService().updateUser(newName,password, user.getId());
                    break;
                case 2:
                    // 修改个人收货地址
                    System.out.println("1.添加个人收货地址");
                    System.out.println("2.修改个人收货地址");
                    int option = scanner.nextInt();
                    switch (option){
                        case 1:
                            System.out.println("填写要添加的收货地址");
                            String newAdderss = scanner.next();
                            System.out.println("是否要设为默认地址");
                            System.out.println("要设请填1 无须请填0");
                            byte isDefault = scanner.nextByte();
                            new AddressService().addAdress(newAdderss,isDefault, user.getId());
                            break;

                        case 2:
                            System.out.println("要修改的旧地址");
                            String oldAddress = scanner.next();
                            System.out.println("新地址");
                            String newAddress = scanner.next();
                            System.out.println("是否设为默认");
                            System.out.println("是 1  否 0（填数字）");
                            byte newDefault = scanner.nextByte();
                            new AddressService().alterAddress(newAddress,newDefault,oldAddress, user.getId());
                            break;
                    }


                    break;
                case 3:
                    // 充值积分
                    System.out.println("请输入要充值的积分（一人民币一积分）：");
                    int point = scanner.nextInt();
                    new UserService().rechargePoint(point, user.getId());
                    break;
                case 4:
                    // 编写将商品添加到购物车的逻辑
                    // 显示所有商品
                    new ProductDao().getAllProducts();
                    System.out.println("请输入想要买的商品id：");
                    int productId = scanner.nextInt();
                    System.out.println("买多少？");
                    int quantity = scanner.nextInt();
                    new CardService().addProductToCard(productId,quantity,user.getId());
                    break;
                case 5:
                    // 管理购物车
                    System.out.println("1.删除购物车");
                    System.out.println("2.修改下单商品数量");
                    int choose = scanner.nextInt();
                    switch (choose){
                        case 1:
                            System.out.println("要删除有哪个商品的购物车：");
                            int produceId = scanner.nextInt();
                            new CardService().manageMyCart(produceId, user.getId());
                            break;
                        case 2:
                            System.out.println("要修改的购物车id：");
                            int cartId = scanner.nextInt();
                            System.out.println("购买的数量");
                            int number = scanner.nextInt();
                            new CardService().manageMyCart(cartId,number, user.getId());
                            break;
                    }
                    break;
                case 6:
                    // 一键生成订单
                    new OrderService().createOrder(user.getId());

                    break;
                case 7:
                    // 结算订单
                    System.out.println("请输入密码：");
                    String paymentCode = scanner.next();
                    new OrderService().tallyOrder(user.getId(), paymentCode);
                    break;
                default:
                    System.out.println("无效的选择，请重新选择");
                    break;
            }
        }

        System.out.println("感谢您的使用，再见！");
    }
}

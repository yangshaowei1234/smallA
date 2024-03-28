package com.yangshaowei.www.servlet;

import com.yangshaowei.www.dao.ProductDao;
import com.yangshaowei.www.pojo.User;
import com.yangshaowei.www.service.ProductService;
import com.yangshaowei.www.service.UserService;

import java.util.Scanner;

/**
 * @author 13
 * 总菜单
 */
public class Menu {
    public static void menu(){
        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        while (choice != 6) {
            System.out.println("_____欢迎人类命运积分商城系统________");
            System.out.println("________________________________");
            System.out.println("1. 浏览商品");
            System.out.println("2. 搜索商品");
            System.out.println("3. 排序商品");
            System.out.println("4. 登录");
            System.out.println("5. 注册新用户");
            System.out.println("6. 退出");
            System.out.println("________________________________");
            choice = scanner.nextInt();
            //调试过程的尝试，留着警醒
            //清除缓冲区的换行符
            //scanner.nextLine();

            switch (choice) {
                case 1:
                    new ProductDao().getAllProducts();
                    break;
                case 2:
                    System.out.println("请输入想查询的商品名");
                    String productQueryName = scanner.next();
                    new ProductService().searchProductByName(productQueryName);
                    break;
                case 3:
                    System.out.println("选择排序方法");
                    System.out.println("1.按销量排序");
                    System.out.println("2.按发布时间排序");
                    System.out.println("亲选择");
                    int orderType = scanner.nextInt();
                    if (orderType == 1){
                        new ProductService().orderProductByStock();
                    } else if (orderType==2) {
                        new ProductService().orderProduceByTime();
                    }
                    break;
                case 4:
                    //调试时最终决定把可尝试次数放在这里，而且可直接在这里修改可尝试次数，之前都写在dao包里无法调整也无法错误后重新输入用户名和密码
                    int time = 3;
                    boolean isLogin = false;
                    while (time > 0 && !isLogin ){
                        System.out.println("请输入用户名：");
                        String username = scanner.next();
                        System.out.println("请输入密码：");
                        String password = scanner.next();
                        //返回用户登录的类型
                        User user1 = new UserService().loginUSer(username, password);

                        if (user1 != null) {

                            if (user1.getRole() == 0) {
                                new UserMenu().userMenu(user1);
                            } else if (user1.getRole() == 1) {
                                new MerchantMenu().merchantMenu(user1);
                            }
                            isLogin = true;
                        } else {
                                time--;
                                System.out.println("登录失败，请重新尝试或注册新用户");
                                System.out.println("用户名或密码错误，您还有 " + time + " 次尝试机会");
                            }

                    }
                    if (isLogin == false) {
                    System.out.println("登录失败，给你机会你不中用啊？！老弟");
                }
                    break;
                case 5:
                    System.out.println("请输入用户名：");
                    String newUsername = scanner.next();
                    System.out.println("请输入密码：");
                    String newPassword = scanner.next();
                    System.out.println("请选择注册类型，客户：0 商家：1");
                    int newRole = scanner.nextInt();
                    User user2 =new UserService().registerUser(newUsername, newPassword, newRole);
                    //注册成功自动登录相应的端
                    if (newRole == 0){
                        new UserMenu().userMenu(user2);
                    } else if(newRole == 1){
                        new MerchantMenu().merchantMenu(user2);
                    } else {
                        System.out.println("注册失败，请重新尝试");
                    }
                    break;
                case 6:
                    System.out.println("退出程序");
                    System.out.println("再见~");
                    break;
                default:
                    System.out.println("无效选项，请重新输入");
            }
        }
    }
    }

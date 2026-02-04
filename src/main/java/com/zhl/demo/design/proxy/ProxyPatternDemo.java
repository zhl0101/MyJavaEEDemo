package com.zhl.demo.design.proxy;

/**
 * 代理模式演示类
 */
public class ProxyPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== 代理模式演示 ===");
        
        // 虚拟代理示例
        System.out.println("\n--- 虚拟代理示例 ---");
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        
        // 图片将在第一次调用display时加载
        System.out.println("第一次显示图片:");
        image1.display();
        
        // 图片已经加载过，不会再从磁盘加载
        System.out.println("\n第二次显示图片:");
        image1.display();
        
        image2.display();
        
        // 保护代理示例
        System.out.println("\n--- 保护代理示例 ---");
        UserServiceImpl userService = new UserServiceImpl();
        
        // 管理员用户
        UserService adminProxy = new ProtectedUserService(userService, "admin");
        adminProxy.addUser("张三");
        adminProxy.grantPermission("张三", "read");
        adminProxy.deleteUser("张三");
        
        System.out.println();
        
        // 普通用户
        UserService userProxy = new ProtectedUserService(userService, "user");
        userProxy.addUser("李四");
        userProxy.grantPermission("李四", "write");
        userProxy.deleteUser("李四");
        
        // 智能引用代理示例
        System.out.println("\n--- 智能引用代理示例 ---");
        Image countingImage1 = new CountingProxy("photo3.jpg");
        Image countingImage2 = new CountingProxy("photo4.jpg");
        
        countingImage1.display();
        countingImage1.display();
        countingImage2.display();
        
        System.out.println("总显示次数: " + CountingProxy.getCount());
    }
}
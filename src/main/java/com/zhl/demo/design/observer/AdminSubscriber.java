package com.zhl.demo.design.observer;

/**
 * 具体观察者实现 - 管理员订阅者
 */
public class AdminSubscriber implements Observer {
    private String adminName;
    
    public AdminSubscriber(String adminName) {
        this.adminName = adminName;
    }
    
    @Override
    public void update(String message) {
        System.out.println("管理员 " + adminName + " 收到通知: " + message);
        // 管理员可能会执行一些特殊操作
        if (message.contains("紧急")) {
            System.out.println("管理员 " + adminName + " 正在处理紧急事件...");
        }
    }
}
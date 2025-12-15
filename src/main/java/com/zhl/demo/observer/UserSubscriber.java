package com.zhl.demo.observer;

/**
 * 具体观察者实现 - 用户订阅者
 */
public class UserSubscriber implements Observer {
    private String name;
    
    public UserSubscriber(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String message) {
        System.out.println(name + " 收到新消息: " + message);
    }
}
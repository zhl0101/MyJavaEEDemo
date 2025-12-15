package com.zhl.demo.proxy;

/**
 * 真实主题 - 用户服务实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String username) {
        System.out.println("添加用户: " + username);
    }
    
    @Override
    public void deleteUser(String username) {
        System.out.println("删除用户: " + username);
    }
    
    @Override
    public void grantPermission(String username, String permission) {
        System.out.println("为用户 " + username + " 授予权限: " + permission);
    }
}
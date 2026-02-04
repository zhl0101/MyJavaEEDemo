package com.zhl.demo.design.proxy;

/**
 * 保护代理 - 用户服务代理类
 */
public class ProtectedUserService implements UserService {
    private UserServiceImpl userService;
    private String role;
    
    public ProtectedUserService(UserServiceImpl userService, String role) {
        this.userService = userService;
        this.role = role;
    }
    
    @Override
    public void addUser(String username) {
        if ("admin".equals(role)) {
            userService.addUser(username);
        } else {
            System.out.println("权限不足，无法添加用户");
        }
    }
    
    @Override
    public void deleteUser(String username) {
        if ("admin".equals(role)) {
            userService.deleteUser(username);
        } else {
            System.out.println("权限不足，无法删除用户");
        }
    }
    
    @Override
    public void grantPermission(String username, String permission) {
        if ("admin".equals(role)) {
            userService.grantPermission(username, permission);
        } else {
            System.out.println("权限不足，无法授予权限");
        }
    }
}
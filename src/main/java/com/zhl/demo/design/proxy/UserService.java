package com.zhl.demo.design.proxy;

/**
 * 保护代理示例 - 用户服务接口
 */
public interface UserService {
    void addUser(String username);
    void deleteUser(String username);
    void grantPermission(String username, String permission);
}
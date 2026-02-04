package com.zhl.demo.design.singleton;

/**
 * 数据库连接池模拟示例
 * 使用枚举实现单例模式
 */
public enum DatabaseConnectionPool {
    INSTANCE;
    
    private int maxConnections = 10;
    private int currentConnections = 0;
    
    // 模拟获取数据库连接
    public Object getConnection() {
        if (currentConnections < maxConnections) {
            currentConnections++;
            System.out.println("获取数据库连接，当前连接数: " + currentConnections);
            return new Object(); // 实际中会返回真正的数据库连接对象
        } else {
            System.out.println("连接池已满，无法获取新的连接");
            return null;
        }
    }
    
    // 模拟释放数据库连接
    public void releaseConnection(Object connection) {
        if (currentConnections > 0) {
            currentConnections--;
            System.out.println("释放数据库连接，当前连接数: " + currentConnections);
        }
    }
    
    public int getCurrentConnections() {
        return currentConnections;
    }
    
    public int getMaxConnections() {
        return maxConnections;
    }
    
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }
}
package com.zhl.demo.design.singleton;

/**
 * 单例模式测试与演示类
 */
public class SingletonDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 单例模式演示 ===");
        
        // 测试Logger单例
        System.out.println("\n--- Logger单例测试 ---");
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        System.out.println("logger1 == logger2: " + (logger1 == logger2)); // 应该为true
        
        logger1.log("这是第一条日志");
        logger2.log("这是第二条日志");
        System.out.println("日志内容:\n" + logger1.getLogData());
        
        // 测试ConfigManager单例
        System.out.println("\n--- ConfigManager单例测试 ---");
        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();
        System.out.println("config1 == config2: " + (config1 == config2)); // 应该为true
        
        config1.setAppName("测试应用");
        config1.setVersion("2.0.0");
        config1.setDebugMode(true);
        
        System.out.println("配置信息: " + config1.toString());
        System.out.println("通过另一个引用获取配置信息: " + config2.toString());
        
        // 测试DatabaseConnectionPool单例
        System.out.println("\n--- DatabaseConnectionPool单例测试 ---");
        Object conn1 = DatabaseConnectionPool.INSTANCE.getConnection();
        Object conn2 = DatabaseConnectionPool.INSTANCE.getConnection();
        System.out.println("连接池实例: " + DatabaseConnectionPool.INSTANCE);
        System.out.println("当前连接数: " + DatabaseConnectionPool.INSTANCE.getCurrentConnections());
        
        DatabaseConnectionPool.INSTANCE.releaseConnection(conn1);
        System.out.println("释放连接后，当前连接数: " + DatabaseConnectionPool.INSTANCE.getCurrentConnections());
    }
}
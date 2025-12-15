package com.zhl.demo.singleton;

/**
 * 日志记录器单例示例
 * 使用双重检查锁定实现线程安全的单例模式
 */
public class Logger {
    private static volatile Logger instance;
    private StringBuilder logData;
    
    // 私有构造函数
    private Logger() {
        logData = new StringBuilder();
    }
    
    // 获取单例实例
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }
    
    // 记录日志
    public void log(String message) {
        logData.append("[").append(System.currentTimeMillis()).append("] ")
               .append(message).append("\n");
        System.out.println("LOG: " + message);
    }
    
    // 获取所有日志数据
    public String getLogData() {
        return logData.toString();
    }
}
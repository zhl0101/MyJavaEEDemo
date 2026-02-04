package com.zhl.demo.design.singleton;

/**
 * 配置管理器单例示例
 * 使用静态内部类实现单例模式
 */
public class ConfigManager {
    private String appName;
    private String version;
    private boolean debugMode;
    
    // 私有构造函数
    private ConfigManager() {
        // 初始化默认配置
        this.appName = "MyApplication";
        this.version = "1.0.0";
        this.debugMode = false;
    }
    
    // 静态内部类
    private static class ConfigManagerHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }
    
    // 获取单例实例
    public static ConfigManager getInstance() {
        return ConfigManagerHolder.INSTANCE;
    }
    
    // Getter和Setter方法
    public String getAppName() {
        return appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }
    
    public boolean isDebugMode() {
        return debugMode;
    }
    
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
    
    @Override
    public String toString() {
        return "ConfigManager{" +
                "appName='" + appName + '\'' +
                ", version='" + version + '\'' +
                ", debugMode=" + debugMode +
                '}';
    }
}
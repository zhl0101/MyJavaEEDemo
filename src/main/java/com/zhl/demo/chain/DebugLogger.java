package com.zhl.demo.chain;

/**
 * Debug级别日志处理器
 */
public class DebugLogger extends Logger {
    public DebugLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}
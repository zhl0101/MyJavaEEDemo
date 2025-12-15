package com.zhl.demo.chain;

/**
 * Error级别日志处理器
 */
public class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}
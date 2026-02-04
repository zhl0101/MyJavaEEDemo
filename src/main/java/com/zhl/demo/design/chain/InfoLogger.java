package com.zhl.demo.design.chain;

/**
 * Info级别日志处理器
 */
public class InfoLogger extends Logger {
    public InfoLogger(int level) {
        this.level = level;
    }
    
    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}
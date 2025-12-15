package com.zhl.demo.chain;

/**
 * 请求类
 */
public class Request {
    private Type type;
    private String content;
    private double amount;
    
    public enum Type {
        LEAVE, REIMBURSEMENT, LOAN
    }
    
    public Request(Type type, String content, double amount) {
        this.type = type;
        this.content = content;
        this.amount = amount;
    }
    
    // Getters
    public Type getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
    
    public double getAmount() {
        return amount;
    }
    
    @Override
    public String toString() {
        return "Request{" +
                "type=" + type +
                ", content='" + content + '\'' +
                ", amount=" + amount +
                '}';
    }
}
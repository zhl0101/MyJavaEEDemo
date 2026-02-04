package com.zhl.demo.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 股票价格监控示例
 */
class Stock {
    private String symbol;
    private double price;
    
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}

// 股票市场主题
class StockMarket implements Subject {
    private List<Observer> observers;
    private List<Stock> stocks;
    
    public StockMarket() {
        observers = new ArrayList<>();
        stocks = new ArrayList<>();
    }
    
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    
    // 添加股票
    public void addStock(Stock stock) {
        stocks.add(stock);
    }
    
    // 更新股票价格
    public void updateStockPrice(String symbol, double newPrice) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol)) {
                double oldPrice = stock.getPrice();
                stock.setPrice(newPrice);
                String message = String.format("股票 %s 价格从 %.2f 变更为 %.2f", 
                                             symbol, oldPrice, newPrice);
                notifyObservers(message);
                break;
            }
        }
    }
}

// 投资者观察者
class Investor implements Observer {
    private String name;
    
    public Investor(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String message) {
        System.out.println("投资者 " + name + " 收到提醒: " + message);
        
        // 根据消息内容做出投资决策
        if (message.contains("变更")) {
            System.out.println("投资者 " + name + " 正在分析价格变化...");
        }
    }
}
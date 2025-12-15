package com.zhl.demo.observer;

/**
 * 观察者模式演示类
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== 观察者模式演示 ===");
        
        // 新闻订阅系统示例
        System.out.println("\n--- 新闻订阅系统示例 ---");
        NewsPublisher publisher = new NewsPublisher();
        
        // 创建订阅者
        UserSubscriber user1 = new UserSubscriber("张三");
        UserSubscriber user2 = new UserSubscriber("李四");
        AdminSubscriber admin = new AdminSubscriber("王管理员");
        
        // 添加观察者
        publisher.addObserver(user1);
        publisher.addObserver(user2);
        publisher.addObserver(admin);
        
        // 发布新闻
        publisher.publishNews("今日天气晴朗，适合出行");
        System.out.println();
        publisher.publishNews("紧急：市中心发生交通事故，请绕行");
        
        // 股票市场示例
        System.out.println("\n--- 股票市场示例 ---");
        StockMarket market = new StockMarket();
        
        // 添加投资者
        Investor investor1 = new Investor("小明");
        Investor investor2 = new Investor("小红");
        
        market.addObserver(investor1);
        market.addObserver(investor2);
        
        // 添加股票
        market.addStock(new Stock("AAPL", 150.0));
        market.addStock(new Stock("GOOGL", 2500.0));
        
        // 更新股票价格
        market.updateStockPrice("AAPL", 155.5);
        System.out.println();
        market.updateStockPrice("GOOGL", 2550.0);
    }
}
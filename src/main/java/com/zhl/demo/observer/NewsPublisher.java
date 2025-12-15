package com.zhl.demo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题实现 - 新闻发布者
 */
public class NewsPublisher implements Subject {
    private List<Observer> observers;
    private String news;
    
    public NewsPublisher() {
        observers = new ArrayList<>();
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
    
    // 发布新闻
    public void publishNews(String news) {
        this.news = news;
        System.out.println("新闻发布: " + news);
        notifyObservers(news);
    }
}
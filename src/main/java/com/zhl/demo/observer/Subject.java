package com.zhl.demo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题接口
 */
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}
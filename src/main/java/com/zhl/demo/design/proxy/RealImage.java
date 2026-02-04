package com.zhl.demo.design.proxy;

/**
 * 真实主题 - 真实图片类
 */
public class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("正在从磁盘加载图片: " + filename);
        // 模拟加载图片的耗时操作
        try {
            Thread.sleep(1000); // 模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("图片加载完成: " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("显示图片: " + filename);
    }
}
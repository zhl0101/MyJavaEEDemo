package com.zhl.demo.proxy;

/**
 * 智能引用代理示例 - 计数代理
 */
public class CountingProxy implements Image {
    private RealImage realImage;
    private String filename;
    private static int count = 0;
    
    public CountingProxy(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
        count++;
        System.out.println("图片显示次数: " + count);
    }
    
    public static int getCount() {
        return count;
    }
}
package com.zhl.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 简单模型训练类
 * 用于演示基本的机器学习模型训练过程
 */
public class SimpleModelTrainer {
    
    // 模型参数
    private double weight;
    private double bias;
    
    // 训练参数
    private double learningRate;
    private int epochs;
    
    // 随机数生成器
    private Random random = new Random();
    
    /**
     * 构造函数
     * @param learningRate 学习率
     * @param epochs 训练轮数
     */
    public SimpleModelTrainer(double learningRate, int epochs) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        // 初始化模型参数
        this.weight = random.nextGaussian();
        this.bias = random.nextGaussian();
    }
    
    /**
     * 线性模型预测函数 y = weight * x + bias
     * @param x 输入值
     * @return 预测值
     */
    public double predict(double x) {
        return weight * x + bias;
    }
    
    /**
     * 计算均方误差损失
     * @param predictions 预测值列表
     * @param targets 真实值列表
     * @return 平均损失值
     */
    public double calculateLoss(List<Double> predictions, List<Double> targets) {
        double totalLoss = 0.0;
        for (int i = 0; i < predictions.size(); i++) {
            double error = predictions.get(i) - targets.get(i);
            totalLoss += error * error;
        }
        return totalLoss / predictions.size();
    }
    
    /**
     * 训练模型
     * @param trainingDataX 训练数据输入
     * @param trainingDataY 训练数据标签
     */
    public void train(List<Double> trainingDataX, List<Double> trainingDataY) {
        System.out.println("开始训练模型...");
        System.out.println("初始参数: weight=" + weight + ", bias=" + bias);
        
        for (int epoch = 0; epoch < epochs; epoch++) {
            // 前向传播 - 计算预测值
            List<Double> predictions = new ArrayList<>();
            for (Double x : trainingDataX) {
                predictions.add(predict(x));
            }
            
            // 计算损失
            double loss = calculateLoss(predictions, trainingDataY);
            
            // 计算梯度
            double weightGradient = 0.0;
            double biasGradient = 0.0;
            
            for (int i = 0; i < trainingDataX.size(); i++) {
                double x = trainingDataX.get(i);
                double y = trainingDataY.get(i);
                double pred = predictions.get(i);
                
                // 计算梯度
                weightGradient += 2 * (pred - y) * x;
                biasGradient += 2 * (pred - y);
            }
            
            weightGradient /= trainingDataX.size();
            biasGradient /= trainingDataX.size();
            
            // 更新参数
            weight -= learningRate * weightGradient;
            bias -= learningRate * biasGradient;
            
            // 每100轮打印一次训练信息
            if (epoch % 100 == 0) {
                System.out.println("Epoch: " + epoch + ", Loss: " + loss);
            }
        }
        
        System.out.println("训练完成!");
        System.out.println("最终参数: weight=" + weight + ", bias=" + bias);
    }
    
    /**
     * 测试模型
     * @param testDataX 测试数据输入
     * @param testDataY 测试数据标签
     */
    public void test(List<Double> testDataX, List<Double> testDataY) {
        System.out.println("\n开始测试模型...");
        List<Double> predictions = new ArrayList<>();
        for (Double x : testDataX) {
            predictions.add(predict(x));
        }
        
        double loss = calculateLoss(predictions, testDataY);
        System.out.println("测试损失: " + loss);
        
        System.out.println("\n预测结果对比:");
        for (int i = 0; i < testDataX.size(); i++) {
            System.out.printf("输入: %.2f, 真实值: %.2f, 预测值: %.2f\n", 
                            testDataX.get(i), testDataY.get(i), predictions.get(i));
        }
    }
    
    // Getter方法
    public double getWeight() {
        return weight;
    }
    
    public double getBias() {
        return bias;
    }
    
    public static void main(String[] args) {
        // 创建训练器实例
        SimpleModelTrainer trainer = new SimpleModelTrainer(0.01, 1000);
        
        // 生成模拟训练数据 (y = 2*x + 1 + noise)
        List<Double> trainingDataX = new ArrayList<>();
        List<Double> trainingDataY = new ArrayList<>();
        
        Random dataRandom = new Random();
        for (int i = 0; i < 100; i++) {
            double x = dataRandom.nextDouble() * 10;
            // 真实关系: y = 2*x + 1，加入一些噪声
            double y = 2 * x + 1 + (dataRandom.nextGaussian() * 0.1);
            trainingDataX.add(x);
            trainingDataY.add(y);
        }
        
        // 训练模型
        trainer.train(trainingDataX, trainingDataY);
        
        // 生成测试数据
        List<Double> testDataX = new ArrayList<>();
        List<Double> testDataY = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            double x = dataRandom.nextDouble() * 10;
            double y = 2 * x + 1 + (dataRandom.nextGaussian() * 0.1);
            testDataX.add(x);
            testDataY.add(y);
        }
        
        // 测试模型
        trainer.test(testDataX, testDataY);
    }
}
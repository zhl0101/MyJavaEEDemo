package com.zhl.test.algorithm;

import com.zhl.demo.algorithm.AlipayDiscount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlipayDiscountTest {

    @Test
    public void testSampleCase() {
        // 测试样例情况
        int n = 5;
        int k = 9;
        int[] prices = {3, 4, 2, 3, 1};
        String discountFlags = "11101";
        
        int result = AlipayDiscount.maxItems(n, k, prices, discountFlags);
        assertEquals(5, result, "样例测试失败");
    }
    
    @Test
    public void testAllDiscountItems() {
        // 测试所有物品都支持优惠的情况
        int n = 3;
        int k = 10;
        int[] prices = {10, 20, 30};
        String discountFlags = "111";
        
        // 折后价格分别为 9, 19, 28
        // 只能买价格为9的物品
        int result = AlipayDiscount.maxItems(n, k, prices, discountFlags);
        assertEquals(1, result, "所有物品都支持优惠测试失败");
    }
    
    @Test
    public void testNoDiscountItems() {
        // 测试所有物品都不支持优惠的情况
        int n = 3;
        int k = 15;
        int[] prices = {5, 6, 7};
        String discountFlags = "000";
        
        // 不支持优惠，所以按原价计算
        // 可以买5和6，总价格11 <= 15
        int result = AlipayDiscount.maxItems(n, k, prices, discountFlags);
        assertEquals(2, result, "所有物品都不支持优惠测试失败");
    }
    
    @Test
    public void testEdgeCase() {
        // 边界测试：没有足够余额购买任何物品
        int n = 2;
        int k = 1;
        int[] prices = {5, 6};
        String discountFlags = "10";
        
        // 第一个物品折后价格为 floor(5*0.95) = 4，仍然大于余额1
        // 第二个物品原价6，也大于余额1
        int result = AlipayDiscount.maxItems(n, k, prices, discountFlags);
        assertEquals(0, result, "边界测试失败");
    }
}
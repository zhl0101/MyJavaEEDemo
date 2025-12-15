package com.zhl.test;

import com.zhl.demo.AdvancedSequentialPrinter;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AdvancedSequentialPrinter类的单元测试
 */
public class AdvancedSequentialPrinterTest {

    @Test
    public void testConstructorAndGetMaxcount() {
        AdvancedSequentialPrinter printer = new AdvancedSequentialPrinter(10);
        assertEquals(10, printer.getMaxcount());

        AdvancedSequentialPrinter printer2 = new AdvancedSequentialPrinter(100);
        assertEquals(100, printer2.getMaxcount());
    }

    @Test
    public void testSetMaxcount() {
        AdvancedSequentialPrinter printer = new AdvancedSequentialPrinter(10);
        assertEquals(10, printer.getMaxcount());

        printer.setMaxcount(20);
        assertEquals(20, printer.getMaxcount());
    }

    @Test
    public void testPrintSequential() {
        AdvancedSequentialPrinter printer = new AdvancedSequentialPrinter(5);

        // 重定向标准输出以捕获打印内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        printer.printSequential();

        // 恢复标准输出
        System.setOut(originalOut);

        // 验证输出内容
        String expectedOutput = "奇数: 1\n偶数: 2\n奇数: 3\n偶数: 4\n奇数: 5\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testReset() {
        AdvancedSequentialPrinter printer = new AdvancedSequentialPrinter(3);
        
        // 重定向标准输出以捕获打印内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        printer.printSequential();
        
        // 恢复标准输出
        System.setOut(originalOut);
        
        // 重置并再次打印
        printer.reset();
        
        // 再次重定向标准输出
        ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent2));
        
        printer.printSequential();
        
        // 恢复标准输出
        System.setOut(originalOut);
        
        // 验证两次输出相同
        assertEquals(outContent.toString(), outContent2.toString());
    }

    @Test
    public void testPrintWithZeroMaxcount() {
        AdvancedSequentialPrinter printer = new AdvancedSequentialPrinter(0);
        assertEquals(0, printer.getMaxcount());

        // 重定向标准输出以捕获打印内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        printer.printSequential();

        // 恢复标准输出
        System.setOut(originalOut);

        // 验证没有输出
        assertEquals("", outContent.toString());
    }
}
package com.zhl.test;

import com.zhl.demo.SequentialPrinter;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SequentialPrinter类的单元测试
 */
public class SequentialPrinterTest {

    @Test
    public void testConstructorAndGetMaxcount() {
        SequentialPrinter printer = new SequentialPrinter(10);
        assertEquals(10, printer.getMaxcount());

        SequentialPrinter printer2 = new SequentialPrinter(100);
        assertEquals(100, printer2.getMaxcount());
    }

    @Test
    public void testSetMaxcount() {
        SequentialPrinter printer = new SequentialPrinter(10);
        assertEquals(10, printer.getMaxcount());

        printer.setMaxcount(20);
        assertEquals(20, printer.getMaxcount());
    }

    @Test
    public void testPrintOdd() {
        SequentialPrinter printer = new SequentialPrinter(5);
        
        // 重定向标准输出以捕获打印内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        printer.printOdd();

        // 恢复标准输出
        System.setOut(originalOut);

        // 验证输出内容
        String expectedOutput = "奇数: 1\n奇数: 3\n奇数: 5\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintEven() {
        SequentialPrinter printer = new SequentialPrinter(6);
        
        // 重定向标准输出以捕获打印内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        printer.printEven();

        // 恢复标准输出
        System.setOut(originalOut);

        // 验证输出内容
        String expectedOutput = "偶数: 2\n偶数: 4\n偶数: 6\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintWithZeroMaxcount() {
        SequentialPrinter printer = new SequentialPrinter(0);
        assertEquals(0, printer.getMaxcount());

        // 重定向标准输出以捕获打印内容
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        printer.printOdd();
        printer.printEven();

        // 恢复标准输出
        System.setOut(originalOut);

        // 验证没有输出
        assertEquals("", outContent.toString());
    }
}
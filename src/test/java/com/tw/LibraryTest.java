package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void test_print_main_interface(){
        Library library = new Library();
        library.printMainInterface();
        String str = "```\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n" +
                "```\r\n";
        assertThat(str, equalTo(systemOut()));
    }

    @Test
    public void test_input_1_return_add(){
        Library library = new Library();
        library.printAddInterface();
        String result = "```\n" +
                "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n" +
                "```\r\n";
        assertThat(result, equalTo(systemOut()));
    }
    @Test
    public void test_match_add_input_form(){
        Library library = new Library();
        String input1 = "张三，123，语文：89，数学：89";
        String input2 = "张三，语文：89，数学：89";

        boolean result1 = library.matchAddInputForm(input1);
        assertTrue(result1);
        assertTrue(!library.matchAddInputForm(input2));
    }

    @Test
    public void test_match_print_input_form(){
        Library library = new Library();
        String input1 = "123，34，453，65";
        String input2 = "，123，34";

        assertTrue(library.matchPringInputForm(input1));
        assertTrue(!library.matchPringInputForm(input2));

    }

    @Test
    public void test_add_student(){
        Library library = new Library();
        String student = "张三，123，语文：89，数学：89";

        library.addStudent(student);

        String out = "```\n" +
                "学生xxx的成绩被添加\n" +
                "```\r\n";
        assertThat(systemOut(),containsString(out));
    }

    @Test
    public void test_print_students(){
        Library library = new Library();

        String stu1 = "张三，123，语文：89，数学：78，英语：85，编程：95";
        String stu2 = "李四，234，语文：78，数学：85，英语：84，编程：85";
        String stu3 = "王五，345，语文：97，数学：84，英语：88，编程：92";
        library.addStudent(stu1);
        library.addStudent(stu2);
        library.addStudent(stu3);

        library.printAll();

        String out = "成绩单";
        assertThat(systemOut(), containsString(out));
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }


    private String systemOut() {
        String out = outContent.toString();
        return out;
    }

}

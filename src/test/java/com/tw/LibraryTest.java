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
                "1. ���ѧ��\n" +
                "2. ���ɳɼ���\n" +
                "3. �˳�\n" +
                "���������ѡ��1��3����\n" +
                "```\r\n";
        assertThat(str, equalTo(systemOut()));
    }

    @Test
    public void test_input_1_return_add(){
        Library library = new Library();
        library.printAddInterface();
        String result = "```\n" +
                "������ѧ����Ϣ����ʽ������, ѧ��, ѧ��: �ɼ�, ...�������س��ύ��\n" +
                "```\r\n";
        assertThat(result, equalTo(systemOut()));
    }
    @Test
    public void test_match_add_input_form(){
        Library library = new Library();
        String input1 = "������123�����ģ�89����ѧ��89";
        String input2 = "���������ģ�89����ѧ��89";

        boolean result1 = library.matchAddInputForm(input1);
        assertTrue(result1);
        assertTrue(!library.matchAddInputForm(input2));
    }

    @Test
    public void test_match_print_input_form(){
        Library library = new Library();
        String input1 = "123��34��453��65";
        String input2 = "��123��34";

        assertTrue(library.matchPringInputForm(input1));
        assertTrue(!library.matchPringInputForm(input2));

    }

    @Test
    public void test_add_student(){
        Library library = new Library();
        String student = "������123�����ģ�89����ѧ��89";

        library.addStudent(student);

        String out = "```\n" +
                "ѧ��xxx�ĳɼ������\n" +
                "```\r\n";
        assertThat(systemOut(),containsString(out));
    }

    @Test
    public void test_print_students(){
        Library library = new Library();

        String stu1 = "������123�����ģ�89����ѧ��78��Ӣ�85����̣�95";
        String stu2 = "���ģ�234�����ģ�78����ѧ��85��Ӣ�84����̣�85";
        String stu3 = "���壬345�����ģ�97����ѧ��84��Ӣ�88����̣�92";
        library.addStudent(stu1);
        library.addStudent(stu2);
        library.addStudent(stu3);

        library.printAll();

        String out = "�ɼ���";
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

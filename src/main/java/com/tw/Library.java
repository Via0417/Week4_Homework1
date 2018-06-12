package com.tw;

import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Library {

    private List<Student> studentList;

    public Library(){
        this.studentList = new ArrayList<>();
    }
    public void printMainInterface() {
        String initString = "```\n" +
                "1. ���ѧ��\n" +
                "2. ���ɳɼ���\n" +
                "3. �˳�\n" +
                "���������ѡ��1��3����\n" +
                "```";
        System.out.println(initString);
    }

    public void printAddInterface(){
        System.out.println("```\n" +
                "������ѧ����Ϣ����ʽ������, ѧ��, ѧ��: �ɼ�, ...�������س��ύ��\n" +
                "```");
    }



    public boolean matchAddInputForm(String input) {
        String regex = "(^[\\u4E00-\\u9FFF]+)��\\d+��[\\u4E00-\\u9FFF]+��\\d{1,3}(��[\\u4E00-\\u9FFF]+��\\d{1,3})*";
        boolean isMatch = Pattern.matches(regex, input);
        return isMatch;
    }

    public boolean matchPringInputForm(String input) {
        String regex = "\\d+(��\\d+)*";
        return Pattern.matches(regex, input);
    }

    public String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void addStudent(String description) {
        Student student = new Student(description);
        studentList.add(student);
        System.out.println("```\n" +
                "ѧ��xxx�ĳɼ������\n" +
                "```");
        printMainInterface();
    }

    public void printAll() {
        float total = 0;
        System.out.println("```\n" +
                "�ɼ���\n" +
                "����|��ѧ|����|Ӣ��|���|ƽ����|�ܷ�\n" +
                "========================");
        for (Student stu : studentList){
            String printLine = stu.name + "|"
                    + stu.mathScore + "|"
                    + stu.chiScore + "|"
                    + stu.engScore + "|"
                    + stu.proScore + "|"
                    + stu.average + "|"
                    + stu.total + "|";
            total += stu.total;
            System.out.println(printLine);
        }
        List<Student> sortStudentList = studentList.stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.total > o2.total)
                    return 1;
                else if (o1.total == o2.total)
                    return 0;
                else
                    return -1;
            }
        }).collect(Collectors.toList());
        int length = studentList.size();
        float left = sortStudentList.get(length / 2).total;
        float right = sortStudentList.get((length + 1) / 2).total;
        float middle = (left + right) / 2;

        System.out.println("========================");
        System.out.println("ȫ���ܷ�ƽ������" + total);
        System.out.println("ȫ���ܷ���λ����" + middle);
    }

    private class Student{
        public String name;
        public int id;
        public float mathScore = 0;
        public float chiScore = 0;
        public float engScore = 0;
        public float proScore = 0;
        public float average = 0;
        public float total = 0;

        public Student(String toxen){
            String[] strList = toxen.split("��");
            this.name = strList[0];
            this.id = Integer.valueOf(strList[1]);
            for (int i = 2; i < strList.length; i++){
                String[] subject_score = strList[i].split("��");
                String subject = subject_score[0];
                float score = Float.valueOf(subject_score[1]);
                switch (subject){
                    case "��ѧ":
                        this.mathScore = score;
                        break;
                    case "����":
                        this.chiScore = score;
                        break;
                    case "Ӣ��":
                        this.engScore = score;
                        break;
                    case "���":
                        this.proScore = score;
                }
                this.total = mathScore + chiScore + engScore + proScore;
                this.average = total / 4;
            }
        }
    }
}

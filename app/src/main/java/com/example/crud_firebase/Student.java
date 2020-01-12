package com.example.crud_firebase;

public class Student {
    int id;
    String name,rollno;

    public Student(int id, String name, String rollno) {
        this.id = id;
        this.name = name;
        this.rollno = rollno;
    }
    public  Student(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }
}

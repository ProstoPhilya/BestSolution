package org.example;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString(){
        return "Person {\n\tName: " + this.name + "\n\tAge: " + this.age + "\n}\n";
    }
}

package uk.ac.ncl.advancedjava.model;

import java.time.LocalDate;

public class Student {
    private String name;
    private int age;
    private LocalDate birthDate;
    private int id;

    public Student(String name, int age, LocalDate birthDate, int id) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.id = id;
    }

    public String toString() {
        return "id: " + id + ", name: " + name + ", age: " + age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getId() {
        return id;
    }

}

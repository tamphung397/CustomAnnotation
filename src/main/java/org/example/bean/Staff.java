package org.example.bean;

import org.example.annotation.TamLength;
import org.example.annotation.TamNotNull;
import org.example.annotation.TamRange;

public class Staff {
    @TamNotNull
    private String name;

    @TamRange(min = 18, max = 30)
    private int age;

    @TamLength(min = 1, max = 5)
    private String position;

    private int salary;

    public Staff(String name, int age, String position, int salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

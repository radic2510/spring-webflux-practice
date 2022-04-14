package com.codestates.edastudy;

public class Person {
    private String name;
    private String email;
    private String number;

    public Person(String name, String email, String number) {
        this.setName(name);
        this.setEmail(email);
        this.setNumber(number);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

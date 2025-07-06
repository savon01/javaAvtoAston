package org.example;

public class Person {
    String lastName;
    String firstName;
    String name;
    int age;
    String phone;
    String email;
    String address;

    public Person() {
        this.lastName = "Попов";
        this.firstName = "Иван";
        this.name = "Иванович";
        this.age = 25;
        this.phone = "375259874562";
        this.email = "выфлрфыр@ьфшд.ru";
        this.address = "Цуцунка 24/8";
    }

    public String getFullInfo() {
        return lastName + " " + firstName + " " + name + " " + age + " " + phone + " " + email + " " + address;
    }
}

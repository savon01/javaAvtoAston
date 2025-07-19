package org.example.lesson8;

import java.util.*;

public class PhoneNumber {

    private Map<String, Set<String>> phoneNumbers;
    private Set<String> allNumbers;


    public PhoneNumber() {
        phoneNumbers = new HashMap<String, Set<String>>();
        allNumbers = new HashSet<String>();
    }

    public void add(String lastName, String number) {
        if (allNumbers.contains(number)) {
            System.out.println("Телефон:" + number + " уже занят");
            return;
        }

        //добавление номера
        phoneNumbers.putIfAbsent(lastName, new HashSet<>());

        if (phoneNumbers.get(lastName).contains(number)) {
            System.out.println("Ошибка: номер: " + number + "уже привязан к " + lastName);
            return;
        }

        phoneNumbers.get(lastName).add(number);
        allNumbers.add(number);
        System.out.println("Номер " + number + " добавлен к фамилии " + lastName);
    }

    // Получить номера по фамилии
    public Set<String> get(String lastName) {
        return phoneNumbers.getOrDefault(lastName, Collections.emptySet());
    }

    // Вывод всего справочника
    public void printAll() {
        for (Map.Entry<String, Set<String>> entry : phoneNumbers.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

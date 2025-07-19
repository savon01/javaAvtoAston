package org.example.lesson8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCounter {
    public static void main(String[] args) {
        String[] words = {
                "кошка", "собака", "дом", "дерево", "река",
                "кошка", "машина", "солнце", "река", "окно",
                "дом", "кошка", "улица", "машина", "река"
        };
        Map<String, Integer> wordCounts = countWords(words);

        List<String> uniqueWords = getUnicoWords(wordCounts);

        printUniqueWords(uniqueWords);
        printWordCounts(wordCounts);

        PhoneNumber phoneNumber = new PhoneNumber();

        phoneNumber.add("Иванов", "123-45-67");
        phoneNumber.add("Иванов", "789-01-23");
        phoneNumber.add("Петров", "234-56-78");
        phoneNumber.add("Иванов", "123-45-67");
        phoneNumber.add("Сидоров", "123-45-67");
        phoneNumber.add("Попов", "123-45-07");
        phoneNumber.add("Куклов", "123-45-97");

        System.out.println("Номера ИВанова:");

        for (String phone : phoneNumber.get("Иванов")) {
            System.out.println(phone);
        }

        System.out.println("\nНомера Петрова:");
        for (String phone : phoneNumber.get("Петров")) {
            System.out.println(phone);
        }

        System.out.println("\nВесь справочник:");
        phoneNumber.printAll();
    }


    //Подсчет слов
    public static Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }

    //Метод для получения уникальных слов
    public static List<String> getUnicoWords(Map<String, Integer> words) {
        return new ArrayList<>(words.keySet());
    }

    // Метод для вывода уникальных слов
    public static void printUniqueWords(List<String> uniqueWords) {
        System.out.println("Уникальные слова:");
        for (String word : uniqueWords) {
            System.out.println(word);
        }
    }

    // Метод для вывода количества вхождений каждого слова
    public static void printWordCounts(Map<String, Integer> wordCounts) {
        System.out.println("\nКоличество вхождений каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}

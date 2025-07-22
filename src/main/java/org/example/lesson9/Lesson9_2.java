package org.example.lesson9;

import java.util.*;
import java.util.stream.Collectors;

public class Lesson9_2 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Highload", "High", "Load", "Highload");

        long count = strings.stream().filter(s-> s.equals("High")).count();
        System.out.println(count);

        String first = strings.stream()
                .findFirst().orElse("0");
        System.out.println("2.2. Первый элемент: " + first);

        String last = strings.isEmpty() ? "0" : strings.get(strings.size() - 1);
        System.out.println("2.3. Последний элемент: " + last);


        List<String> list = Arrays.asList("f10", "f15", "f2", "f4", "f4");

        List<String> sortedList = list.stream()
                .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.substring(1))))
                .toList();

        String[] array = sortedList.toArray(new String[0]);
        System.out.println(Arrays.toString(array));
    }

}

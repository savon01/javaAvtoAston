package org.example.lesson9;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RandomNumber {
    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Введите сколько чисел генерируем: ");
        int number = scaner.nextInt();


        long countNumber = IntStream.of(number)
                .filter(x -> x % 2 == 0)
                .count();

        System.out.println("Вот сколько четных: " + countNumber);
    }
}

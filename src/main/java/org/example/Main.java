package org.example;

public class Main {
    public static void main(String[] args) {
//        System.out.println(sumNumber(5, 5));
        System.out.println(intNumber(0));
    }



    public static boolean sumNumber(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    public static String intNumber(int a) {
        if (a < 0) {
            return "Отрицательное";
        } else {
            return "Положительное";
        }
    }
}
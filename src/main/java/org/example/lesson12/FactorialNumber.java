package org.example.lesson12;

import java.util.Scanner;

public class FactorialNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n = sc.nextInt();
        System.out.println(factorial(n));
    }

    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number is negative");
        }
        if (n == 1 || n ==0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

}

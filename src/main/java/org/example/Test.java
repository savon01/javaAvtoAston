package org.example;

public class Test {
    public static String method() {
        try {
            return "1";
        }
        finally {
            return "2";
        }
    }

    public static void main(String[] args) {
        String what = method();
        System.out.println(what);
    }
}

package org.example;

import org.example.lesson7.ArrayProcessor;
import org.example.lesson7.MyArrayDataException;
import org.example.lesson7.MyArraySizeException;
import org.example.lessons6.AppData;
import org.example.lessons6.CsvUtils;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "X", "12"}, // <- ошибка здесь
                {"13", "14", "15", "16"}
        };

        try {
            int result = ArrayProcessor.processArray(array);
            System.out.println("Сумма всех элементов: " + result);
        } catch (MyArraySizeException e) {
            System.err.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println("Ошибка данных в массиве: " + e.getMessage());
        }

//        String[] header = {"Value1", "Value2", "Value3"};
//        int[][] data = {
//                {100, 200, 300},
//                {400, 500, 600},
//                {700, 800, 900}
//        };
//
//        AppData appData = new AppData(header, data);
//
//        try {
//            // Сохраняем в CSV
//            CsvUtils.save(appData, "data.csv");
//
//            // Загружаем обратно
//            AppData loaded = CsvUtils.load("data.csv");
//
//            // Печать загруженных данных
//            System.out.println(String.join(" | ", loaded.getHeader()));
//            for (int[] row : loaded.getData()) {
//                System.out.println(Arrays.toString(row));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        System.out.println(sumNumber(5, 5));
////        Person p = new Person();
////        System.out.println(p.getFullInfo());
////        System.out.println(intNumber(0));
//    }
//
//
//
//    public static boolean sumNumber(int a, int b) {
//        int sum = a + b;
//        return sum >= 10 && sum <= 20;
//    }
//
//    public static String intNumber(int a) {
//        if (a < 0) {
//            return "Отрицательное";
//        } else {
//            return "Положительное";
//        }
    }
}
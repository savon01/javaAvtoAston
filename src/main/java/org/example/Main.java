package org.example;

import org.example.lessons6.AppData;
import org.example.lessons6.CsvUtils;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String[] header = {"Value1", "Value2", "Value3"};
        int[][] data = {
                {100, 200, 300},
                {400, 500, 600},
                {700, 800, 900}
        };

        AppData appData = new AppData(header, data);

        try {
            // Сохраняем в CSV
            CsvUtils.save(appData, "data.csv");

            // Загружаем обратно
            AppData loaded = CsvUtils.load("data.csv");

            // Печать загруженных данных
            System.out.println(String.join(" | ", loaded.getHeader()));
            for (int[] row : loaded.getData()) {
                System.out.println(Arrays.toString(row));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(sumNumber(5, 5));
//        Person p = new Person();
//        System.out.println(p.getFullInfo());
//        System.out.println(intNumber(0));
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
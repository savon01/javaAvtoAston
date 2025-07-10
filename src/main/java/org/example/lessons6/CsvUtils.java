package org.example.lessons6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtils {
    private static final String DELIMITER = ";";

    public  static void save(AppData appData, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Запись заголовков
            writer.write(String.join(DELIMITER, appData.getHeader()));
            writer.newLine();

            // Запись чисел
            for (int[] row : appData.getData()) {
                String[] strRow = Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .toArray(String[]::new);
                writer.write(String.join(DELIMITER, strRow));
                writer.newLine();
            }
        }
    }

    public static AppData load(String fileName) throws IOException {
        List<int[]> dataList = new ArrayList<>();
        String[] header;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Читаем заголовок
            String line = reader.readLine();
            if (line == null) throw new IOException("Файл пустой");
            header = line.split(DELIMITER);

            // Читаем данные
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                int[] row = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
                dataList.add(row);
            }
        }

        int[][] dataArray = dataList.toArray(new int[0][]);
        return new AppData(header, dataArray);
    }
}

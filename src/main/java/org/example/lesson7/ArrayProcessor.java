package org.example.lesson7;

public class ArrayProcessor {

    public static int processArray(String [][] array) throws MyArrayDataException, MyArraySizeException {
        if (array.length != 4) {
            throw new MyArraySizeException("Массив должен иметь 4 строки");
        }
        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArrayDataException("Каждая строка должна содержать 4 элемента");
            }
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка в ячейке [" + i + "][" + j + "]: '" + array[i][j] + "'");
                }
            }
        }
        return sum;
    }
}

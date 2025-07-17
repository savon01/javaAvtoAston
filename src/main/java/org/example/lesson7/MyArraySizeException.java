package org.example.lesson7;

// Исключение при неправильном размере массива
public class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

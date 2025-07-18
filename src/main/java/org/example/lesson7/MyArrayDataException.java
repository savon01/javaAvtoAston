package org.example.lesson7;


// Исключение при неверных данных в массиве
public class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}

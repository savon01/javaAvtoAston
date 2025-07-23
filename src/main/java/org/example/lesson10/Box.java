package org.example.lesson10;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    private final List<T> fruits = new ArrayList<T>();

    //Добавление фруктов
    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    // Посчитать вес коробки
    public float  getWeight() {
        float sum = 0;
        for (T fruit : fruits) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    //сравниваем две коробки
    public boolean compare(Box<?> otherBox) {
        return Math.abs(getWeight() - otherBox.getWeight()) < 0.001f;
    }

    //пересыпаем фрукты
    public void transferFruitsTo (Box<T> otherBox) {
        if (otherBox == this) return;// нельзя пересыпать в туже коробку
        otherBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

    //считаем сколько фруктов
    public int countFruits() {
        return fruits.size();
    }

}
package org.example.lesson10;

public class Start {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<Apple>();
        Box<Apple> appleBox2 = new Box<Apple>();
        Box<Orange> orangeBox1 = new Box<Orange>();

        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());

        orangeBox1.addFruit(new Orange());
        orangeBox1.addFruit(new Orange());

        System.out.println("Вес коробки с яблоками: " + appleBox1.getWeight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox1.getWeight());

        System.out.println("Коробки по весу ровны: " + appleBox1.compare(orangeBox1));

        appleBox1.transferFruitsTo(appleBox2);
//        orangeBox1.transferFruitsTo(appleBox2);
        System.out.println("Яблок в первой коробке: " + appleBox1.countFruits());
        System.out.println("Яблок во второй коробке: " + appleBox2.countFruits());

    }
}

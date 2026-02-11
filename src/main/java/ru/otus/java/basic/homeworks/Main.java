package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Пример 1: Работа с целыми числами ===");
        List<Integer> sortedNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<>(sortedNumbers);

        System.out.println("Исходный отсортированный список: " + sortedNumbers);
        System.out.println("Отсортированный список из дерева: " + tree1.getSortedList());

        Integer found1 = tree1.find(5);
        System.out.println("Поиск элемента 5: " + (found1 != null ? "Найден: " + found1 : "Не найден"));

        Integer found2 = tree1.find(1);
        System.out.println("Поиск элемента 1: " + (found2 != null ? "Найден: " + found2 : "Не найден"));

        Integer found3 = tree1.find(10);
        System.out.println("Поиск элемента 10: " + (found3 != null ? "Найден: " + found3 : "Не найден"));

        Integer found4 = tree1.find(15);
        System.out.println("Поиск элемента 15: " + (found4 != null ? "Найден: " + found4 : "Не найден"));

        System.out.println();

        System.out.println("=== Пример 2: Работа со строками ===");
        List<String> sortedStrings = new ArrayList<>(Arrays.asList(
                "apple", "banana", "cherry", "date", "elderberry", "fig", "grape"
        ));
        BinarySearchTree<String> tree2 = new BinarySearchTree<>(sortedStrings);

        System.out.println("Исходный отсортированный список: " + sortedStrings);
        System.out.println("Отсортированный список из дерева: " + tree2.getSortedList());

        String found5 = tree2.find("cherry");
        System.out.println("Поиск элемента 'cherry': " + (found5 != null ? "Найден: " + found5 : "Не найден"));

        String found6 = tree2.find("apple");
        System.out.println("Поиск элемента 'apple': " + (found6 != null ? "Найден: " + found6 : "Не найден"));

        String found7 = tree2.find("kiwi");
        System.out.println("Поиск элемента 'kiwi': " + (found7 != null ? "Найден: " + found7 : "Не найден"));

        System.out.println();

        System.out.println("=== Пример 3: Пустой список ===");
        List<Integer> emptyList = new ArrayList<>();
        BinarySearchTree<Integer> tree3 = new BinarySearchTree<>(emptyList);
        System.out.println("Отсортированный список из пустого дерева: " + tree3.getSortedList());
        Integer found8 = tree3.find(5);
        System.out.println("Поиск в пустом дереве: " + (found8 != null ? "Найден: " + found8 : "Не найден"));
    }
}


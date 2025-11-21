package ru.otus.java.basic.homeworks;

public class Application {
    private static final int ARRAY_SIZE = 100_000_000;

    public static void main(String[] args) {
        System.out.println("Реализация №1: Однопоточное заполнение");
        singleThreaded();

        System.out.println("\nРеализация №2: Многопоточное заполнение (4 потока)");
        multiThreaded();
    }

    public static void singleThreaded() {
        double[] array = new double[ARRAY_SIZE];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Время выполнения: " + duration + " мс");
        System.out.println("Проверка: array[0] = " + array[0] + ", array[last] = " + array[ARRAY_SIZE - 1]);
    }

    public static void multiThreaded() {
        double[] array = new double[ARRAY_SIZE];
        int numberOfThreads = 4;
        int chunkSize = ARRAY_SIZE / numberOfThreads;

        Thread[] threads = new Thread[numberOfThreads];

        long startTime = System.currentTimeMillis();

        for (int t = 0; t < numberOfThreads; t++) {
            final int threadIndex = t;
            final int startIndex = threadIndex * chunkSize;
            final int endIndex = (threadIndex == numberOfThreads - 1) ? ARRAY_SIZE : (threadIndex + 1) * chunkSize;

            threads[t] = new Thread(() -> {
                for (int i = startIndex; i < endIndex; i++) {
                    array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
                }
            });
            threads[t].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Время выполнения: " + duration + " мс");
        System.out.println("Проверка: array[0] = " + array[0] + ", array[last] = " + array[ARRAY_SIZE - 1]);
    }
}
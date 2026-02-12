package ru.otus.java.basic.homeworks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AbcPrinter {

    static class Printer {
        private char current = 'A';

        public synchronized void print(char letter, char next) {
            for (int i = 0; i < 5; i++) {
                while (current != letter) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.print(letter);
                current = next;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> printer.print('A', 'B'));
        executor.submit(() -> printer.print('B', 'C'));
        executor.submit(() -> printer.print('C', 'A'));

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println();
    }
}
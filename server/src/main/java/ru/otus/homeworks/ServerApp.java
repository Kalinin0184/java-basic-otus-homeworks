package ru.otus.homeworks;

public class ServerApp {
    public static final int PORT = 8189;

    public static void main(String[] args) {
        new Server(PORT).start();
    }
}
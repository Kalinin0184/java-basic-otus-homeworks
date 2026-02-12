package ru.otus.homeworks;

public class HttpServerApp {

    private static final int HTTP_PORT = 8080;

    public static void main(String[] args) {
        HttpServer server = new HttpServer(HTTP_PORT);
        server.start();
    }
}



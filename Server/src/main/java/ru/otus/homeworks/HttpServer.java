package ru.otus.homeworks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {

    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        System.out.println("HTTP сервер запускается на порту: " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("HTTP сервер запущен на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                handleClient(socket);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка работы HTTP сервера", e);
        }
    }

    private void handleClient(Socket socket) {
        try (socket;
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             OutputStream out = socket.getOutputStream()) {

            String requestLine = reader.readLine();
            if (requestLine == null || requestLine.isBlank()) {
                return;
            }

            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
            }

            String[] parts = requestLine.split(" ");
            String method = parts.length > 0 ? parts[0] : "";
            String path = parts.length > 1 ? parts[1] : "/";

            HttpResponse response = new HttpResponse();

            if (!"GET".equalsIgnoreCase(method)) {
                response.setStatus(405, "Method Not Allowed");
                Map<String, Object> body = new HashMap<>();
                body.put("error", "Only GET is supported");
                body.put("method", method);
                response.setJsonBody(body);
            } else if ("/hello".equals(path)) {
                response.setStatus(200, "OK");
                Map<String, Object> body = new HashMap<>();
                body.put("message", "Hello from simple HTTP server");
                body.put("time", Instant.now().toString());
                response.setJsonBody(body);
            } else {
                response.setStatus(404, "Not Found");
                Map<String, Object> body = new HashMap<>();
                body.put("error", "Resource not found");
                body.put("path", path);
                response.setJsonBody(body);
            }

            response.write(out);
        } catch (IOException e) {
            System.out.println("Ошибка при обработке HTTP-запроса: " + e.getMessage());
        }
    }
}



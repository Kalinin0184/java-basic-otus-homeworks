package ru.otus.homeworks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;

    private List<ClientHandler> clients;

    private ExecutorService clientHandlingPool;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        clientHandlingPool = Executors.newFixedThreadPool(10);
    }

    public void start(){
        DatabaseService.getInstance();
        System.out.println("База данных инициализирована");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запустился на порту: " + port);
            while (true){
                Socket socket = serverSocket.accept();
                try {
                    ClientHandler handler = new ClientHandler(socket, this);
                    clientHandlingPool.submit(handler);
                } catch (IOException e) {
                    System.out.println("Ошибка при обработке подключения клиента: " + e.getMessage());
                    try {
                        socket.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (clientHandlingPool != null && !clientHandlingPool.isShutdown()) {
                clientHandlingPool.shutdown();
            }
        }
    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
        System.out.println("Клиент " + clientHandler.getUsername() + " подключился с ролью: " + clientHandler.getRole());
    }

    public void unsubscribe(ClientHandler clientHandler){
        if (clientHandler.getUsername() != null) {
            System.out.println("Клиент " + clientHandler.getUsername() + " отключился");
        } else {
            System.out.println("Неавторизованный клиент отключился");
        }
        clients.remove(clientHandler);
    }

    public int getClientsCount() {
        return clients.size();
    }

    public void broadcastMessage(String message){
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void sendPrivateMessage(String fromUsername, String toUsername, String message){
        boolean recipientFound = false;
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(toUsername)) {
                c.sendMsg("[Личное от " + fromUsername + "]: " + message);
                recipientFound = true;
                break;
            }
        }
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(fromUsername)) {
                if (recipientFound) {
                    c.sendMsg("[Вы отправили личное сообщение " + toUsername + "]: " + message);
                } else {
                    c.sendMsg("[Ошибка]: Пользователь " + toUsername + " не найден в чате");
                }
                break;
            }
        }
    }

    public void kickUser(String adminUsername, String targetUsername) {
        boolean targetFound = false;
        ClientHandler targetHandler = null;

        ClientHandler adminHandler = null;
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(adminUsername)) {
                adminHandler = c;
            }
            if (c.getUsername().equals(targetUsername)) {
                targetHandler = c;
                targetFound = true;
            }
        }

        if (adminHandler != null) {
            if (targetFound && targetHandler != null) {
                targetHandler.sendMsg("[Система]: Вы были отключены администратором " + adminUsername);
                targetHandler.disconnect();
                adminHandler.sendMsg("[Система]: Пользователь " + targetUsername + " был отключен");
                broadcastMessage("[Система]: Пользователь " + targetUsername + " был отключен администратором");
            } else {
                adminHandler.sendMsg("[Ошибка]: Пользователь " + targetUsername + " не найден в чате");
            }
        }
    }
}

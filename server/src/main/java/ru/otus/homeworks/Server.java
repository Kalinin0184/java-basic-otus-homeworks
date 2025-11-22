package ru.otus.homeworks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;

    private List<ClientHandler> clients;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
    }

    public void start(){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запустился на порту: " + port);
            while (true){
                Socket socket = serverSocket.accept();
                subscribe(new ClientHandler(socket, this));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler){
        System.out.println("Клиент " + clientHandler.getUsername() + " отключился");
        clients.remove(clientHandler);
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
}

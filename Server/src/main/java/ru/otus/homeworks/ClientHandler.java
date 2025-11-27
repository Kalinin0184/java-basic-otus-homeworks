package ru.otus.homeworks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;

    private String username;
    private UserRole role;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        username = "user" + socket.getPort();
        this.role = server.getClientsCount() == 0 ? UserRole.ADMIN : UserRole.USER;

        new Thread(() -> {
            System.out.println("Клиент подключился " + socket.getPort() + " с ролью: " + role);
            sendMsg("Вы подключились с ником: " + username + " (роль: " + role + ")");
            try {
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        if (message.startsWith("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                        if (message.startsWith("/w ")) {
                            String[] tokens = message.split(" ", 3);
                            if (tokens.length >= 3) {
                                String recipient = tokens[1];
                                String privateMessage = tokens[2];
                                server.sendPrivateMessage(username, recipient, privateMessage);
                            } else {
                                sendMsg("[Ошибка]: Неверный формат команды. Используйте: /w <ник> <сообщение>");
                            }
                        }
                        if (message.startsWith("/kick ")) {
                            if (role == UserRole.ADMIN) {
                                String[] tokens = message.split(" ", 2);
                                if (tokens.length >= 2) {
                                    String targetUsername = tokens[1];
                                    server.kickUser(username, targetUsername);
                                } else {
                                    sendMsg("[Ошибка]: Неверный формат команды. Используйте: /kick <ник>");
                                }
                            } else {
                                sendMsg("[Ошибка]: У вас нет прав для выполнения этой команды. Только ADMIN может использовать /kick");
                            }
                        }
                    } else {
                        server.broadcastMessage(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void disconnect() {
        server.unsubscribe(this);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

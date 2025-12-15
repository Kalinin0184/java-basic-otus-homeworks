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
    private boolean authenticated;
    private DatabaseService databaseService;

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.username = null;
        this.role = UserRole.USER;
        this.authenticated = false;
        this.databaseService = DatabaseService.getInstance();

        new Thread(() -> {
            System.out.println("Клиент подключился " + socket.getPort());
            sendMsg("Добро пожаловать! Для входа используйте: /login <username> <password>");
            sendMsg("Для регистрации используйте: /register <username> <password>");
            try {
                while (true) {
                    String message = in.readUTF();
                    
                    if (!authenticated) {
                        if (message.startsWith("/login ")) {
                            String[] tokens = message.split(" ", 3);
                            if (tokens.length >= 3) {
                                String loginUsername = tokens[1];
                                String password = tokens[2];
                                if (databaseService.authenticateUser(loginUsername, password)) {
                                    this.username = loginUsername;
                                    this.role = databaseService.getUserRole(loginUsername);
                                    this.authenticated = true;
                                    server.subscribe(this);
                                    sendMsg("/authok");
                                    sendMsg("Вы успешно вошли как " + username + " (роль: " + role + ")");
                                    server.broadcastMessage("[Система]: " + username + " присоединился к чату");
                                } else {
                                    sendMsg("[Ошибка]: Неверное имя пользователя или пароль");
                                }
                            } else {
                                sendMsg("[Ошибка]: Неверный формат команды. Используйте: /login <username> <password>");
                            }
                        } else if (message.startsWith("/register ")) {
                            String[] tokens = message.split(" ", 3);
                            if (tokens.length >= 3) {
                                String registerUsername = tokens[1];
                                String password = tokens[2];
                                if (databaseService.registerUser(registerUsername, password)) {
                                    sendMsg("[Успех]: Пользователь " + registerUsername + " успешно зарегистрирован. Войдите используя /login");
                                } else {
                                    sendMsg("[Ошибка]: Пользователь с таким именем уже существует");
                                }
                            } else {
                                sendMsg("[Ошибка]: Неверный формат команды. Используйте: /register <username> <password>");
                            }
                        } else if (message.startsWith("/exit")) {
                            sendMsg("/exitok");
                            break;
                        } else {
                            sendMsg("[Ошибка]: Вы не авторизованы. Используйте /login или /register");
                        }
                    } else {
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
        if (authenticated && username != null) {
            server.broadcastMessage("[Система]: " + username + " покинул чат");
        }
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

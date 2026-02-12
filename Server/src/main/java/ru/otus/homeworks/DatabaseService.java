package ru.otus.homeworks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static final String DB_URL = "jdbc:sqlite:chat.db";
    private static DatabaseService instance;

    private DatabaseService() {
        loadDriver();
        initializeDatabase();
    }

    public static synchronized DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    private void loadDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC драйвер не найден. Убедитесь, что:\n" +
                    "1. Зависимость sqlite-jdbc добавлена в pom.xml\n" +
                    "2. Проект пересобран через Maven (mvn clean install)\n" +
                    "3. Зависимости находятся в classpath при запуске", e);
        }
    }

    private void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL DEFAULT 'USER', " +
                    "created_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ")";
            
            try (Statement statement = connection.createStatement()) {
                statement.execute(createUsersTable);
            }

            if (getUserCount() == 0) {
                registerUser("admin", "admin", UserRole.ADMIN);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка инициализации базы данных", e);
        }
    }

    private int getUserCount() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM users")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения количества пользователей", e);
        }
        return 0;
    }

    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка аутентификации пользователя", e);
        }
    }

    public UserRole getUserRole(String username) {
        String sql = "SELECT role FROM users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return UserRole.valueOf(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения роли пользователя", e);
        }
        return UserRole.USER;
    }

    public boolean registerUser(String username, String password) {
        return registerUser(username, password, UserRole.USER);
    }

    public boolean registerUser(String username, String password, UserRole role) {
        if (userExists(username)) {
            return false;
        }
        
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role.name());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка регистрации пользователя", e);
        }
    }

    public boolean userExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка проверки существования пользователя", e);
        }
        return false;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        String sql = "SELECT username FROM users";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка пользователей", e);
        }
        return usernames;
    }
}


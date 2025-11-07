package ru.otus.java.basic.homeworks;

import java.io.*;
import java.net.*;

public class Server {
    private static final String OPERATIONS = "Доступные операции: +, -, *, /";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Ожидание подключения клиента...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(
                             clientSocket.getOutputStream(), true)) {

                    System.out.println("Клиент подключен: " + clientSocket.getInetAddress());

                    out.println(OPERATIONS);

                    String input;
                    while ((input = in.readLine()) != null) {
                        if (input.equalsIgnoreCase("exit")) {
                            System.out.println("Клиент отключился");
                            break;
                        }

                        System.out.println("Получен запрос: " + input);
                        String result = calculate(input);
                        out.println(result);
                        System.out.println("Отправлен результат: " + result);
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }

    private static String calculate(String expression) {
        try {
            String[] parts = expression.trim().split("\\s+");

            if (parts.length != 3) {
                return "Ошибка: Неверный формат. Используйте: число1 операция число2";
            }

            double num1 = Double.parseDouble(parts[0]);
            String operation = parts[1];
            double num2 = Double.parseDouble(parts[2]);
            double result = 0;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        return "Ошибка: Деление на ноль!";
                    }
                    result = num1 / num2;
                    break;
                default:
                    return "Ошибка: Неподдерживаемая операция: " + operation;
            }

            return "Результат: " + result;
        } catch (NumberFormatException e) {
            return "Ошибка: Неверный формат числа";
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }
}
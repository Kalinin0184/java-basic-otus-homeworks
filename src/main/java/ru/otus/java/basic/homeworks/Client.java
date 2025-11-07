package ru.otus.java.basic.homeworks;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Подключено к серверу ");

            String operations = in.readLine();
            System.out.println(operations);
            System.out.println("Для выхода введите 'exit'\n");

            while (true) {
                System.out.print("Введите первое число: ");
                String num1 = scanner.nextLine();

                if (num1.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }

                System.out.print("Введите операцию (+, -, *, /): ");
                String operation = scanner.nextLine();

                if (operation.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }

                System.out.print("Введите второе число: ");
                String num2 = scanner.nextLine();

                if (num2.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    break;
                }

                String request = num1 + " " + operation + " " + num2;
                out.println(request);

                String response = in.readLine();
                System.out.println(response);
                System.out.println();
            }

            System.out.println("Клиент отключен");
        } catch (ConnectException e) {
            System.err.println("Не удалось подключиться к серверу. Убедитесь, что сервер запущен.");
        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }
}
package ru.otus.java.basic.homeworks;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    private static final String PROJECT_ROOT = ".";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Редактор текстовых файлов ===\n");

        // Шаг 1: Выводим список текстовых файлов из корневого каталога
        List<String> textFiles = getTextFiles();

        if (textFiles.isEmpty()) {
            System.out.println("В корневом каталоге проекта не найдено текстовых файлов (.txt)");
            System.out.println("Вы можете создать новый файл, введя его имя.\n");
        } else {
            System.out.println("Список текстовых файлов в корневом каталоге проекта:");
            for (int i = 0; i < textFiles.size(); i++) {
                System.out.println((i + 1) + ". " + textFiles.get(i));
            }
            System.out.println();
        }

        System.out.print("Введите имя файла для работы (например, example.txt): ");
        String fileName = scanner.nextLine().trim();

        if (fileName.isEmpty()) {
            System.out.println("Ошибка: имя файла не может быть пустым!");
            return;
        }

        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("\n=== Содержимое файла " + fileName + " ===");
            try {
                String content = readFile(file);
                if (content.isEmpty()) {
                    System.out.println("(Файл пуст)");
                } else {
                    System.out.println(content);
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
                return;
            }
            System.out.println("=== Конец содержимого ===\n");
        } else {
            System.out.println("\nФайл " + fileName + " не существует. Будет создан новый файл.\n");
        }

        System.out.println("Введите текст для записи в файл (для завершения введите 'exit' или нажмите Ctrl+C):");
        System.out.println("(Каждая строка будет добавлена в файл)\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("\nРабота с файлом завершена.");
                    break;
                }

                writer.write(input);
                writer.newLine();
                writer.flush();

                System.out.println("Строка записана в файл.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }

        scanner.close();
    }

    private static List<String> getTextFiles() {
        List<String> textFiles = new ArrayList<>();

        try {
            Path rootPath = Paths.get(PROJECT_ROOT);
            textFiles = Files.list(rootPath)
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(name -> name.toLowerCase().endsWith(".txt"))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении каталога: " + e.getMessage());
        }

        return textFiles;
    }

    private static String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }
}

package ru.otus.java.basic.homeworks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileSequenceCounter {

    public static int countSequenceOccurrences(String fileName, String sequence) throws IOException {
        if (sequence == null || sequence.isEmpty()) {
            return 0;
        }

        String fileContent;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            char[] buffer = new char[8192];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, charsRead);
            }
            fileContent = content.toString();
        }

        int count = 0;
        int index = 0;
        int sequenceLength = sequence.length();

        while ((index = fileContent.indexOf(sequence, index)) != -1) {
            count++;
            index += sequenceLength;
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        try {
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine().trim();

            System.out.print("Введите искомую последовательность символов: ");
            String sequence = scanner.nextLine();

            File file = new File(fileName);
            if (!file.exists()) {
                System.err.println("Ошибка: Файл '" + fileName + "' не найден.");
                return;
            }

            if (!file.isFile()) {
                System.err.println("Ошибка: '" + fileName + "' не является файлом.");
                return;
            }

            int count = countSequenceOccurrences(fileName, sequence);

            System.out.println("Результат: последовательность '" + sequence +
                    "' встречается в файле " + count + " раз(а).");

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}


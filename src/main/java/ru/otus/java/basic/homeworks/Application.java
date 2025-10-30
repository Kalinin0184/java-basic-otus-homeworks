package ru.otus.java.basic.homeworks;

public class Application {

    public static void main(String[] args) {
        // Корректный 4x4
        String[][] ok = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Неверные данные (ячейка [2][1])
        String[][] badData = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "x", "11", "12"},
                {"13", "14", "15", "16"}
        };

        // Неверный размер
        String[][] badSize = {
                {"1", "2", "3"},
                {"4", "5", "6"}
        };

        try {
            int sum = sumArray(ok);
            System.out.println("Сумма элементов (ok): " + sum);
        } catch (AppArraySizeException | AppArrayDataException e) {
            System.out.println("Ошибка при обработке ok: " + e.getMessage());
        } finally {
            System.out.println("Завершена попытка обработки массива ok.");
        }

        try {
            int sum = sumArray(badData);
            System.out.println("Сумма элементов (badData): " + sum);
        } catch (AppArraySizeException | AppArrayDataException e) {
            System.out.println("Ошибка при обработке badData: " + e.getMessage());
        } finally {
            System.out.println("Завершена попытка обработки массива badData.");
        }

        try {
            int sum = sumArray(badSize);
            System.out.println("Сумма элементов (badSize): " + sum);
        } catch (AppArraySizeException | AppArrayDataException e) {
            System.out.println("Ошибка при обработке badSize: " + e.getMessage());
        } finally {
            System.out.println("Завершена попытка обработки массива badSize.");
        }
    }

    public static int sumArray(String[][] arr) throws AppArraySizeException, AppArrayDataException {
        // Проверка размерности 4x4
        if (arr == null || arr.length != 4) {
            throw new AppArraySizeException("Ожидался массив 4x4: неверное число строк");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || arr[i].length != 4) {
                throw new AppArraySizeException("Ожидался массив 4x4: неверное число столбцов в строке " + i);
            }
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String cell = arr[i][j];
                try {
                    sum += Integer.parseInt(cell);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]: \"" + cell + "\"", i, j, cell);
                }
            }
        }
        return sum;
    }
}
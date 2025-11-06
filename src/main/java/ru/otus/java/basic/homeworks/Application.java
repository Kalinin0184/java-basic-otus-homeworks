package ru.otus.java.basic.homeworks;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        ArrayList<Integer> rangeList = createRangeList(1, 10);
        System.out.println("Диапазон от 1 до 10: " + rangeList);

        ArrayList<Integer> testList = new ArrayList<>();
        testList.add(2);
        testList.add(6);
        testList.add(8);
        testList.add(3);
        testList.add(10);
        System.out.println("Сумма элементов > 5: " + sumElementsGreaterThanFive(testList));

        ArrayList<Integer> fillList = new ArrayList<>();
        fillList.add(1);
        fillList.add(2);
        fillList.add(3);
        fillListWithNumber(5, fillList);
        System.out.println("Список после заполнения числом 5: " + fillList);

        ArrayList<Integer> increaseList = new ArrayList<>();
        increaseList.add(1);
        increaseList.add(2);
        increaseList.add(3);
        increaseListElementsByNumber(10, increaseList);
        System.out.println("Список после увеличения на 10: " + increaseList);

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Иван", 25));
        employees.add(new Employee("Мария", 30));
        employees.add(new Employee("Петр", 20));
        employees.add(new Employee("Анна", 28));

        System.out.println("Имена сотрудников: " + getEmployeeNames(employees));

        ArrayList<Employee> filtered = filterEmployeesByMinAge(employees, 25);
        System.out.println("Сотрудники возрастом >= 25: " + filtered);

        System.out.println("Средний возраст > 24: " + isAverageAgeGreaterThan(employees, 24));
        System.out.println("Средний возраст > 30: " + isAverageAgeGreaterThan(employees, 30));

        Employee youngest = getYoungestEmployee(employees);
        System.out.println("Самый молодой сотрудник: " + youngest.getName() + ", возраст: " + youngest.getAge());
    }

    public static ArrayList<Integer> createRangeList(int min, int max) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list;
    }

    public static int sumElementsGreaterThanFive(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer num : list) {
            if (num > 5) {
                sum += num;
            }
        }
        return sum;
    }

    public static void fillListWithNumber(int number, ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, number);
        }
    }

    public static void increaseListElementsByNumber(int number, ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + number);
        }
    }

    public static ArrayList<String> getEmployeeNames(ArrayList<Employee> employees) {
        ArrayList<String> names = new ArrayList<>();
        for (Employee employee : employees) {
            names.add(employee.getName());
        }
        return names;
    }

    public static ArrayList<Employee> filterEmployeesByMinAge(ArrayList<Employee> employees, int minAge) {
        ArrayList<Employee> filtered = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= minAge) {
                filtered.add(employee);
            }
        }
        return filtered;
    }

    public static boolean isAverageAgeGreaterThan(ArrayList<Employee> employees, double minAverageAge) {
        if (employees.isEmpty()) {
            return false;
        }

        int totalAge = 0;
        for (Employee employee : employees) {
            totalAge += employee.getAge();
        }

        double averageAge = (double) totalAge / employees.size();
        return averageAge > minAverageAge;
    }

    public static Employee getYoungestEmployee(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            return null;
        }

        Employee youngest = employees.get(0);
        for (Employee employee : employees) {
            if (employee.getAge() < youngest.getAge()) {
                youngest = employee;
            }
        }
        return youngest;
    }
}

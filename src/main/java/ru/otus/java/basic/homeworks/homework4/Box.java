package ru.otus.java.basic.homeworks.homework4;

public class Box {
    private int lenght;
    private int width;
    private int height;
    private String color;
    private boolean isOpen = false;
    private String item;

    public Box(String color, int lenght, int width, int height) {
        this.color = color;
        this.height = height;
        this.lenght = lenght;
        this.width = width;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void open() {
        isOpen = true;
        System.out.println("Коробка открыта");
    }

    public void close() {
        isOpen = false;
        System.out.println("Коробка закрыта");
    }

    public void printInfo()
    {
        System.out.println("Длина: " + lenght +  " " + "Ширина: " + width + " " + "Высота: " + height + " " + "Цвет: " + color);
    }

    public void putItem(String item)
    {
        if (isOpen) {
           if (this.item == null) {
               this.item = item;
               System.out.println("Вы положили в коробку предмет: " + item);
           }
           else {
               System.out.println("В коробке уже есть предмет");
           }
        }
        else {
            System.out.println("Коробка закрыта");
        }
    }

    public void removeItem()
    {
        if (isOpen) {
            if (item != null) {
                System.out.println("Вы убрали из коробки предмет: " + item);
                item = null;
            }
            else {
                System.out.println("В коробке нету предмета");
            }
        }
        else {
            System.out.println("Коробка закрыта");
        }
    }
}

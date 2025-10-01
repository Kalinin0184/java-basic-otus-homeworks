package ru.otus.java.basic.homeworks;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eatFood(Plates plate) {
        if (plate.getCurrentAmountFood() >= appetite) {
            satiety = true;
            plate.reduceFood(appetite);
            appetite = 0;
            System.out.println("Кот поел и сыт");
        } else {
            satiety = false;
            System.out.println("Коту не хватает еды");
        }
    }

    public void info() {
        if (satiety) {
            System.out.println("Кот: " + name + " сыт");
        } else {
            System.out.println("Кот: " + name + " голоден");
        }
    }
}

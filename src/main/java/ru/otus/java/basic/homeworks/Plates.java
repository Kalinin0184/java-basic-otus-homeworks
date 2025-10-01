package ru.otus.java.basic.homeworks;

public class Plates {
    int maxAmountFood;
    int currentAmountFood;

    public Plates(int maxAmountFood) {
        this.maxAmountFood = maxAmountFood;
        currentAmountFood = maxAmountFood;
    }

    public int getCurrentAmountFood() {
        return currentAmountFood;
    }

    public void setCurrentAmountFood(int currentAmountFood) {
        this.currentAmountFood = currentAmountFood;
    }

    public int getMaxAmountFood() {
        return maxAmountFood;
    }

    public void setMaxAmountFood(int maxAmountFood) {
        this.maxAmountFood = maxAmountFood;
    }
}

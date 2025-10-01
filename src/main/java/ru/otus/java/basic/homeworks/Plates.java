package ru.otus.java.basic.homeworks;

public class Plates {
    private int maxAmountFood;
    private int currentAmountFood;

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

    public void addFood(int amountFood) {
        if (currentAmountFood < maxAmountFood) {
            if ((currentAmountFood + amountFood) <= maxAmountFood) {
                currentAmountFood += amountFood;
                System.out.println("В тарелку успешно добавлено " + amountFood + " еды");
            } else {
                System.out.println("В тарелке не хватит места");
            }
        } else {
            System.out.println("Тарелка заполнена");
        }
    }

    public boolean reduceFood(int foodAmount) {
        if ((currentAmountFood - foodAmount) >= 0) {
            currentAmountFood -= foodAmount;
            return true;
        } else {
            return false;
        }
    }
}

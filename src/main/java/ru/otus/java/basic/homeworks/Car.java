package ru.otus.java.basic.homeworks;

public class Car extends GeneralTransport {
    private int petrol;
    private final int MAX_DIST = 60;

    public int getPetrol() {
        return petrol;
    }

    public void setPetrol(int petrol) {
        this.petrol = petrol;
    }

    public Car() {
        super(TransportType.CAR);
        this.petrol = 40;
    }

    @Override
    public boolean canMove(TerrainType terrainType) {
        return terrainType == TerrainType.plain;
    }

    @Override
    public boolean move(int distant, TerrainType terrainType) {
        if (!canMove(terrainType)) {
            System.out.println("Машина не может двигаться по местности " + terrainType.getTerrainName());
            return false;
        }
        if (distant > MAX_DIST || petrol < distant) {
            System.out.println("Не может передвигаться! Слишком большое расстояние или закончился бензин.");
            return false;
        }
        petrol -= distant;
        System.out.println("Машина проехала по местности " + terrainType.getTerrainName() + " " + distant + " км. Топлива осталось: " + petrol);
        return true;
    }

}
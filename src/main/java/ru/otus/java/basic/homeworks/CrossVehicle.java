package ru.otus.java.basic.homeworks;

public class CrossVehicle extends GeneralTransport {
    private int petrol;
    private final int MAX_DIST = 30;

    public int getPetrol() {
        return petrol;
    }

    public void setPetrol(int petrol) {
        this.petrol = petrol;
    }

    public CrossVehicle() {
        super(TransportType.ROVER);
        this.petrol = 60;
    }

    @Override
    public boolean canMove(TerrainType terrainType) {
        return true;
    }

    @Override
    public boolean move(int distant, TerrainType terrainType) {
        if (distant > MAX_DIST || distant > petrol) {
            System.out.println("Не может передвигаться! Слишком большое расстояние или закончился бензин.");
            return false;
        }
        petrol -= distant;
        System.out.println("Вездеход проехал по местности " + terrainType.getTerrainName() + " " + distant + " км. Топлива осталось: " + petrol);
        return true;
    }
}
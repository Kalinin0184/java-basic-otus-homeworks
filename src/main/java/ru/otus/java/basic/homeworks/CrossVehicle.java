package ru.otus.java.basic.homeworks;

public class CrossVehicle extends Vehicle{
    private int petrolCount;
    private int petrolConsumption;

    public CrossVehicle(int petrolCount, int petrolConsumption) {
        this.setVehicleName("Вездеход");
        this.petrolCount = petrolCount;
        this.petrolConsumption = petrolConsumption;
    }

    @Override
    public boolean move(TerrainType terrainType, int distance) {
        petrolCount -= petrolConsumption * distance;
        if (petrolCount >= 0) {
            return super.move(terrainType, distance);
        } else {
            System.out.println("Транспорту " + getVehicleName() + " не хватает топлива на прохождение расстояния " + distance);
            return false;
        }
    }
}

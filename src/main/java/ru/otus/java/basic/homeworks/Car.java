package ru.otus.java.basic.homeworks;

public class Car extends Vehicle{
    private int petrolCount;
    private int petrolConsumption;

    public Car(int petrolCount, int petrolConsumption) {
        this.setVehicleName("Машина");
        this.petrolCount = petrolCount;
        this.petrolConsumption = petrolConsumption;
    }

    @Override
    public boolean move(TerrainType terrainType, int distance){
        if (terrainType != TerrainType.plain) {
            System.out.println("По местности: " + terrainType.getTerrainName() + " " + getVehicleName() + " не проедет");
            return false;
        }
        petrolCount -= petrolConsumption * distance;
        if (petrolCount >= 0){
            return super.move(terrainType, distance);
        }
        else {
            System.out.println("Транспорту " + getVehicleName() + " не хватает топлива на прохождение расстояния " + distance);
            return false;
        }
    }
}

package ru.otus.java.basic.homeworks;

public class Horse extends Vehicle {
    private int strengthCount;
    private int strengthConsumption;

    public Horse(int strengthCount, int strengthConsumption) {
        this.setVehicleName("Лошадь");
        this.strengthCount = strengthCount;
        this.strengthConsumption = strengthConsumption;
    }

    @Override
    public boolean move(TerrainType terrainType, int distance) {
        if (terrainType.equals(TerrainType.swamp)) {
            System.out.println("По местности " + terrainType.getTerrainName() + " " + getVehicleName() + " не проедет");
            return false;
        }
        strengthCount -= strengthConsumption * distance;
        if (strengthCount >= 0) {
            return super.move(terrainType, distance);
        } else {
            System.out.println("Транспорту " + getVehicleName() + " не хватает сил на прохождение расстояния " + distance);
            return false;
        }
    }
}

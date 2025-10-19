package ru.otus.java.basic.homeworks;

public class Bicycle extends Vehicle{
    public Bicycle() {
        this.setVehicleName("Велосипед");
    }

    @Override
    public boolean move(TerrainType terrainType, int distance){
        if (terrainType != TerrainType.swamp) {
            return super.move(terrainType, distance);
        }
        else {
            System.out.println("По местности: " + terrainType.getTerrainName() + " " + getVehicleName() + " не проедет");
            return false;
        }
    }
}

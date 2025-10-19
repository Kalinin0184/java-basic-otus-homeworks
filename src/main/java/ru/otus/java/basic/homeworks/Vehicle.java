package ru.otus.java.basic.homeworks;

public class Vehicle {
    private String vehicleName;

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public boolean move(TerrainType terrainType, int distance){
        System.out.println("Проехал расстояние " + distance + " метров, на транспорте " + vehicleName + ", по местности " + terrainType.getTerrainName());
        return true;
    }
}

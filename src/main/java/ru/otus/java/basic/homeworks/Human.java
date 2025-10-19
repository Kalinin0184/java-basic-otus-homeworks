package ru.otus.java.basic.homeworks;

public class Human {
    private String name;
    private String currentTransport;

    public Human(String name) {
        this.name = name;
    }
    public void takeVehicle(Vehicle vehicle) {
        currentTransport = vehicle.getVehicleName();
        System.out.println("Человек пересел на транспорт " + currentTransport);
    }

    public void leaveVehicle(Vehicle vehicle) {
        System.out.println("Человек встал с транспорта " + currentTransport);
        currentTransport = null;
    }

    public void moveOnVehicle(Vehicle vehicle, TerrainType terrainType, int distance) {
        if (currentTransport != null) {
            vehicle.move(terrainType, distance);
        } else {
            System.out.println("Человек пошел пешком преодолевать расстояние " + distance + " метров" );
        }
    }
}

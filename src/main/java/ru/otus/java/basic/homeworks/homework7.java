package ru.otus.java.basic.homeworks;

public class homework7 {
    public static void main(String[] args) {
        Human human = new Human("Denis");
        Horse horse = new Horse (50,1);
        human.takeVehicle(horse);
        human.moveOnVehicle(horse, TerrainType.plain, 20);
        human.leaveVehicle(horse);
        human.moveOnVehicle(horse, TerrainType.plain, 8);
    }
}

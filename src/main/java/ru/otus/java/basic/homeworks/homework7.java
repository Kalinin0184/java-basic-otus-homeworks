package ru.otus.java.basic.homeworks;

public class homework7 {
    public static void main(String[] args) {
        Human human = new Human("Иван", 10);
        Transport[] transports = {new Car(), new Horse(), new Bicycle(), new CrossVehicle()};

        human.move(5, TerrainType.denseForest);
        human.move(5, TerrainType.swamp);
        human.move(5, TerrainType.plain);
        System.out.println();

        for (Transport t : transports) {
            human.sitDownHum(t, human);
            human.move(15, TerrainType.denseForest);
            human.move(10, TerrainType.swamp);
            human.move(20, TerrainType.plain);
            human.standUpHum(t, human);
            System.out.println();
        }

    }
}
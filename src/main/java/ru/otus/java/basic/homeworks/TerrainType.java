package ru.otus.java.basic.homeworks;

public enum TerrainType {
    denseForest ("Густой лес"),
    plain ("Равнина"),
    swamp ("Болото");

    private String terrainName;

    public String getTerrainName() {
        return terrainName;
    }

    TerrainType(String terrainName) {
        this.terrainName = terrainName;
    }
}

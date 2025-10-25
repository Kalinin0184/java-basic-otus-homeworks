package ru.otus.java.basic.homeworks;

abstract class GeneralTransport implements Transport {
    protected TransportType type;

    public GeneralTransport(TransportType type) {
        this.type = type;
    }

    @Override
    public void sitDown(Human human) {
        System.out.println(human.getName() + " начал использовать транспорт " + type.getNameTypeRu());
    }

    @Override
    public void standUp(Human human) {
        System.out.println(human.getName() + " перестал использовать транспорт " + type.getNameTypeRu());
    }
}
package javastream.chapter4.model;

/**
 * Car 를 상속하는 여러가지 자동차들을 만들 목적
 * Car 를 상속하는 하위클래스는 `drive` method 를 상세구현 해주어야 한다.
 */
public abstract class Car {
    protected String name;
    protected String brand;

    public Car(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public abstract void drive();
}

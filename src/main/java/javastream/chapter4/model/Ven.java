package javastream.chapter4.model;

public class Ven extends Car {
    public Ven(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println("Driving a ven, name: " + this.name + ", brand: " + this.brand);
    }
}

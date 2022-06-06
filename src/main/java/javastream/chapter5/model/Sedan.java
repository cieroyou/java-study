package javastream.chapter5.model;

public class Sedan extends Car {
    public Sedan(String name, String brand) {
        super(name, brand);
    }

    @Override
    public void drive() {
        System.out.println("Driving a sedan, name: " + this.name + ", brand: " + this.brand);
    }
}

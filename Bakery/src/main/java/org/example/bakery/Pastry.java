package org.example.bakery;

public class Pastry {
    private int id;
    private String name;
    private double price;

    public Pastry(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

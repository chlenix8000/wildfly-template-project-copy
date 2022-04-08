package org.example;

public class Product {
    private int id;
    private String name;
    private double cost;

    public Product(int id, String name, double cost) {
        id = this.id;
        name = this.name;
        cost = this.cost;
    }

    public int getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}

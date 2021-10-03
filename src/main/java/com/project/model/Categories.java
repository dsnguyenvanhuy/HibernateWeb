package com.project.model;

public class Categories {
    private String name;
    private long amount;

    public Categories() {
    }

    public Categories(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}

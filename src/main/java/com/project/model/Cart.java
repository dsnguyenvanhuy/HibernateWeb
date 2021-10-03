package com.project.model;

import com.project.pojo.Products;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    // chua thong tin ve mot don hang hien tai
    private List<Products> items; // list of item in cart

    public Cart() {
        items = new ArrayList<>();
    }

    // add a new product to cart
    public void add(Products ci, int numberProduct) {
        for (Products x : items) {
            if (ci.getId() == x.getId()) {
                x.setNumber(x.getNumber() + numberProduct);
                return;
            }
        }
        ci.setNumber(numberProduct);
        items.add(ci);
    }

    //remove a product from cart
    public void remove(int id) {
        for (Products x : items) {
            if (x.getId() == id) {
                items.remove(x);
                return;
            }
        }
    }

    // minus (-) number of product in cart
    public void minus(int mid) {
        for (Products x : items) {
            if (x.getId() == mid && x.getNumber() > 0) {
                x.setNumber(x.getNumber() - 1);
                if (x.getNumber() == 0) {
                    remove(x.getId());
                }
                return;
            }
        }
    }

    // plus (+) number of product in cart
    public void plus(int pid) {
        for (Products x : items) {
            if (x.getId() == pid) {
                x.setNumber(x.getNumber() + 1);
                return;
            }
        }
    }

    // return total amount of cart
    public double getAmount() {
        double s = 0;
        for (Products x : items) {
            s += x.getPrice() * x.getNumber();
        }
        return Math.round(s * 100.0) / 100.0;
    }

    // return list of products in cart
    public List<Products> getItems() {
        return items;
    }
}

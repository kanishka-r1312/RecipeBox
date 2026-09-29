package com.example.recipebox.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private String quantity;

    private boolean purchased;

    public ShoppingList() {
    }

    public ShoppingList(String itemName, String quantity, boolean purchased) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.purchased = purchased;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}
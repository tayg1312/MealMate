package com.example.mealmate;

import java.io.Serializable;

public class FridgeModel implements Serializable {
    String id, userId, type, name, date, quantity;

    public FridgeModel() {
    }

    public FridgeModel(String id, String userId, String type, String name, String date, String quantity) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.name = name;
        this.date = date;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

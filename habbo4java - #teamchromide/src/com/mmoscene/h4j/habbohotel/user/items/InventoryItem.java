package com.mmoscene.h4j.habbohotel.user.items;

import com.mmoscene.h4j.habbohotel.furniture.Furniture;
import com.mmoscene.h4j.habbohotel.user.User;
import com.mmoscene.h4j.network.sessions.Session;

public class InventoryItem {
    private int id;
    private User owner;
    private Furniture base;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Furniture getBase() {
        return base;
    }

    public void setBase(Furniture base) {
        this.base = base;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

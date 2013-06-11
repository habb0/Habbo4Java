package com.mmoscene.h4j.habbohotel.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.furniture.Furniture;

public class CatalogItem {
    private int id, page, cost, pixel_cost, currency_cost, quantity, base_id, ltd_stock, ltd_purchased;

    private String name;

    private Boolean ltd;

    public CatalogItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPixelCost() {
        return pixel_cost;
    }

    public void setPixelCost(int pixel_cost) {
        this.pixel_cost = pixel_cost;
    }

    public int getCurrencyCost() {
        return currency_cost;
    }

    public void setCurrencyCost(int currency_cost) {
        this.currency_cost = currency_cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBaseId() {
        return base_id;
    }

    public void setBaseId(int base) {
        this.base_id = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLtd() {
        return ltd;
    }

    public void setLtd(Boolean ltd) {
        this.ltd = ltd;
    }

    public int getLtdStock() {
        return ltd_stock;
    }

    public void setLtdStock(int ltd_stock) {
        this.ltd_stock = ltd_stock;
    }

    public int getLtdPurchased() {
        return ltd_purchased;
    }

    public void setLtdPurchased(int ltd_purchased) {
        this.ltd_purchased = ltd_purchased;
    }

    public Furniture getBase() {
        return H4J.getHabboHotel().getFurnitureManager().getByID(this.base_id);
    }

    public String getPresetFlag() {
        if (getName().split("_").length >= 3)
        {
            return getName().split("_")[2];
        }

        return "";
    }
}

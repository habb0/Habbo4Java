package com.mmoscene.h4j.habbohotel.furniture;

import org.magicwerk.brownies.collections.GapList;

public class Furniture {
    private int id, width, length, sprite, iterate_count;

    private String public_name, item_name, type, interaction;

    private Float stack_height;

    private boolean is_stackable, is_sitable, is_walkable, is_recyclable, is_tradeable, is_sellable, is_giftable, is_layable;

    private GapList<Integer> vendors = new GapList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

    public int getIterateCount() {
        return iterate_count;
    }

    public void setIterateCount(int iterate_count) {
        this.iterate_count = iterate_count;
    }

    public String getPublicName() {
        return public_name;
    }

    public void setPublicName(String public_name) {
        this.public_name = public_name;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public Float getStackHeight() {
        return stack_height;
    }

    public void setStackHeight(Float stack_height) {
        this.stack_height = stack_height;
    }

    public boolean isStackable() {
        return is_stackable;
    }

    public void setStackable(boolean is_stackable) {
        this.is_stackable = is_stackable;
    }

    public boolean isSitable() {
        return is_sitable;
    }

    public void setSitable(boolean is_sitable) {
        this.is_sitable = is_sitable;
    }

    public boolean isWalkable() {
        return is_walkable;
    }

    public void setWalkable(boolean is_walkable) {
        this.is_walkable = is_walkable;
    }

    public boolean isRecyclable() {
        return is_recyclable;
    }

    public void setRecyclable(boolean is_recyclable) {
        this.is_recyclable = is_recyclable;
    }

    public boolean isTradeable() {
        return is_tradeable;
    }

    public void setTradeable(boolean is_tradeable) {
        this.is_tradeable = is_tradeable;
    }

    public boolean isSellable() {
        return is_sellable;
    }

    public void setSellable(boolean is_sellable) {
        this.is_sellable = is_sellable;
    }

    public boolean isGiftable() {
        return is_giftable;
    }

    public void setGiftable(boolean is_giftable) {
        this.is_giftable = is_giftable;
    }

    public boolean isLayable() {
        return is_layable;
    }

    public void setLayable(boolean is_layable) {
        this.is_layable = is_layable;
    }

    public GapList<Integer> getVendors() {
        return vendors;
    }

    public void setVendors(GapList<Integer> vendors) {
        this.vendors = vendors;
    }

    public String getPresetFlag() {
        if (item_name.split("_").length >= 3)
        {
            return item_name.split("_")[2];
        }

        return "";
    }
}

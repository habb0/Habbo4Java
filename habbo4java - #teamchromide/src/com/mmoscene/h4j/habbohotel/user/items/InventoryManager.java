package com.mmoscene.h4j.habbohotel.user.items;

import com.mmoscene.h4j.H4J;
import gnu.trove.map.hash.THashMap;
import org.magicwerk.brownies.collections.GapList;

public class InventoryManager {
    private THashMap<Integer, InventoryItem> items = new THashMap<>();

    private int user = 0;

    public InventoryManager(int id) {

        user = id;

        items = H4J.getDAO().getItemsDAO().getInventoryById(id);
    }

    public GapList<InventoryItem> getFloors() {
        GapList<InventoryItem> floors = new GapList<>();

        for(InventoryItem i : items.values()) {
            if (i.getBase().getType().equals("s")) {
                floors.add(i);
            }
        }

        return floors;
    }

    public GapList<InventoryItem> getWalls() {
        GapList<InventoryItem> walls = new GapList<>();

        for(InventoryItem i : items.values()) {
            if (i.getBase().getType().equals("s")) {
                walls.add(i);
            }
        }

        return walls;
    }

    public GapList<InventoryItem> getItems() {
        GapList<InventoryItem> items = new GapList<>();

        for(InventoryItem i : this.items.values()) {
            items.add(i);
        }

        return items;
    }

    public void refresh() {
        items = H4J.getDAO().getItemsDAO().getInventoryById(user);
    }
}

package com.mmoscene.h4j.habbohotel.furniture;

import com.mmoscene.h4j.H4J;
import gnu.trove.map.hash.THashMap;

public class FurnitureManager {

    private THashMap<Integer, Furniture> furniture;

    public FurnitureManager() {
        furniture = H4J.getDAO().getFurnitureDAO().getFurniture();

        H4J.getLogger(Furniture.class.getName()).info(String.format("Loaded %d Furnis successfully!", furniture.size()));
    }

    public Furniture getByID(int id) {
        return furniture.get(id);
    }
}

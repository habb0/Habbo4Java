package com.mmoscene.h4j.database;

import com.mmoscene.h4j.H4J;
import gnu.trove.map.hash.THashMap;
import org.magicwerk.brownies.collections.GapList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Furniture {
    public THashMap<Integer, com.mmoscene.h4j.habbohotel.furniture.Furniture> getFurniture() {

        THashMap<Integer, com.mmoscene.h4j.habbohotel.furniture.Furniture> furniture = new THashMap<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM server_furni")) {

                try (ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        furniture.put(set.getInt("id"), this.generateAndFill(set));
                    }
                }
            }
        } catch(Exception ex) {
            H4J.getLogger(Furniture.class.getName()).error(ex.getMessage());
        }

        return furniture;
    }

    public com.mmoscene.h4j.habbohotel.furniture.Furniture generateAndFill(ResultSet set) {
        com.mmoscene.h4j.habbohotel.furniture.Furniture furniture = new com.mmoscene.h4j.habbohotel.furniture.Furniture();

        try {
            furniture.setId(set.getInt("id"));
            furniture.setWidth(set.getInt("width"));
            furniture.setLength(set.getInt("length"));
            furniture.setSprite(set.getInt("sprite_id"));
            furniture.setIterateCount(set.getInt("interaction_modes_count"));
            furniture.setPublicName(set.getString("public_name"));
            furniture.setItemName(set.getString("item_name"));
            furniture.setType(set.getString("type"));
            furniture.setInteraction(set.getString("interaction_type"));
            furniture.setStackHeight(set.getFloat("stack_height"));

            furniture.setStackable(set.getInt("can_stack") == 1);
            furniture.setSitable(set.getInt("can_sit") == 1);
            furniture.setWalkable(set.getInt("is_walkable") == 1);
            furniture.setRecyclable(set.getInt("allow_recycle") == 1);
            furniture.setTradeable(set.getInt("allow_trade") == 1);
            furniture.setSellable(set.getInt("allow_marketplace_sell") == 1);
            furniture.setGiftable(set.getInt("allow_gift") == 1);
            furniture.setLayable(set.getString("interaction_type").equalsIgnoreCase("bed"));

            furniture.setVendors(new GapList<Integer>());

            for(String vending : set.getString("vending_ids").split(",")) {
                furniture.getVendors().add(new Integer(vending.replace(" ", "")));
            }

        } catch(Exception ex) {
            H4J.getLogger(Furniture.class.getName()).error(ex.getMessage());
        }
        return furniture;
    }
}

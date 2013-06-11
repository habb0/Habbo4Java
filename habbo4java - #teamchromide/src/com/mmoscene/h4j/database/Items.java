package com.mmoscene.h4j.database;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.user.items.InventoryItem;
import com.mysql.jdbc.Statement;
import gnu.trove.map.hash.THashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Items {
    public int completePurchase(int user, int base, String extra, String type) {
        int id = 0;

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO server_items (room, user, base, extra, floor) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, 0);
                statement.setInt(2, user);
                statement.setInt(3, base);
                statement.setString(4, extra);
                statement.setString(5, type.equals("s") ? "1" : "0");

                statement.executeUpdate();

                try (ResultSet set = statement.getGeneratedKeys()) {
                    while(set.next()) {
                        id = set.getInt(1);
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(Items.class.getName()).info(ex.getMessage());
        }

        return id;
    }

    public THashMap<Integer, InventoryItem> getInventoryById(int id) {
        THashMap<Integer, InventoryItem> items = new THashMap<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_items WHERE user = ? AND room = ?")) {

                statement.setInt(1, id);
                statement.setInt(2, 0);

                try (ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        InventoryItem i = new InventoryItem();

                        i.setId(set.getInt("id"));
                        i.setBase(H4J.getHabboHotel().getFurnitureManager().getByID(set.getInt("base")));
                        i.setData(set.getString("extra"));
                        i.setOwner(null);

                        items.put(i.getId(), i);
                    }

                }
            }
        } catch (Exception ex) {
            H4J.getLogger(Items.class.getName()).info(ex.getMessage());
        }
        return items;
    }
}

package com.mmoscene.h4j.habbohotel.rooms;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.rooms.models.Model;
import gnu.trove.map.hash.THashMap;
import org.magicwerk.brownies.collections.GapList;

public class RoomManager {
    private THashMap<Integer, Room> living = new THashMap<>();
    private THashMap<String, Model> models = new THashMap<>();

    public RoomManager() {
        models = H4J.getDAO().getRoomDAO().getModels();

        H4J.getLogger(this.getClass().getName()).info(String.format("Loaded %d Room Models successfully!", models.size()));
    }

    public THashMap<Integer, Room> getLiving() {
        return living;
    }

    public GapList<Room> getLivingAsList() {
        GapList<Room> rooms = new GapList<>();

        for(Room r : living.values()) {
            rooms.add(r);
        }

        return rooms;
    }

    public boolean roomIsLiving(int room) {
        return living.containsKey(room);
    }

    public Room getRoom(int room) {
        return living.get(room);
    }

    public Model getModel(String name) {
        return models.get(name);
    }
}

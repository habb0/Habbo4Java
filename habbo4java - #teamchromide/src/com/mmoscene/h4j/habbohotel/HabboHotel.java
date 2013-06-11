package com.mmoscene.h4j.habbohotel;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.cache.CacheTask;
import com.mmoscene.h4j.habbohotel.catalog.CatalogManager;
import com.mmoscene.h4j.habbohotel.furniture.FurnitureManager;
import com.mmoscene.h4j.habbohotel.hotelview.HotelViewUser;
import com.mmoscene.h4j.habbohotel.hotelview.PromoPiece;
import com.mmoscene.h4j.habbohotel.pathfinding.Pathfinder;
import com.mmoscene.h4j.habbohotel.pathfinding.complex.ComplexPathfinder;
import com.mmoscene.h4j.habbohotel.pathfinding.simple.SimplePathfinder;
import com.mmoscene.h4j.habbohotel.rooms.RoomManager;
import com.mmoscene.h4j.habbohotel.rooms.RoomTask;
import org.magicwerk.brownies.collections.GapList;

import java.security.SecureRandom;

public class HabboHotel {

    private CatalogManager catalog_manager;
    private FurnitureManager furniture_manager;
    private RoomManager room_manager;

    private GapList<PromoPiece> promos = new GapList<>();
    private GapList<HotelViewUser> richest = new GapList<>();

    private CacheTask task;

    private Pathfinder pathfinder;

    private RoomTask room_task;

    public HabboHotel() {
        catalog_manager = new CatalogManager();
        furniture_manager = new FurnitureManager();
        room_manager = new RoomManager();

        promos = H4J.getDAO().getHotelViewDAO().getPromos();
        richest = H4J.getDAO().getHotelViewDAO().getRichestUsers();

        H4J.getLogger(HabboHotel.class.getName()).info("Loaded Habbo Hotel successfully!");

        task = new CacheTask();
        pathfinder = new SimplePathfinder();

        room_task = new RoomTask();
    }

    public CatalogManager getCatalogManager() {
        return catalog_manager;
    }

    public FurnitureManager getFurnitureManager() {
        return furniture_manager;
    }

    public RoomManager getRoomManager() {
        return room_manager;
    }

    public GapList<PromoPiece> getPromos() {
        return promos;
    }

    public GapList<HotelViewUser> getRichestUsers() {
        return richest;
    }

    public CacheTask getCacheTask() {
        return task;
    }

    public void reloadPromos() {
        promos = H4J.getDAO().getHotelViewDAO().getPromos();
    }

    public Pathfinder getPathfinder() {
        return pathfinder;
    }

    public GapList<String> generateUsernameSuggestions(String name) {
        GapList<String> suggestions = new GapList<>();

        suggestions.add(name + (new SecureRandom().nextInt(9999)));
        suggestions.add("TheBoss" + name);
        suggestions.add(name + "SupaFly");

        return suggestions;
    }
}

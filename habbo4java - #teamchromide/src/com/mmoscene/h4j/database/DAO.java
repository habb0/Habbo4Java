package com.mmoscene.h4j.database;

public class DAO {

    private User user_db_obj;
    private Catalog catalog_db_obj;
    private Furniture furniture_db_obj;
    private Messenger messenger_db_obj;
    private Navigator navigator_db_obj;
    private Room room_db_obj;
    private HotelView hotelview_db_obj;
    private Items items_db_obj;

    public DAO() {
        user_db_obj = new User();
        catalog_db_obj = new Catalog();
        furniture_db_obj = new Furniture();
        messenger_db_obj = new Messenger();
        navigator_db_obj = new Navigator();
        room_db_obj = new Room();
        hotelview_db_obj = new HotelView();
        items_db_obj = new Items();
    }

    public User getUserDAO() {
        return user_db_obj;
    }

    public Catalog getCatalogDAO() {
        return catalog_db_obj;
    }

    public Furniture getFurnitureDAO() {
        return furniture_db_obj;
    }

    public Messenger getMessengerDAO() {
        return messenger_db_obj;
    }

    public Navigator getNavigatorDAO() {
        return navigator_db_obj;
    }

    public Room getRoomDAO() {
        return room_db_obj;
    }

    public HotelView getHotelViewDAO() {
        return hotelview_db_obj;
    }

    public Items getItemsDAO() {
        return items_db_obj;
    }
}

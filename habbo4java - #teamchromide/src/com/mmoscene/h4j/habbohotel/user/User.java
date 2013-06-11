package com.mmoscene.h4j.habbohotel.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.messenger.MessengerManager;
import com.mmoscene.h4j.habbohotel.rooms.actors.RoomActor;
import com.mmoscene.h4j.habbohotel.user.badges.BadgeManager;
import com.mmoscene.h4j.habbohotel.user.items.InventoryManager;

public class User {
    private int id, credits, pixels, currency, rank, respect, daily_respect, home, name_changes, primary_guild;

    private String username, email, motto, look, client_key, last_active;

    private char gender;

    private MessengerManager messenger;

    private RoomActor actor;

    private InventoryManager inventory_manager;

    private BadgeManager badge_manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.setRoomActor(new RoomActor(id));
        this.setInventory(new InventoryManager(id));
        this.setBadgeManager(new BadgeManager(id));
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getPixels() {
        return pixels;
    }

    public void setPixels(int pixels) {
        this.pixels = pixels;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRespect() {
        return respect;
    }

    public void setRespect(int respect) {
        this.respect = respect;
    }

    public int getDailyRespect() {
        return daily_respect;
    }

    public void setDailyRespect(int daily_respect) {
        this.daily_respect = daily_respect;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getNameChanges() {
        return name_changes;
    }

    public void setNameChanges(int name_changes) {
        this.name_changes = name_changes;
    }

    public int getPrimaryGuild() {
        return primary_guild;
    }

    public void setPrimaryGuild(int primary_guild) {
        this.primary_guild = primary_guild;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public String getClientKey() {
        return client_key;
    }

    public void setClientKey(String client_key) {
        this.client_key = client_key;
    }

    public String getLastActive() {
        return last_active;
    }

    public void setLastActive(String last_active) {
        this.last_active = last_active;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void append() {
        H4J.getDAO().getUserDAO().append(this);
    }

    public void startMessenger() {
        messenger = new MessengerManager(this);
    }

    public MessengerManager getMessenger() {
        return messenger;
    }

    public RoomActor getRoomActor() {
        return actor;
    }

    public void setRoomActor(RoomActor actor) {
        this.actor = actor;
    }

    public InventoryManager getInventory() {
        return inventory_manager;
    }

    public void setInventory(InventoryManager inventory_manager) {
        this.inventory_manager = inventory_manager;
    }

    public BadgeManager getBadgeManager() {
        return badge_manager;
    }

    public void setBadgeManager(BadgeManager badge_manager) {
        this.badge_manager = badge_manager;
    }
}

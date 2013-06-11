package com.mmoscene.h4j.habbohotel.rooms;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.communication.composers.room.SendRoomActorStatusMessageComposer;
import com.mmoscene.h4j.communication.composers.room.SendRoomActorsMessageComposer;
import com.mmoscene.h4j.habbohotel.rooms.models.Model;
import com.mmoscene.h4j.network.sessions.Session;
import gnu.trove.map.hash.THashMap;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.magicwerk.brownies.collections.GapList;

public class Room {
    private int id, owner, state, guild;

    private String name, description, password, wallpaper, floor, landscape;

    private Model model;

    private THashMap<Integer, Session> party = new THashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getGuild() {
        return guild;
    }

    public void setGuild(int guild) {
        this.guild = guild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }

    public THashMap<Integer, Session> getParty() {
        return party;
    }

    public void addToParty(Session session) {
        party.put(session.getHabbo().getId(), session);
    }

    public void removeFromParty(int id) {
        party.remove(id);
    }

    public void removeFromParty(Session session) {
        party.remove(session.getHabbo().getId());
    }

    public void respond(Response response) {
        ChannelGroup group = new DefaultChannelGroup();

        for(Session session : party.values()) {
            group.add(session.getChannel());
        }

        group.write(response);
    }

    public void sendRoomActorStatus(Session session) {
        for(Session s : party.values()) {
            session.respond(SendRoomActorStatusMessageComposer.compose(
                    s.getHabbo().getId(), s.getHabbo().getRoomActor().getCurrentPosition(), s.getHabbo().getRoomActor().getStatus()));

            s.respond(SendRoomActorStatusMessageComposer.compose(
                    session.getHabbo().getId(), session.getHabbo().getRoomActor().getCurrentPosition(), session.getHabbo().getRoomActor().getStatus()));
        }
    }

    public Response sendRoomActorInformation() {
        GapList<Session> users = new GapList<>();

        for(Session s : party.values()) {
            users.add(s);
        }

        respond(SendRoomActorsMessageComposer.compose(users));

        return SendRoomActorsMessageComposer.compose(users);
    }

    public void onCycle() {
        for(Session s : party.values()) {
            s.getHabbo().getRoomActor().onCycle();
        }
    }
}

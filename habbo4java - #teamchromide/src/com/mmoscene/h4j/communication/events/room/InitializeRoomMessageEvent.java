package com.mmoscene.h4j.communication.events.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.room.SendRoomModelMessageComposer;
import com.mmoscene.h4j.communication.composers.room.SendRoomPapersMessageComposer;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import com.mmoscene.h4j.network.sessions.Session;

public class InitializeRoomMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        int id = request.readInt();

        Room room;

        if(H4J.getHabboHotel().getRoomManager().roomIsLiving(id)) {
            room = H4J.getHabboHotel().getRoomManager().getRoom(id);
        } else {
            room = H4J.getDAO().getRoomDAO().generate(id);
            H4J.getHabboHotel().getRoomManager().getLiving().put(id, room);
        }

        if (session.getHabbo().getRoomActor().getCurrentRoom() != null) {
            session.getHabbo().getRoomActor().getCurrentRoom().removeFromParty(session);
        }

        session.getHabbo().getRoomActor().setCurrentRoom(room);
        session.getHabbo().getRoomActor().getCurrentRoom().addToParty(session);
        session.getHabbo().getRoomActor().setCurrentPosition(room.getModel().getDoor());

        session.respond(SendRoomModelMessageComposer.compose(room.getModel().getName(), room.getId()));

        if (!room.getWallpaper().equals("0.0")) {
            session.respond(SendRoomPapersMessageComposer.compose("wallpaper", room.getWallpaper()));
        }

        if (!room.getFloor().equals("0.0")) {
            session.respond(SendRoomPapersMessageComposer.compose("floor", room.getFloor()));
        }

        session.respond(SendRoomPapersMessageComposer.compose("landscape", room.getLandscape()));

        if (session.getHabbo().getId() == room.getOwner() || session.getHabbo().getRank() >= 6) {
            session.getHabbo().getRoomActor().addStatus("flatctrl", "4 useradmin");
        } else {
            session.getHabbo().getRoomActor().addStatus("flatctrl", "0");
        }
    }
}

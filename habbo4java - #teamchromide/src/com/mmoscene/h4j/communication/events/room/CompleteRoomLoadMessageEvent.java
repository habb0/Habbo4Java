package com.mmoscene.h4j.communication.events.room;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.room.SendRoomActorInformationMessageComposer;
import com.mmoscene.h4j.communication.composers.room.SendRoomHeightmapMessageComposer;
import com.mmoscene.h4j.communication.composers.room.SendRoomInformationMessageComposer;
import com.mmoscene.h4j.communication.composers.room.SendRoomRelativeHeightmapMessageComposer;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import com.mmoscene.h4j.network.sessions.Session;

public class CompleteRoomLoadMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {

        Room room = session.getHabbo().getRoomActor().getCurrentRoom();

        if (room == null) {
            return;
        }

        session.respond(SendRoomHeightmapMessageComposer.compose(room.getModel().getHeightmap()));
        session.respond(SendRoomRelativeHeightmapMessageComposer.compose(room.getModel().getRelativeHeightmap()));

        session.respond(SendRoomActorInformationMessageComposer.compose(session.getHabbo().getId(), session.getHabbo().getUsername()));

        session.respond(room.sendRoomActorInformation());

        session.respond(SendRoomInformationMessageComposer.compose(room));

        room.sendRoomActorStatus(session);
    }
}

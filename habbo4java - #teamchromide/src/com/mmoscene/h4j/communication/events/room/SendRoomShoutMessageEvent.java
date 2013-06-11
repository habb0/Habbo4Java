package com.mmoscene.h4j.communication.events.room;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.room.SendRoomShoutMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class SendRoomShoutMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        String msg = request.readString();
        int color = request.readInt();

        session.getHabbo().getRoomActor().getCurrentRoom().respond(SendRoomShoutMessageComposer.compose(session.getHabbo().getId(), msg, 0, color));
    }
}

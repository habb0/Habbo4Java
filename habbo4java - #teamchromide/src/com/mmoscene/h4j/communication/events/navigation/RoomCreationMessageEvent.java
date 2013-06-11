package com.mmoscene.h4j.communication.events.navigation;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.room.SendUserToRoomMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class RoomCreationMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        String name = request.readString();
        String model = request.readString();

        int id = H4J.getDAO().getNavigatorDAO().createRoom(name, model, session.getHabbo().getId());

        //TODO: Send them to that room!
        session.respond(SendUserToRoomMessageComposer.compose(id, name));
    }
}

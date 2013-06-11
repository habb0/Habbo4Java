package com.mmoscene.h4j.communication.events.memenu;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;

public class SendRoomUserSignStatusMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        int id = request.readInt();

        session.getHabbo().getRoomActor().addStatus("sign", id);
        session.getHabbo().getRoomActor().sendRoomActorStatusMessageComposer();
        session.getHabbo().getRoomActor().setSignTime(5);
    }
}

package com.mmoscene.h4j.communication.events.room;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;

public class RoomActorWalkMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        int x = request.readInt();
        int y = request.readInt();

        session.getHabbo().getRoomActor().getGoalPosition().setX(x);
        session.getHabbo().getRoomActor().getGoalPosition().setY(y);
        session.getHabbo().getRoomActor().setMoving(true);
    }
}

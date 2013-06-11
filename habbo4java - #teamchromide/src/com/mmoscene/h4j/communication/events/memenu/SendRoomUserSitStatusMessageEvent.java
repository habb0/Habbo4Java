package com.mmoscene.h4j.communication.events.memenu;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;

public class SendRoomUserSitStatusMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        if (!session.getHabbo().getRoomActor().hasStatus("sit") && !session.getHabbo().getRoomActor().hasStatus("lay")) {

            if ((session.getHabbo().getRoomActor().getCurrentPosition().getRotation() % 2) != 0) {
                session.getHabbo().getRoomActor().getCurrentPosition().setRotation(
                        session.getHabbo().getRoomActor().getCurrentPosition().getRotation() + 1
                );
            }

            session.getHabbo().getRoomActor().addStatus("sit", session.getHabbo().getRoomActor().getCurrentPosition().getZ() + .65);
            session.getHabbo().getRoomActor().sendRoomActorStatusMessageComposer();
        }
    }
}

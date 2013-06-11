package com.mmoscene.h4j.communication.events.memenu;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.memenu.SendRoomUserActionStatusMessageComposer;
import com.mmoscene.h4j.communication.composers.memenu.SendRoomUserIdleStatusMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class SendRoomUserActionStatusMessageEvent implements GameEvent {

    @Override
    public void parse(Session session, Request request) {
        int id = request.readInt();

        if (id == 5) {
            session.getHabbo().getRoomActor().getCurrentRoom().respond(
                    SendRoomUserIdleStatusMessageComposer.compose(
                            session.getHabbo().getId(),
                            true
                    )
            );
        } else {
            session.getHabbo().getRoomActor().getCurrentRoom().respond(
                    SendRoomUserActionStatusMessageComposer.compose(
                            session.getHabbo().getId(),
                            id
                    )
            );
        }
    }
}

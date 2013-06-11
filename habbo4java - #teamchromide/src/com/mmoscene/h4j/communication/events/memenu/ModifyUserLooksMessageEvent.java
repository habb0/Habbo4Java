package com.mmoscene.h4j.communication.events.memenu;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.events.room.UpdateRoomUserInformationMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class ModifyUserLooksMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.getHabbo().setGender(request.readString().charAt(0));
        session.getHabbo().setLook(request.readString());

        session.getHabbo().getRoomActor().getCurrentRoom().respond(UpdateRoomUserInformationMessageComposer.compose(
                session.getHabbo().getId(),
                session.getHabbo().getMotto(),
                session.getHabbo().getLook(),
                session.getHabbo().getGender()
        ));

        session.getHabbo().getRoomActor().getCurrentRoom().respond(UpdateRoomUserInformationMessageComposer.compose(
                -1,
                session.getHabbo().getMotto(),
                session.getHabbo().getLook(),
                session.getHabbo().getGender()
        ));

        session.getHabbo().append();
    }
}

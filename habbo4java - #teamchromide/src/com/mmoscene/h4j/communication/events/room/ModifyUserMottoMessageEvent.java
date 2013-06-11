package com.mmoscene.h4j.communication.events.room;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;

public class ModifyUserMottoMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.getHabbo().setMotto(request.readString());

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

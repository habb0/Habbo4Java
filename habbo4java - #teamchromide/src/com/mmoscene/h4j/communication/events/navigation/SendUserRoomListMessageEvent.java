package com.mmoscene.h4j.communication.events.navigation;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.navigation.SendNavigatorResultsMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class SendUserRoomListMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(SendNavigatorResultsMessageComposer.compose(H4J.getDAO().getNavigatorDAO().getUserRooms(session.getHabbo().getId())));
    }
}

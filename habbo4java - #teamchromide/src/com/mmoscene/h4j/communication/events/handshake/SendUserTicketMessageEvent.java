package com.mmoscene.h4j.communication.events.handshake;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.handshake.SendAuthenicationOkayMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class SendUserTicketMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.setUser(request.readString());

        session.respond(SendAuthenicationOkayMessageComposer.compose());

        H4J.getLogger(SendUserTicketMessageEvent.class.getName()).info(session.getHabbo().getUsername() + " has connected!");
    }
}

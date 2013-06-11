package com.mmoscene.h4j.communication.events.room;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.room.SendNewTypingStatusMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class ShowSpeechBubbleStatusMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.getHabbo().getRoomActor().setIdle(false);

        session.respond(SendNewTypingStatusMessageComposer.compose(
                session.getHabbo().getId(),
                true
        ));
    }
}

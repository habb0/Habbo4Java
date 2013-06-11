package com.mmoscene.h4j.communication.events.navigation;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.navigation.SendRoomCreationCheckResultsMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class RoomCreationCheckMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(SendRoomCreationCheckResultsMessageComposer.compose());
    }
}

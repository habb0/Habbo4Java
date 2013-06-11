package com.mmoscene.h4j.communication.events.user;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.user.LoadUserRelationshipsMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class LoadUserRelationshipsMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(LoadUserRelationshipsMessageComposer.compose(session.getHabbo().getId()));
    }
}

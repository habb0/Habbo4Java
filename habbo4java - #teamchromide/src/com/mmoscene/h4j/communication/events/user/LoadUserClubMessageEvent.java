package com.mmoscene.h4j.communication.events.user;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.user.LoadUserClubMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

import java.util.Random;

public class LoadUserClubMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(LoadUserClubMessageComposer.compose(new Random().nextInt(100)));
    }
}

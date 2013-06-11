package com.mmoscene.h4j.communication.events.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.user.LoadUserProfileMessageComposer;
import com.mmoscene.h4j.habbohotel.user.User;
import com.mmoscene.h4j.network.sessions.Session;

public class LoadUserProfileMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        int id = request.readInt();

        User user = H4J.getDAO().getUserDAO().getUserById(id);

        session.respond(LoadUserProfileMessageComposer.compose(user, session.getHabbo().getMessenger().friendshipExists(id)));
    }
}

package com.mmoscene.h4j.communication.events.messenger;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.messenger.InitializeMessengerMessageComposer;
import com.mmoscene.h4j.communication.composers.messenger.SendPendingFriendRequestsMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class InitializeMessengerMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(InitializeMessengerMessageComposer.compose(session.getHabbo().getMessenger().getFriends()));
        session.respond(SendPendingFriendRequestsMessageComposer.compose(session.getHabbo().getMessenger().getRequests()));
        session.getHabbo().getMessenger().setUpdate(true);
    }
}

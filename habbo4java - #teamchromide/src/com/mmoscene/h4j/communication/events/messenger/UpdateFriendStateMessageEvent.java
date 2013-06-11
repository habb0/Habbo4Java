package com.mmoscene.h4j.communication.events.messenger;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;

public class UpdateFriendStateMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.getHabbo().getMessenger().setUpdate(true);
    }
}

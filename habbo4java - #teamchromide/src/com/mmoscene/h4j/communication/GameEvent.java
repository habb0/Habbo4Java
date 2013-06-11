package com.mmoscene.h4j.communication;

import com.mmoscene.h4j.network.sessions.Session;

public interface GameEvent {
    public void parse(Session session, Request request);
}

package com.mmoscene.h4j.communication.events.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.messenger.SendMessengerQueryResultsMessageComposer;
import com.mmoscene.h4j.habbohotel.messenger.Friend;
import com.mmoscene.h4j.network.sessions.Session;
import org.magicwerk.brownies.collections.GapList;

public class SendMessengerSearchMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        String query = request.readString();

        GapList<Friend> results = H4J.getDAO().getMessengerDAO().performSearch(query);

        session.respond(SendMessengerQueryResultsMessageComposer.compose(session.getHabbo().getMessenger().getFriends(), results));
    }
}

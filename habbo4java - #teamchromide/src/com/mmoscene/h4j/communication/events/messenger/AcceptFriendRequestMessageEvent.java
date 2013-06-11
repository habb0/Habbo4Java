package com.mmoscene.h4j.communication.events.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;

public class AcceptFriendRequestMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        int count = request.readInt();

        for (int i = 0; i < count; i++) {
            int user = request.readInt();

            H4J.getDAO().getMessengerDAO().acceptFriendship(user, session.getHabbo().getId());

            if(H4J.getNetwork().getSessionManager().getOnlineStatusById(user)) {
                Session sender = H4J.getNetwork().getSessionManager().getSessionById(user);

                sender.getHabbo().getMessenger().refreshFriends();
            }
        }

        session.getHabbo().getMessenger().refreshFriends();
    }
}

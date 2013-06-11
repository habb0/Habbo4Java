package com.mmoscene.h4j.communication.events.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.network.sessions.Session;

public class SendFriendRequestMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        String username = request.readString();

        int user_id = H4J.getDAO().getUserDAO().getIdByUsername(username);

        if (user_id != 0) {
            H4J.getDAO().getMessengerDAO().requestFriendship(session.getHabbo().getId(), user_id);

            if (H4J.getNetwork().getSessionManager().getOnlineStatusById(user_id)) {
                Session receiver = H4J.getNetwork().getSessionManager().getSessionById(user_id);

                receiver.getHabbo().getMessenger().refreshRequests();
            }
        }
    }
}

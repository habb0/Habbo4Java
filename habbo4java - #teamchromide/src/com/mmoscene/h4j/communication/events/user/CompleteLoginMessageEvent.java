package com.mmoscene.h4j.communication.events.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.user.SendNotificationMessageComposer;
import com.mmoscene.h4j.communication.composers.user.SendUserFuseRankMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CompleteLoginMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(SendUserFuseRankMessageComposer.compose(session.getHabbo().getRank()));

        session.respond(SendNotificationMessageComposer.compose(
                "Hello " + session.getHabbo().getUsername() + "!\n" +
                        "Welcome to ChromideHotel Closed BETA! \n" +
                        "Follow us @TeamChromide \n\n" +
                        "Updates\n" +
                        "------------------------------------\n" +
                        "--- Room Models are bugged \n" +
                        "--- Pathfinder is unfinished \n\n" +
                        "                                                    -TeamChromide"
        ));

        session.getHabbo().setLastActive(new SimpleDateFormat("M-d-yyyy").format(new Date()));

        session.getHabbo().append();

        H4J.getDAO().getUserDAO().updateOnline();
    }
}

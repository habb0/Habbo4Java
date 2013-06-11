package com.mmoscene.h4j.communication.events.memenu;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.memenu.ValidateNewUsernameMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;
import org.magicwerk.brownies.collections.GapList;

public class ValidateNewUsernameMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        String name = request.readString();

        int error = 0;

        if (name.length() >= 32) {
            error = 1;
        }

        if (name.length() <= 2) {
            error = 2;
        }

        if (H4J.getDAO().getUserDAO().usernameExists(name)) {
            error = 5;
        }

        if (session.getHabbo().getUsername().equals(name)) {
            error = 0;
        }

        GapList<String> suggestions = new GapList<>();

        if (error > 0) {
            suggestions = H4J.getHabboHotel().generateUsernameSuggestions(name);
        }

        session.respond(
                ValidateNewUsernameMessageComposer.compose(
                        error,
                        name,
                        suggestions
                )
        );
    }
}

package com.mmoscene.h4j.communication.events.navigation;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.navigation.SendNavigatorResultsMessageComposer;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import com.mmoscene.h4j.network.sessions.Session;
import org.magicwerk.brownies.collections.GapList;

public class CompleteNavigatorSearchMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        String query = request.readString();

        GapList<Room> rooms;

        if (query.contains(":")) {
            String val = query.split(":")[1];

            if (query.split(":")[0].equals("owner")) {
                rooms = H4J.getDAO().getNavigatorDAO().performSearchByOwner(H4J.getDAO().getUserDAO().getIdByUsername(val));
            } else {
                rooms = H4J.getDAO().getNavigatorDAO().performSearch(val);
            }
        } else {
            rooms = H4J.getDAO().getNavigatorDAO().performSearch(query);
        }

        session.respond(SendNavigatorResultsMessageComposer.compose(rooms));
    }
}

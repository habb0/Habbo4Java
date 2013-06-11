package com.mmoscene.h4j.communication.events.navigation;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.navigation.SendNavigatorResultsMessageComposer;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import com.mmoscene.h4j.network.sessions.Session;
import org.magicwerk.brownies.collections.GapList;

public class SendRecentlyVisitedRoomListMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(SendNavigatorResultsMessageComposer.compose(new GapList<Room>()));
    }
}

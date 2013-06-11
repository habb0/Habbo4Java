package com.mmoscene.h4j.communication.events.navigation;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.navigation.SendNavigatorResultsMessageComposer;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import com.mmoscene.h4j.network.sessions.Session;
import org.magicwerk.brownies.collections.GapList;

public class SendPopulatedRoomsMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(SendNavigatorResultsMessageComposer.compose(H4J.getHabboHotel().getRoomManager().getLivingAsList()));
    }
}

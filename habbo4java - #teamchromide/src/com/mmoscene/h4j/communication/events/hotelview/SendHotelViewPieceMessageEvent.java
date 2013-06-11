package com.mmoscene.h4j.communication.events.hotelview;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.hotelview.SendHotelViewPieceMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class SendHotelViewPieceMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        if (request.body().length() <= 6) {
            session.respond(SendHotelViewPieceMessageComposer.compose("", ""));
        } else {
            String str = request.readString();

            String[] vals = str.split(",");

            session.respond(SendHotelViewPieceMessageComposer.compose(str, ""));
        }
    }
}

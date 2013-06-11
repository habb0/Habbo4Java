package com.mmoscene.h4j.communication.events.hotelview;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.hotelview.LoadPromotionalNewsMessageComposer;
import com.mmoscene.h4j.habbohotel.hotelview.PromoPiece;
import com.mmoscene.h4j.network.sessions.Session;
import org.magicwerk.brownies.collections.GapList;

public class LoadPromotionalNewsMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        GapList<PromoPiece> pieces = H4J.getHabboHotel().getPromos();

        session.respond(LoadPromotionalNewsMessageComposer.compose(pieces));
    }
}

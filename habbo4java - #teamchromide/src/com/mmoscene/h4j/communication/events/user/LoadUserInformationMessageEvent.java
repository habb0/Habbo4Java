package com.mmoscene.h4j.communication.events.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.hotelview.SendHotelViewLooksMessageComposer;
import com.mmoscene.h4j.communication.composers.hotelview.SendWelcomeMessageMessageComposer;
import com.mmoscene.h4j.communication.composers.user.LoadUserCreditsMessageComposer;
import com.mmoscene.h4j.communication.composers.user.LoadUserCurrencyMessageComposer;
import com.mmoscene.h4j.communication.composers.user.LoadUserInformationMessageComposer;
import com.mmoscene.h4j.communication.composers.user.LoadUserRelationshipsMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class LoadUserInformationMessageEvent implements GameEvent {

    @Override
    public void parse(Session session, Request request) {
        session.respond(LoadUserCreditsMessageComposer.compose(session.getHabbo().getCredits()));
        session.respond(LoadUserCurrencyMessageComposer.compose(session.getHabbo().getCurrency()));
        session.respond(LoadUserInformationMessageComposer.compose(session));
        session.respond(SendWelcomeMessageMessageComposer.compose());
        session.respond(SendHotelViewLooksMessageComposer.compose("badgemuseum1", H4J.getHabboHotel().getRichestUsers()));
    }
}

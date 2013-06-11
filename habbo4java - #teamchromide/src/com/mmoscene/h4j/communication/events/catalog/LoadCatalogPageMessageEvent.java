package com.mmoscene.h4j.communication.events.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.catalog.LoadCatalogPageMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class LoadCatalogPageMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        int page = request.readInt();

        session.respond(LoadCatalogPageMessageComposer.compose(
                H4J.getHabboHotel().getCatalogManager().getPageById(page),
                H4J.getHabboHotel().getCatalogManager().getItemsForPage(page)));
    }
}

package com.mmoscene.h4j.communication.events.inventory;

import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.inventory.SendBadgeInventoryMessageComposer;
import com.mmoscene.h4j.communication.composers.inventory.SendInventoryItemsMessageComposer;
import com.mmoscene.h4j.network.sessions.Session;

public class SendInventoryItemsMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        session.respond(SendInventoryItemsMessageComposer.compose(session.getHabbo().getInventory().getItems()));
        //session.respond(SendNotificationMessageComposer.compose("Inventory is disabled at the moment!"));
        session.respond(SendBadgeInventoryMessageComposer.compose(
                session.getHabbo().getBadgeManager().getBadges(),
                session.getHabbo().getBadgeManager().getEquippedBadges()
        ));
    }
}

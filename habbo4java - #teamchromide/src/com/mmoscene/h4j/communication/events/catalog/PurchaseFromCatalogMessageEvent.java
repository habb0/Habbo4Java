package com.mmoscene.h4j.communication.events.catalog;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.GameEvent;
import com.mmoscene.h4j.communication.Request;
import com.mmoscene.h4j.communication.composers.catalog.PurchaseFromCatalogMessageComposer;
import com.mmoscene.h4j.communication.composers.user.LoadUserCreditsMessageComposer;
import com.mmoscene.h4j.communication.composers.user.LoadUserCurrencyMessageComposer;
import com.mmoscene.h4j.communication.composers.user.SendNotificationMessageComposer;
import com.mmoscene.h4j.habbohotel.catalog.CatalogItem;
import com.mmoscene.h4j.network.sessions.Session;

public class PurchaseFromCatalogMessageEvent implements GameEvent {
    @Override
    public void parse(Session session, Request request) {
        int page = request.readInt();
        int id = request.readInt();
        String data = request.readString();
        int amount = request.readInt();
        boolean error = false;

        int discount = 0;

        CatalogItem item = H4J.getHabboHotel().getCatalogManager().getItemById(id);

        if (amount % 6 == 0 && amount != 40 && amount != 99) { //divisible by 6, not an exception
            discount = (item.getCost() * (amount / 6));

            if (amount > 6) {
                discount += (item.getCost() * ((amount / 6) - 1));
            }
        }

        int cost = (item.getCost() * amount) - discount;
        int currency_cost = 0;

        if (item.getCurrencyCost() > 0) { //lets do discount for that too...
            if (amount % 6 == 0 && amount != 40 && amount != 99) { //divisible by 6, not an exception
                discount = (item.getCurrencyCost() * (amount / 6));
            }

            if (amount > 6) {
                discount += (item.getCurrencyCost() * ((amount / 6) - 1));
            }

            currency_cost = (item.getCurrencyCost() * amount) - discount;
        }

        if (session.getHabbo().getCredits() < cost) {
            session.respond(SendNotificationMessageComposer.compose("You don't have enough credits for this transaction!"));
            error = true;
        }

        if (session.getHabbo().getCurrency() < currency_cost) {
            session.respond(SendNotificationMessageComposer.compose("You don't have enough currency for this transaction!"));
            error = true;
        }

        session.respond(PurchaseFromCatalogMessageComposer.compose(item));

        if (!error) {
            session.getHabbo().setCredits(session.getHabbo().getCredits() - cost);
            session.respond(LoadUserCreditsMessageComposer.compose(session.getHabbo().getCredits()));

            if (item.getCurrencyCost() > 0) {
                session.getHabbo().setCurrency(session.getHabbo().getCurrency() - currency_cost);
                session.respond(LoadUserCurrencyMessageComposer.compose(session.getHabbo().getCurrency()));
            }

            for (int i = 0; i < amount; i++) {
                H4J.getDAO().getItemsDAO().completePurchase(
                        session.getHabbo().getId(),
                        item.getBaseId(),
                        data,
                        item.getBase().getType()
                );
            }

            session.getHabbo().append();
            session.getHabbo().getInventory().refresh();
        }
    }
}

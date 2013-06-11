package com.mmoscene.h4j.communication.composers.inventory;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.user.badges.BadgeObject;
import org.magicwerk.brownies.collections.GapList;

public class SendBadgeInventoryMessageComposer {
    public static Response compose(GapList<BadgeObject> badges, GapList<BadgeObject> equipped) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendBadgeInventoryMessageComposer"));

        response.addInt(badges.size());

        for(BadgeObject b : badges) {
            response.addInt(0);
            response.addString(b.getCode());
        }

        response.addInt(equipped.size());

        for (BadgeObject b : equipped) {
            response.addInt(b.getSlot());
            response.addString(b.getCode());
        }

        return response;
    }
}

package com.mmoscene.h4j.communication.composers.inventory;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.user.items.InventoryItem;
import org.magicwerk.brownies.collections.GapList;

public class SendInventoryItemsMessageComposer {
    public static Response compose(GapList<InventoryItem> items) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendInventoryItemsMessageComposer"));
        response.addInt(1);
        response.addInt(1);
        response.addInt(items.size());

        for (InventoryItem i : items) {
            response.addInt(i.getId());
            response.addString(i.getBase().getType().toUpperCase());
            response.addInt(i.getId());
            response.addInt(i.getBase().getSprite());

            if (i.getBase().getType().equals("i")) {
                response.addInt(1);
                response.addInt(0);

                response.addString(i.getData());

                response.addBool(i.getBase().isRecyclable());
                response.addBool(i.getBase().isTradeable());
                response.addBool(true);
                response.addBool(i.getBase().isSellable());
                response.addInt(-1);
                response.addBool(true);
                response.addInt(-1);
            } else {
                response.addInt(1);
                response.addString("");
                response.addInt(0);

                response.addBool(i.getBase().isRecyclable());
                response.addBool(i.getBase().isTradeable());
                response.addBool(true);
                response.addBool(i.getBase().isSellable());
                response.addInt(-1);
                response.addBool(true);
                response.addInt(-1);

                response.addString("");
                response.addInt(0);
            }

        }

        return response;
    }
}

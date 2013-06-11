package com.mmoscene.h4j.communication.composers.hotelview;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.hotelview.PromoPiece;
import org.magicwerk.brownies.collections.GapList;

public class LoadPromotionalNewsMessageComposer {
    public static Response compose(GapList<PromoPiece> pieces) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("LoadPromotionalNewsMessageComposer"));

        response.addInt(pieces.size());

        for(PromoPiece p : pieces) {
            response.addInt(p.getId());

            response.addString(p.getName());
            response.addString(p.getStory());
            response.addString(p.getButtonText());
            response.addInt(0);
            response.addString(p.getExternalURL());
            response.addString(p.getImage());
        }
        return response;
    }
}

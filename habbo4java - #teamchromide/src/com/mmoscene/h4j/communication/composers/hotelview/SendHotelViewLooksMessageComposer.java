package com.mmoscene.h4j.communication.composers.hotelview;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.hotelview.HotelViewUser;
import org.magicwerk.brownies.collections.GapList;

public class SendHotelViewLooksMessageComposer {
    public static Response compose(String key, GapList<HotelViewUser> users) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendHotelViewLooksMessageComposer"));
        response.addString(key);

        response.addInt(users.size());

        for(HotelViewUser u : users) {
            response.addInt(u.getId());
            response.addString(u.getUsername());
            response.addString(u.getLook());
            response.addInt(1);
            response.addInt(u.getValue());
        }
        return response;
    }
}

package com.mmoscene.h4j.communication.composers.hotelview;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendHotelViewPieceMessageComposer {
    public static Response compose(String key, String value) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendHotelViewPieceMessageComposer"));
        response.addString(key);
        response.addString(value);

        return response;
    }
}

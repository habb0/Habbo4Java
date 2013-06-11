package com.mmoscene.h4j.communication.composers.hotelview;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendWelcomeMessageMessageComposer {
    public static Response compose() {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendWelcomeMessageMessageComposer"));
        response.addInt(0);

        return response;
    }
}

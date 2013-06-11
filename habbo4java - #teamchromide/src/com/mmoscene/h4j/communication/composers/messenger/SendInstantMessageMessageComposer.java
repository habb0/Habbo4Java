package com.mmoscene.h4j.communication.composers.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendInstantMessageMessageComposer {
    public static Response compose(int user, String msg) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendInstantMessageMessageComposer"));
        response.addInt(user);
        response.addString(msg);
        response.addInt(0);

        return response;
    }
}

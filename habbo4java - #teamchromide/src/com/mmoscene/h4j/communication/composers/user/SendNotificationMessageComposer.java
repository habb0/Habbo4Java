package com.mmoscene.h4j.communication.composers.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendNotificationMessageComposer {
    public static Response compose(String msg) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendNotificationMessageComposer"));
        response.addString(msg);
        response.addString("");

        return response;
    }
}

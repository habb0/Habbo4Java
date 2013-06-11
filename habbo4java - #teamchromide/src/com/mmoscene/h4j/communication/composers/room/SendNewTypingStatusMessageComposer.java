package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendNewTypingStatusMessageComposer {
    public static Response compose(int id, boolean toggle) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendNewTypingStatusMessageComposer"));
        response.addInt(id);
        response.addInt(toggle ? 1 : 0);

        return response;
    }
}

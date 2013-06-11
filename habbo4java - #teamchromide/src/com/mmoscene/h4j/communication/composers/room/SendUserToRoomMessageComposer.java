package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendUserToRoomMessageComposer {
    public static Response compose(int id, String name) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendUserToRoomMessageComposer"));
        response.addInt(id);
        response.addString(name);

        return response;
    }
}

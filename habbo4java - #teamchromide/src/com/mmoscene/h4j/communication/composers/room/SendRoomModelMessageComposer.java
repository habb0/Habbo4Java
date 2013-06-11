package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomModelMessageComposer {
    public static Response compose(String name, int room) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomModelMessageComposer"));
        response.addString(name);
        response.addInt(room);

        return response;
    }
}

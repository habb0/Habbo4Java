package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomSayMessageComposer {
    public static Response compose(int user, String msg, int smile, int color) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomSayMessageComposer"));
        response.addInt(user);
        response.addString(msg);
        response.addInt(smile);
        response.addInt(color);
        response.addInt(0);
        response.addInt(-1);

        return response;
    }
}

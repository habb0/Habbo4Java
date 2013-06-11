package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomActorInformationMessageComposer {
    public static Response compose(int id, String username) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomActorInformationMessageComposer"));
        response.addInt(1);
        response.addInt(id);
        response.addString(username);
        response.addInt(0);

        return response;
    }
}

package com.mmoscene.h4j.communication.composers.memenu;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomUserActionStatusMessageComposer {
    public static Response compose(int user, int id) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomUserActionStatusMessageComposer"));
        response.addInt(user);
        response.addInt(id);

        return response;
    }
}

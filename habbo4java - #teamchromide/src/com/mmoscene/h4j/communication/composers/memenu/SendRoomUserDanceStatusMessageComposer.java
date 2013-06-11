package com.mmoscene.h4j.communication.composers.memenu;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomUserDanceStatusMessageComposer {
    public static Response compose(int user, int dance) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomUserDanceStatusMessageComposer"));
        response.addInt(user);
        response.addInt(dance);

        return response;
    }
}

package com.mmoscene.h4j.communication.composers.memenu;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomUserIdleStatusMessageComposer {
    public static Response compose(int user, boolean is_idle) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomUserIdleStatusMessageComposer"));
        response.addInt(user);
        response.addBool(is_idle);

        return response;
    }
}

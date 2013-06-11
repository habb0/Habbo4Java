package com.mmoscene.h4j.communication.composers.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.user.User;

public class UpdateFriendStateMessageComposer {
    public static Response compose(boolean online, User user) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("UpdateFriendStateMessageComposer"));
        response.addInt(0);
        response.addInt(1);
        response.addInt(0);
        response.addInt(user.getId());
        response.addString(user.getUsername());
        response.addInt(1);
        response.addBool(online);
        response.addBool(false);
        response.addString(user.getLook());
        response.addInt(0);
        response.addString(user.getMotto());
        response.addInt(0);
        response.addInt(0);
        response.addInt(0);

        return response;
    }
}

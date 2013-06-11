package com.mmoscene.h4j.communication.events.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class UpdateRoomUserInformationMessageComposer {
    public static Response compose(int id, String motto, String look, char gender) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("UpdateRoomUserInformationMessageComposer"));
        response.addInt(id);
        response.addString(look);
        response.addString(gender);
        response.addString(motto);
        response.addInt(0);

        return response;
    }
}

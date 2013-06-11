package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomRelativeHeightmapMessageComposer {
    public static Response compose(String map) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomRelativeHeightmapMessageComposer"));
        response.addString(map);

        return response;
    }
}

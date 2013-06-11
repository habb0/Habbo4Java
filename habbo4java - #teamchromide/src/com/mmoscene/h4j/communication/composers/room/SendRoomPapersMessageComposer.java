package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomPapersMessageComposer {
    public static Response compose(String type, String value) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomPapersMessageComposer"));
        response.addString(type);
        response.addString(value);

        return response;
    }
}

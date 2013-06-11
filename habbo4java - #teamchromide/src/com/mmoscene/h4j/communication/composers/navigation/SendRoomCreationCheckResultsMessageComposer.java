package com.mmoscene.h4j.communication.composers.navigation;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendRoomCreationCheckResultsMessageComposer {
    public static Response compose() {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomCreationCheckResultsMessageComposer"));
        response.addInt(0);
        response.addInt(25);

        return response;
    }
}

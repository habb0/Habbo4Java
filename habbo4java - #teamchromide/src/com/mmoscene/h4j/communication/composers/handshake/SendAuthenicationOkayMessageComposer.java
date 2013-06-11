package com.mmoscene.h4j.communication.composers.handshake;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendAuthenicationOkayMessageComposer {
    public static Response compose() {
        Response response = new Response();

        response.init(new Integer(H4J.getHeaders().get("SendAuthenicationOkayMessageComposer")));

        return response;
    }
}

package com.mmoscene.h4j.communication.composers.handshake;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendSecretKeyMessageComposer {
    public static Response compose() {
        Response response = new Response();

        response.init(new Integer(H4J.getHeaders().get("SendSecretKeyMessageComposer")));
        response.addString("24231219992253632572058933470468103090824667747608911151318774416044820318109");
        return response;
    }
}

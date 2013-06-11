package com.mmoscene.h4j.communication.composers.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendUserFuseRankMessageComposer {
    public static Response compose(int rank) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendUserFuseRankMessageComposer"));
        response.addInt(2); // 0 = normal, 1 = hc, 2 = vip
        response.addInt(rank);

        return response;
    }
}

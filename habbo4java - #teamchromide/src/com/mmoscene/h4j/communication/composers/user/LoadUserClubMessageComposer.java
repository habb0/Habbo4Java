package com.mmoscene.h4j.communication.composers.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class LoadUserClubMessageComposer {
    public static Response compose(int days) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("LoadUserClubMessageComposer"));
        response.addString("club_habbo");
        response.addInt(days);
        response.addInt(3);
        response.addInt(0);
        response.addInt(1);
        response.addBool(true);
        response.addBool(true);
        response.addInt(0);
        response.addInt(0);
        response.addInt(28022);

        return response;
    }
}

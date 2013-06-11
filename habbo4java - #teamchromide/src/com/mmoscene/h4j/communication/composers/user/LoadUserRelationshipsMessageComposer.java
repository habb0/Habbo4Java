package com.mmoscene.h4j.communication.composers.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class LoadUserRelationshipsMessageComposer {
    public static Response compose(int id) { //TODO: Do real relationships.. may just keep em out!
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("LoadUserRelationshipsMessageComposer"));

        response.addInt(id);
        response.addInt(0); //count

        //iterate through..
        //response.addInt(type);
        //response.addInt(int);
        //response.addInt(id);
        //response.addInt(username);
        //response.addInt(look);

        return response;
    }
}

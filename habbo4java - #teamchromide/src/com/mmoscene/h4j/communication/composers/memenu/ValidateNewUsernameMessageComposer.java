package com.mmoscene.h4j.communication.composers.memenu;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import org.magicwerk.brownies.collections.GapList;

public class ValidateNewUsernameMessageComposer {
    public static Response compose(int error, String username, GapList<String> suggestions) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("ValidateNewUsernameMessageComposer"));
        response.addInt(error);
        response.addString(username);

        if (error > 0) {
            response.addInt(suggestions.size());

            for(String s : suggestions) {
                response.addString(s);
            }
        } else {
            response.addInt(0);
        }

        return response;
    }
}

package com.mmoscene.h4j.communication.composers.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;

public class SendPermissionsMessageComposer {
    public static Response compose() {
        Response response = new Response();

        //NOTE: This is all static, too lazy to do them properly (y)

        response.init(H4J.getHeaders().getInt("SendPermissionsMessageComposer"));
        response.addInt(7); //PASSED = 7 ELSE = 5
        response.addString("VOTE_IN_COMPETITIONS");
        response.addBool(true);
        response.addString("");
        response.addString("TRADE");
        response.addBool(true);
        response.addString("");
        response.addString("CITIZEN");
        response.addBool(true);
        response.addString("");

        //if (HasBadge) {
            response.addString("SAFE_CHAT");
            response.addBool(true);
            response.addString("");
            response.addString("FULL_CHAT");
            response.addBool(true);
            response.addString("");
        //}

        response.addString("CALL_ON_HELPERS");
        response.addBool(true);
        response.addString("");
        response.addString("USE_GUIDE_TOOL");
        response.addBool(false);
        response.addString("requirement.unfulfilled.helper_level_4");
        response.addString("JUDGE_CHAT_REVIEWS");
        response.addBool(false);
        response.addString("requirement.unfulfilled.helper_level_6");
        
        return response;
    }
}

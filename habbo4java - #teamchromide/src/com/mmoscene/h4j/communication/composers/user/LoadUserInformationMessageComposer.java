package com.mmoscene.h4j.communication.composers.user;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.network.sessions.Session;

public class LoadUserInformationMessageComposer {
    public static Response compose(Session session) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("LoadUserInformationMessageComposer"));
        response.addInt(session.getHabbo().getId());
        response.addString(session.getHabbo().getUsername());
        response.addString(session.getHabbo().getLook());
        response.addString(session.getHabbo().getGender());
        response.addString(session.getHabbo().getMotto());
        response.addString(session.getHabbo().getUsername().toLowerCase());
        response.addBool(false);
        response.addInt(session.getHabbo().getRespect());
        response.addInt(session.getHabbo().getDailyRespect());
        response.addInt(session.getHabbo().getDailyRespect());
        response.addBool(true);
        response.addString(session.getHabbo().getLastActive());
        response.addBool((session.getHabbo().getNameChanges() > 0));  //Can change name
        response.addBool(false); //Cannot purchase from catalog


        return response;
    }
}

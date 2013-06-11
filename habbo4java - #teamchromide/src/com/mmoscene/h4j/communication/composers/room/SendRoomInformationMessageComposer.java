package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.rooms.Room;

public class SendRoomInformationMessageComposer {
    public static Response compose(Room r) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomInformationMessageComposer"));

        response.addBool(true);
        response.addInt(r.getId());
        response.addString(r.getName());
        response.addBool(true);
        response.addInt(r.getOwner());
        response.addString(H4J.getDAO().getUserDAO().getUsernameById(r.getOwner()));
        response.addInt(0);
        response.addInt(r.getParty().size());
        response.addInt(50);
        response.addString(r.getDescription());
        response.addInt(0);
        response.addInt(0);
        response.addInt(10); // score
        response.addInt(0);
        response.addInt(0); // category
        response.addInt(0); //group id = 0
        response.addInt(0); //group id = 0
        response.addString("");
        response.addInt(0); // tag count
        response.addInt(0);
        response.addInt(0);
        response.addInt(0);
        response.addBool(true);
        response.addBool(true);
        response.addInt(0);
        response.addInt(0);
        response.addBool(false);
        response.addBool(false);
        response.addBool(false);
        response.addInt(0);
        response.addInt(0);
        response.addInt(0);

        response.addInt(1);
        response.addInt(0);
        response.addInt(0);

        return response;
    }
}

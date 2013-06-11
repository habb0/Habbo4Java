package com.mmoscene.h4j.communication.composers.navigation;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import org.magicwerk.brownies.collections.GapList;

public class SendNavigatorResultsMessageComposer {
    public static Response compose(GapList<Room> rooms) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendNavigatorResultsMessageComposer"));
        response.addInt(5);
        response.addString("");
        response.addInt(rooms.size());

        for(Room r : rooms) {
            response.addInt(r.getId());
            response.addString(r.getName());
            response.addBool(true); //?
            response.addInt(r.getOwner());
            response.addString(H4J.getDAO().getUserDAO().getUsernameById(r.getOwner()));
            response.addInt(r.getState());
            response.addInt(r.getParty().size()); //current users
            response.addInt(50);
            response.addString(r.getDescription());
            response.addInt(0);
            response.addInt(0);
            response.addInt(0);
            response.addInt(0);
            response.addInt(0);
            response.addInt(0);
            response.addInt(0);
            response.addString("");

            response.addInt(0); //tag count

            response.addInt(0);
            response.addInt(0);
            response.addInt(0);
            response.addBool(true);
            response.addBool(true);
            response.addInt(0);
            response.addInt(0);
        }

        response.addInt(0);
        response.addInt(0);
        response.addBool(false);

        return response;
    }
}

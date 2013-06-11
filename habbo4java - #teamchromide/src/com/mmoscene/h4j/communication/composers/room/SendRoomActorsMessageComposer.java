package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.network.sessions.Session;
import org.magicwerk.brownies.collections.GapList;

public class SendRoomActorsMessageComposer {
    public static Response compose(GapList<Session> users) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomActorsMessageComposer"));
        response.addInt(users.size());

        for(Session s : users) {
            response.addInt(s.getHabbo().getId());
            response.addString(s.getHabbo().getUsername());
            response.addString(s.getHabbo().getMotto());
            response.addString(s.getHabbo().getLook());
            response.addInt(s.getHabbo().getId());
            response.addInt(s.getHabbo().getRoomActor().getCurrentPosition().getX());
            response.addInt(s.getHabbo().getRoomActor().getCurrentPosition().getY());
            response.addString(Double.toString(s.getHabbo().getRoomActor().getCurrentPosition().getZ()));
            response.addInt(s.getHabbo().getRoomActor().getCurrentPosition().getRotation());
            response.addInt(1);
            response.addString(s.getHabbo().getGender());

            //TODO: Guilds
            response.addInt(-1);
            response.addInt(-1);
            response.addInt(0);
            response.addInt(0);
        }

        response.addInt(1);

        return response;
    }
}

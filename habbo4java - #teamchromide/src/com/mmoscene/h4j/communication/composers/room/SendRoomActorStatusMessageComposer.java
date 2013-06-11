package com.mmoscene.h4j.communication.composers.room;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.pathfinding.Position;

public class SendRoomActorStatusMessageComposer {
    public static Response compose(int user, Position p, String status) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendRoomActorStatusMessageComposer"));
        response.addInt(1);
        response.addInt(user);
        response.addInt(p.getX());
        response.addInt(p.getY());
        response.addString(Double.toString(p.getZ()));
        response.addInt(p.getRotation());
        response.addInt(p.getRotation());
        response.addString(status);

        return response;
    }
}

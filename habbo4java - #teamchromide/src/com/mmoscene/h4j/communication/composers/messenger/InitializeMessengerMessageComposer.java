package com.mmoscene.h4j.communication.composers.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.messenger.Friend;
import gnu.trove.map.hash.THashMap;

public class InitializeMessengerMessageComposer {
    public static Response compose(THashMap<Integer, Friend> friends) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("InitializeMessengerMessageComposer"));
        response.addInt(1100);
        response.addInt(300);
        response.addInt(800);
        response.addInt(1100);
        response.addInt(0);
        response.addInt(friends.size());

        for(Friend friend : friends.values()) {
            response.addInt(friend.getId());
            response.addString(friend.getUsername());
            response.addInt(1); //?
            response.addBool(H4J.getNetwork().getSessionManager().getOnlineStatusById(friend.getId()));
            response.addBool(false); //if in room
            response.addString(friend.getLook());
            response.addInt(0); //?
            response.addString(friend.getMotto());
            response.addString(""); //last online
            response.addString(""); //?
            response.addBool(true);
            response.addBool(true);
            response.addBool(false);
            response.addShort(0);
        }
        return response;
    }
}

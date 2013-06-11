package com.mmoscene.h4j.communication.composers.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.messenger.Friend;
import gnu.trove.map.hash.THashMap;
import org.magicwerk.brownies.collections.GapList;

public class SendMessengerQueryResultsMessageComposer {
    public static Response compose(THashMap<Integer, Friend> friends, GapList<Friend> results) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendMessengerQueryResultsMessageComposer"));
        response.addInt(friends.size());

        for(Friend friend : friends.values()) {
            response.addInt(friend.getId());
            response.addString(friend.getUsername());
            response.addString(friend.getMotto());
            response.addBool(H4J.getNetwork().getSessionManager().getOnlineStatusById(friend.getId()));
            response.addBool(false);
            response.addString("");
            response.addInt(0);
            response.addString(friend.getLook());
            response.addString("");
        }

        response.addInt(results.size());

        for(Friend friend : results) {
            response.addInt(friend.getId());
            response.addString(friend.getUsername());
            response.addString(friend.getMotto());
            response.addBool(H4J.getNetwork().getSessionManager().getOnlineStatusById(friend.getId()));
            response.addBool(false);
            response.addString("");
            response.addInt(0);
            response.addString(friend.getLook());
            response.addString("");
        }
        return response;
    }
}

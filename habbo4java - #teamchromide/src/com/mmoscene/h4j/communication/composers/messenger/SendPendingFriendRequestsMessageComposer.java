package com.mmoscene.h4j.communication.composers.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.Response;
import com.mmoscene.h4j.habbohotel.messenger.Friend;
import gnu.trove.map.hash.THashMap;

public class SendPendingFriendRequestsMessageComposer {
    public static Response compose(THashMap<Integer, Friend> requests) {
        Response response = new Response();

        response.init(H4J.getHeaders().getInt("SendPendingFriendRequestsMessageComposer"));
        response.addInt(requests.size());
        response.addInt(requests.size());

        for(Friend request : requests.values()) {
            response.addInt(request.getId());
            response.addString(request.getUsername());
            response.addString(request.getLook());
        }

        return response;
    }
}

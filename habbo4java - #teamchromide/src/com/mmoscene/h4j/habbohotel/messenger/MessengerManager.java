package com.mmoscene.h4j.habbohotel.messenger;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.communication.composers.messenger.InitializeMessengerMessageComposer;
import com.mmoscene.h4j.communication.composers.messenger.SendPendingFriendRequestsMessageComposer;
import com.mmoscene.h4j.communication.composers.messenger.UpdateFriendStateMessageComposer;
import com.mmoscene.h4j.habbohotel.user.User;
import com.mmoscene.h4j.network.sessions.Session;
import gnu.trove.map.hash.THashMap;

public class MessengerManager {
    private THashMap<Integer, Friend> friends = new THashMap<>();
    private THashMap<Integer, Friend> requests = new THashMap<>();
    private User user;

    public MessengerManager(User user) {
        friends = H4J.getDAO().getMessengerDAO().loadFriends(user);
        requests = H4J.getDAO().getMessengerDAO().loadRequests(user);
        this.user = user;
    }

    public THashMap<Integer, Friend> getFriends() {
        return friends;
    }

    public THashMap<Integer, Friend> getRequests() {
        return requests;
    }

    public void setUpdate(boolean online) {
        for(Friend friend : friends.values()) {
            if (H4J.getNetwork().getSessionManager().getOnlineStatusById(friend.getId())) { //if online
                Session channel = H4J.getNetwork().getSessionManager().getSessionById(friend.getId());

                channel.respond(UpdateFriendStateMessageComposer.compose(online, this.user));
            }
        }
    }

    public boolean friendshipExists(int id) {

        for(Friend friend : friends.values()) {
            if (friend.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void refreshFriends() {
        friends = H4J.getDAO().getMessengerDAO().loadFriends(user);

        Session session = H4J.getNetwork().getSessionManager().getSessionById(user.getId());
        session.respond(InitializeMessengerMessageComposer.compose(friends));
    }

    public void refreshRequests() {
        requests = H4J.getDAO().getMessengerDAO().loadRequests(user);

        Session session = H4J.getNetwork().getSessionManager().getSessionById(user.getId());
        session.respond(SendPendingFriendRequestsMessageComposer.compose(requests));
    }
}

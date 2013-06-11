package com.mmoscene.h4j.database;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.messenger.Friend;
import com.mmoscene.h4j.habbohotel.user.User;
import gnu.trove.map.hash.THashMap;
import org.magicwerk.brownies.collections.GapList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Messenger {
    public THashMap<Integer, Friend> loadFriends(User user) {
        THashMap<Integer, Friend> friends = new THashMap<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT server_users.id, server_users.username, server_users.motto, server_users.look FROM server_friendships INNER JOIN server_users ON server_friendships.friend_id = server_users.id WHERE server_friendships.user_id = ? UNION " +
                "SELECT server_users.id, server_users.username, server_users.motto, server_users.look FROM server_friendships INNER JOIN server_users ON server_friendships.user_id = server_users.id WHERE server_friendships.friend_id = ?")) {
                statement.setInt(1, user.getId());
                statement.setInt(2, user.getId());

                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        friends.put(set.getInt("id"), this.generateFriend(set));
                    }
                }
            }
        } catch(Exception ex) {
            H4J.getLogger(Messenger.class.getName()).error(ex.getMessage());
        }
        return friends;
    }

    public THashMap<Integer, Friend> loadRequests(User user) {
        THashMap<Integer, Friend> friends = new THashMap<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT server_users.id, server_users.username, server_users.motto, server_users.look FROM server_friendships_pending INNER JOIN server_users ON server_friendships_pending.sender_id = server_users.id WHERE server_friendships_pending.reciever_id = ?")) {
                statement.setInt(1, user.getId());

                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        friends.put(set.getInt("id"), this.generateFriend(set));
                    }
                }
            }
        } catch(Exception ex) {
            H4J.getLogger(Messenger.class.getName()).error(ex.getMessage());
        }
        return friends;
    }

    public GapList<Friend> performSearch(String query) {
        GapList<Friend> results = new GapList<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, username, motto, look FROM server_users WHERE username LIKE ? LIMIT 15")) {
                statement.setString(1, "%" + query + "%");

                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        results.add(this.generateFriend(set));
                    }
                }
            }
        } catch(Exception ex) {
            H4J.getLogger(Messenger.class.getName()).error(ex.getMessage());
        }
        return results;
    }

    public void requestFriendship(int sender, int receiver) {
        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO server_friendships_pending (sender_id, reciever_id) VALUES (?, ?)")) {
                statement.setInt(1, sender);
                statement.setInt(2, receiver);

                statement.execute();
            }
        } catch(Exception ex) {
            H4J.getLogger(Messenger.class.getName()).error(ex.getMessage());
        }
    }

    public void acceptFriendship(int sender, int receiver) {
        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM server_friendships_pending WHERE sender_id = ? AND reciever_id = ?")) {
                statement.setInt(1, sender);
                statement.setInt(2, receiver);

                statement.execute();
            }
        } catch(Exception ex) {
            H4J.getLogger(Messenger.class.getName()).error(ex.getMessage());
        }

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO server_friendships(user_id, friend_id) VALUES (?, ?)")) {
                statement.setInt(1, sender);
                statement.setInt(2, receiver);

                statement.execute();
            }
        } catch(Exception ex) {
            H4J.getLogger(Messenger.class.getName()).error(ex.getMessage());
        }
    }

    public Friend generateFriend(ResultSet set) {
        Friend friend = new Friend();

        try {
            friend.setId(set.getInt("id"));
            friend.setUsername(set.getString("username"));
            friend.setLook(set.getString("look"));
            friend.setMotto(set.getString("motto"));
        } catch(Exception ex) {
            H4J.getLogger(Messenger.class.getName()).error(ex.getMessage());
        }

        return friend;
    }
}

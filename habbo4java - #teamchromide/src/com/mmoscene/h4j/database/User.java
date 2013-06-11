package com.mmoscene.h4j.database;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.user.badges.BadgeObject;
import org.magicwerk.brownies.collections.GapList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

    public void generate(String ticket) {
        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_users WHERE client_key = ?")) {

            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }
    }

    public com.mmoscene.h4j.habbohotel.user.User getUserBySSO(String ticket) {
        com.mmoscene.h4j.habbohotel.user.User user = new com.mmoscene.h4j.habbohotel.user.User();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_users WHERE client_key = ?")) {
                statement.setString(1, ticket);

                try (ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        user.setId(set.getInt("id"));
                        user.setCredits(set.getInt("credits"));
                        user.setPixels(set.getInt("pixels"));
                        user.setCurrency(set.getInt("currency"));
                        user.setRank(set.getInt("rank"));
                        user.setRespect(set.getInt("respect_points"));
                        user.setDailyRespect(set.getInt("daily_respect"));
                        user.setNameChanges(set.getInt("active_name_changes"));
                        user.setPrimaryGuild(set.getInt("primary_guild"));

                        user.setUsername(set.getString("username"));
                        user.setEmail(set.getString("email"));
                        user.setMotto(set.getString("motto"));
                        user.setLook(set.getString("look"));
                        user.setLastActive(set.getString("last_active"));

                        user.setGender(set.getString("gender").charAt(0));

                        user.startMessenger();
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }

        return user;
    }

    public com.mmoscene.h4j.habbohotel.user.User getUserById(int id) {
        com.mmoscene.h4j.habbohotel.user.User user = new com.mmoscene.h4j.habbohotel.user.User();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_users WHERE id = ?")) {
                statement.setInt(1, id);

                try (ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        user.setId(set.getInt("id"));
                        user.setCredits(set.getInt("credits"));
                        user.setPixels(set.getInt("pixels"));
                        user.setCurrency(set.getInt("currency"));
                        user.setRank(set.getInt("rank"));
                        user.setRespect(set.getInt("respect_points"));
                        user.setDailyRespect(set.getInt("daily_respect"));
                        user.setNameChanges(set.getInt("active_name_changes"));
                        user.setPrimaryGuild(set.getInt("primary_guild"));

                        user.setUsername(set.getString("username"));
                        user.setEmail(set.getString("email"));
                        user.setMotto(set.getString("motto"));
                        user.setLook(set.getString("look"));
                        user.setLastActive(set.getString("last_active"));

                        user.setGender(set.getString("gender").charAt(0));

                        user.startMessenger();
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }

        return user;
    }

    public int getIdByUsername(String username) {
        int id = 0;

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM server_users WHERE username = ?")) {
                statement.setString(1, username);

                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        id = set.getInt("id");
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }

        return id;
    }

    public String getUsernameById(int id) {
        String username = "";

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT username FROM server_users WHERE id = ?")) {
                statement.setInt(1, id);

                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        username = set.getString("username");
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }

        return username;
    }

    public void append(com.mmoscene.h4j.habbohotel.user.User user) {
        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE server_users SET credits = ?, pixels = ?, currency = ?, rank = ?, respect_points = ?, daily_respect = ?, active_name_changes = ?, primary_guild = ?, username = ?, email = ?, motto = ?, look = ?, last_active = ?, gender = ? WHERE id = ?")) {
                statement.setInt(1, user.getCredits());
                statement.setInt(2, user.getPixels());
                statement.setInt(3, user.getCurrency());
                statement.setInt(4, user.getRank());
                statement.setInt(5, user.getRespect());
                statement.setInt(6, user.getDailyRespect());
                statement.setInt(7, user.getNameChanges());
                statement.setInt(8, user.getPrimaryGuild());

                statement.setString(9, user.getUsername());
                statement.setString(10, user.getEmail());
                statement.setString(11, user.getMotto());
                statement.setString(12, user.getLook());
                statement.setString(13, user.getLastActive()) ;
                statement.setString(14, Character.toString(user.getGender()));

                statement.setInt(15, user.getId());

                statement.execute();
            }
        } catch(Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }
    }

    public boolean usernameExists(String username) {
        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_users WHERE username = ?")) {
                statement.setString(1, username);

                try(ResultSet set = statement.executeQuery()) {
                    set.last();

                    return set.getRow() > 0;
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }

        return false;
    }

    public void updateOnline() {
        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE server_stats SET `value` = ? WHERE `key` = ?")) {
                statement.setInt(1, H4J.getNetwork().getSessionManager().size());
                statement.setString(2, "online_count");
                statement.execute();
            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }
    }

    public GapList<BadgeObject> getBadgesById(int id) {
        GapList<BadgeObject> badges = new GapList<>();

        try (Connection connection = H4J.getStorage().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_user_badges WHERE user = ?")) {
                statement.setInt(1, id);

                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        BadgeObject b = new BadgeObject();

                        b.setId(set.getInt("id"));
                        b.setCode(set.getString("code"));
                        b.setEquipped(set.getInt("equipped") == 1);

                        if (b.isEquipped()) {
                            b.setSlot(set.getInt("slot"));
                        } else {
                            b.setSlot(0);
                        }

                        badges.add(b);
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(User.class.getName()).info(ex.getMessage());
        }

        return badges;
    }
}

package com.mmoscene.h4j.database;

import com.google.common.primitives.Ints;
import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.pathfinding.Position;
import com.mmoscene.h4j.habbohotel.rooms.models.Model;
import com.mmoscene.h4j.habbohotel.rooms.models.SquareState;
import gnu.trove.map.hash.THashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class Room {
    public com.mmoscene.h4j.habbohotel.rooms.Room generate(ResultSet set) {
        com.mmoscene.h4j.habbohotel.rooms.Room room = new com.mmoscene.h4j.habbohotel.rooms.Room();

        try {
            room.setId(set.getInt("id"));
            room.setOwner(set.getInt("owner"));
            room.setState(set.getInt("state"));
            room.setGuild(set.getInt("guild"));

            room.setName(set.getString("name"));
            room.setDescription(set.getString("description"));
            room.setPassword(set.getString("password"));
            room.setModel(H4J.getHabboHotel().getRoomManager().getModel(set.getString("model")));
            room.setWallpaper(set.getString("wallpaper"));
            room.setFloor(set.getString("floor"));
            room.setLandscape(set.getString("landscape"));
        } catch (Exception ex) {
            H4J.getLogger(Room.class.getName()).info(ex.getMessage());
        }

        return room;
    }

    public com.mmoscene.h4j.habbohotel.rooms.Room generate(int id) {
        com.mmoscene.h4j.habbohotel.rooms.Room room = new com.mmoscene.h4j.habbohotel.rooms.Room();

        try(Connection connection = H4J.getStorage().getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_rooms WHERE id = ?")) {

                statement.setInt(1, id);

                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        room.setId(set.getInt("id"));
                        room.setOwner(set.getInt("owner"));
                        room.setState(set.getInt("state"));
                        room.setGuild(set.getInt("guild"));

                        room.setName(set.getString("name"));
                        room.setDescription(set.getString("description"));
                        room.setPassword(set.getString("password"));
                        room.setModel(H4J.getHabboHotel().getRoomManager().getModel(set.getString("model")));
                        room.setWallpaper(set.getString("wallpaper"));
                        room.setFloor(set.getString("floor"));
                        room.setLandscape(set.getString("landscape"));
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(Room.class.getName()).info(ex.getMessage());
        }

        return room;
    }

    public THashMap<String, Model> getModels() {
        THashMap<String, Model> models = new THashMap<>();

        try(Connection connection = H4J.getStorage().getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM server_room_models")) {
                try(ResultSet set = statement.executeQuery()) {
                    while(set.next()) {
                        Model m = new Model();

                        m.setName(set.getString("id"));

                        m.setDoor(new Position(
                                set.getInt("door_x"),
                                set.getInt("door_y"),
                                set.getInt("door_z"),
                                set.getInt("door_dir")));

                        m.setHeightmap(set.getString("heightmap"));

                        String s_map = "";
                        String[] lines = m.getHeightmap().split(Character.toString((char) 13));

                        m.setLimitX(lines[0].length());
                        m.setLimitY(lines.length);

                        m.setSquareStates(new int[m.getLimitX()][m.getLimitY()]);
                        m.setSquareHeight(new double[m.getLimitX()][m.getLimitY()]);
                        m.setSquares(new SquareState[m.getLimitX()][m.getLimitY()]);

                        String relative = "";

                        for (int y = 0; y < m.getLimitY() - 1; y++) {
                            if (y > 0) {
                                lines[y] = lines[y].substring(1);
                            }

                            for (int x = 0; x < m.getLimitX() - 1; x++) {

                                String sq = lines[y].substring(x, x + 1).trim().toLowerCase();

                                if (sq.equals("x")) {
                                    m.getSquares()[x][y] = SquareState.CLOSED;
                                    m.getSquareStates()[x][y] = 0;
                                } else if (Ints.tryParse(sq) != null) {
                                    m.getSquares()[x][y] = SquareState.OPEN;
                                    m.getSquareStates()[x][y] = 1;
                                    m.getSquareHeight()[x][y] = Double.parseDouble(sq);
                                }

                                if (x == m.getDoor().getX() && y == m.getDoor().getY()) {
                                    m.getSquares()[x][y] = SquareState.OPEN;
                                    relative += m.getDoor().getZ() + "";
                                } else {
                                    if (!sq.isEmpty() && sq != null) {
                                        relative += sq;
                                    }
                                }
                            }

                            relative += (char) 13;
                        }

                        m.setRelativeHeightmap(relative);

                        for(String l : lines) {
                            if (!l.isEmpty() && l != null) {
                                s_map += l + (char) 13;
                            }
                        }

                        m.setHeightmap(s_map);

                        models.put(m.getName(), m);
                    }
                }
            }
        } catch (Exception ex) {
            H4J.getLogger(Room.class.getName()).info(ex.getMessage());
        }
        return models;
    }
}

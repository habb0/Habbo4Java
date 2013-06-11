package com.mmoscene.h4j.habbohotel.pathfinding;

import com.mmoscene.h4j.habbohotel.rooms.Room;

import java.util.LinkedList;

public interface Pathfinder {
    public Position getNextNode(Position current, Position end, Room room);
    public LinkedList<Position> calculatePath(Position start, Position end, Room room);
}

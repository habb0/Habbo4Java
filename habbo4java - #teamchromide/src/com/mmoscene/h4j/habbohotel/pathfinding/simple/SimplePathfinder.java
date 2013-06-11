package com.mmoscene.h4j.habbohotel.pathfinding.simple;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.pathfinding.Pathfinder;
import com.mmoscene.h4j.habbohotel.pathfinding.Position;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import com.mmoscene.h4j.habbohotel.rooms.models.SquareState;

import java.awt.*;
import java.util.LinkedList;

public class SimplePathfinder implements Pathfinder {
    private Point[] proximity = new Point[] {
        new Point(0, -1),
        new Point(0, 1),
        new Point(1, 0),
        new Point(-1, 0),
        new Point(1, -1),
        new Point(-1, 1),
        new Point(1, 1),
        new Point(-1, -1)
    };

    public Position getNextNode(Position current, Position end, Room room) {
        Position p = new Position(-1, -1, 0, 0);

        //diagonal
        if (current.getX() > end.getX() && current.getY() > end.getY()) {
            p.setX(current.getX() - 1);
            p.setY(current.getY() - 1);
        } else if(current.getX() < end.getX() && current.getY() < end.getY()) {
            p.setX(current.getX() + 1);
            p.setY(current.getY() + 1);
        } else if(current.getX() > end.getX() && current.getY() < end.getY()) {
            p.setX(current.getX() - 1);
            p.setY(current.getY() + 1);
        } else if(current.getX() < end.getX() && current.getY() > end.getY()) {
            p.setX(current.getX() + 1);
            p.setY(current.getY() - 1);
        }
        //straight
        else if(current.getX() > end.getX()) {
            p.setX(current.getX() - 1);
            p.setY(current.getY());
        } else if(current.getX() < end.getX()) {
            p.setX(current.getX() + 1);
            p.setY(current.getY());
        } else if(current.getY() > end.getY()) {
            p.setX(current.getX());
            p.setY(current.getY() - 1);
        } else if(current.getY() < end.getY()) {
            p.setX(current.getX());
            p.setY(current.getY() + 1);
        }
        return p;
    }

    public LinkedList<Position> calculatePath(Position start, Position end, Room room) {
        LinkedList<Position> path = new LinkedList<>();

        Position n = new Position(0, 0, 0, 0); //our placeholder

        while(true) {
            if (n.getX() == 0 && n.getY() == 0) {
                n = this.getNextNode(start, end, null);
            } else {
                n = this.getNextNode(n, end, null);
            }

            path.add(n);

            if (n.getX() == end.getX() && n.getY() == end.getY()) {
                break;
            }
        }

        return path;
    }
}

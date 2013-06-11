package com.mmoscene.h4j.habbohotel.pathfinding.complex;

import com.mmoscene.h4j.habbohotel.pathfinding.Pathfinder;
import com.mmoscene.h4j.habbohotel.pathfinding.Position;
import com.mmoscene.h4j.habbohotel.rooms.Room;
import com.mmoscene.h4j.habbohotel.rooms.models.SquareState;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

public class ComplexPathfinder implements Pathfinder {

    private Point[] proximity = new Point[] {
            new Point(-1, 0),
            new Point(0, -1),
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, -1),
            new Point(-1, 1),
            new Point(1, -1),
            new Point(1, 1),
    };

    @Override
    public Position getNextNode(Position current, Position end, Room room) {
        return calculatePath(current, end, room).get(0);
    }

    @Override
    public LinkedList<Position> calculatePath(Position start, Position end, Room room) {
        LinkedList<Position> path = new LinkedList<>();

        Values v = new Values(room);

        if (end.getX() >= room.getModel().getLimitX() || end.getY() >= room.getModel().getLimitY()) { //out of bounds
            return path;
        }

        if (room.getModel().getSquares()[end.getX()][end.getY()] == SquareState.CLOSED) { //closed tile
            return path;
        }

        if (start.getX() == end.getX() && start.getY() == end.getY()) { //no movement
            return path;
        }

        v.count++;
        v.getBinaryHeap()[v.count] = v.id;
        v.getX()[v.id] = start.getX();
        v.getY()[v.id] = start.getY();
        v.getH()[v.id] = (short) start.distanceFrom(end);
        v.getParent()[v.id] = 0;
        v.getG()[v.id] = 0;
        v.getF()[v.id] = (short) (v.getG()[v.id] + v.getH()[v.id]);

         while(v.count != 0) {
             v.location = v.getBinaryHeap()[1];

             if (v.getX()[v.location] == end.getX() && v.getY()[v.location] == end.getY()) {
                 break;
             }

             try {
                 move(v);

                 addAllNeighbors(end, v, room);
             } catch(Exception ignored) {
                 ignored.printStackTrace();
             }
         }

        while(v.getX()[v.getParent()[v.location]] != start.getX() || v.getY()[v.getParent()[v.location]] != start.getY()) {
            path.add(new Position(v.getX()[v.location], v.getY()[v.location], 0, 0));
            v.location = v.getParent()[v.location];
        }

        path.add(new Position(v.getX()[v.location], v.getY()[v.location], 0, 0));

        Collections.reverse(path);

        return path;
    }

    private void addNeighbor(Position start, Position end, Values v, Room r) {
        Position n = new Position(
                v.getX()[v.location] + start.getX(),
                v.getY()[v.location] + start.getY(), 0, 0);

        if (n.getX() < 0 || n.getY() < 0) {
            return;
        }

        short p = v.location;

        if (n.getX() >= r.getModel().getLimitX() || n.getY() >= r.getModel().getLimitY()) { //out of bounds
            return;
        }

        if (r.getModel().getSquareStates()[n.getX()][n.getY()] == 2) { //not possible
            //return;
        }

        if (r.getModel().getSquares()[n.getX()][n.getY()] == SquareState.CLOSED ||
                (n.getY() != end.getY() && n.getX() != end.getX())) {
            return;
        }

        if (p > 0) {
            if (v.getX()[p] != n.getX() && v.getY()[p] != n.getY()) {
                if (r.getModel().getSquares()[n.getX()][v.getY()[p]] == SquareState.CLOSED ||
                        r.getModel().getSquares()[v.getX()[p]][n.getY()] == SquareState.CLOSED) {
                    //return;
                }
            }
        }

        if (v.getTiles()[n.getX()][n.getY()] == 1) {
            short i = 1;

            for (; i <= v.count; i++) {
                if  (v.getX()[i] == n.getX() && v.getY()[i] == n.getY()) {
                    break;
                }
            }

            if (v.getX()[i] == end.getX() || v.getY()[i] == end.getY()) {
                if (10 + v.getG()[p] < v.getG()[i]) {
                    v.getParent()[i] = p;
                } else if(14 + v.getG()[p] < v.getG()[i]) {
                    v.getParent()[i] = p;
                    return;
                }
            }

            if ((0 <= v.id && v.id + 1 < v.size) && (0 <= v.count && v.count + 1 < v.size)) {
                v.id++;
                v.count++;

                v.getBinaryHeap()[v.count] = v.id;
                v.getX()[v.id] = n.getX();
                v.getY()[v.id] = n.getY();
                v.getH()[v.id] = (short) n.distanceFrom(end);
                v.getParent()[v.id] = p;

                if (n.getX() == v.getX()[p] || n.getY() == v.getY()[p]) {
                    v.getG()[v.id] = (short) (10 + v.getG()[p]);
                } else {
                    v.getG()[v.id] = (short) (14 + v.getG()[p]);
                }

                v.getF()[v.id] = (short) (v.getG()[v.id] + v.getH()[v.id]);

                for(short s = v.count; s != 1; s /= 2) {
                    if (v.getF()[v.getBinaryHeap()[s]] > v.getF()[v.getBinaryHeap()[s / 2]]) { //what the fuk D:
                        break;
                    }

                    /*
                        Invert the values..
                     */
                    short t = v.getBinaryHeap()[s / 2];

                    v.getBinaryHeap()[s / 2] = v.getBinaryHeap()[s];
                    v.getBinaryHeap()[s] = t;
                }
            }
            v.getTiles()[n.getX()][n.getY()] = 1;
        }
    }

    private void addAllNeighbors(Position end, Values v, Room r) {
        for(Point p : proximity) {
            addNeighbor(new Position(p.x, p.y, 0, 0), end, v, r);
        }
    }

    private void move(Values v) {
        v.getTiles()[v.getX()[v.getBinaryHeap()[1]]][v.getY()[v.getBinaryHeap()[1]]] = 1;

        v.getBinaryHeap()[1] = v.getBinaryHeap()[v.count];
        v.count--;

        short l = 1;

        while(true) {
            short h = l;

            if (2 * h + 1 <= v.count) {
                if (v.getF()[v.getBinaryHeap()[h]] >= v.getF()[v.getBinaryHeap()[2 * h]]) {
                    l = (short) (2 * h);
                }
                if (v.getF()[v.getBinaryHeap()[l]] >= v.getF()[v.getBinaryHeap()[2 * h + 1]]) {
                    l = (short) (2 * h + 1);
                }
            } else if(2 * h <= v.count) {
                if (v.getF()[v.getBinaryHeap()[h]] >= v.getF()[v.getBinaryHeap()[2 * h]]) {
                    l = (short) (2 * h);
                }
            }

            if (h == l) {
                break;
            }

            /*
                Switch the values..
             */
            short t = v.getBinaryHeap()[h];
            v.getBinaryHeap()[h] = v.getBinaryHeap()[l];
            v.getBinaryHeap()[l] = t;
        }
    }
}

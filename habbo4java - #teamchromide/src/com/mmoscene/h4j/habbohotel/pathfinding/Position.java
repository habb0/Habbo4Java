package com.mmoscene.h4j.habbohotel.pathfinding;

import com.mmoscene.h4j.H4J;

public class Position {
    private int x, y, rotation;
    private float z;

    public Position(int x, int y, float z, int rotation) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public int calculateRotation(int new_x, int new_y) {
        int rot = 0;

        if (x > new_x && y > new_y) {
            rot = 7;
        } else if (x < new_x && y < new_y) {
            rot = 3;
        } else if (x > new_x && y < new_y) {
            rot = 5;
        } else if (x < new_x && y > new_y) {
            rot = 1;
        } else if (x > new_x) {
            rot = 6;
        } else if (x < new_x) {
            rot = 2;
        } else if (y < new_y) {
            rot = 4;
        } else if (y > new_y) {
            rot = 0;
        }
        
        return rot;
    }

    public int distanceFrom(Position pos) {
        return (Math.abs(x + pos.getX()) + Math.abs(y + pos.getY()));
    }
}

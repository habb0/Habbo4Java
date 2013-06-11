package com.mmoscene.h4j.habbohotel.pathfinding.complex;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.rooms.Room;

/*
    Re-Written from IHI
 */
public class Values {

    private float max_jump = (float) 1.2;
    private float max_fall = (float) 3.0;

    private short[] binary_heap;
    private short[] f;
    private short[] g;
    private short[] h;

    private short[] parent;

    private int[][] tiles;
    private int[] x;
    private int[] y;
    private float[][] z;

    public short count = 0;
    public short id = 0;
    public short location = 0;
    public int size = 0;

    public Values(Room r) {
        size = r.getModel().getLimitX() * r.getModel().getLimitY();

        size++;

        tiles = r.getModel().getSquareStates();

        x = new int[size];
        y = new int[size];
        z = new float[r.getModel().getLimitX()][r.getModel().getLimitY()];

        f = new short[size];
        g = new short[size];
        h = new short[size];

        binary_heap = new short[size];
        parent = new short[size];
    }

    public float getMaxJump() {
        return max_jump;
    }

    public float getMaxFall() {
        return max_fall;
    }

    public short[] getBinaryHeap() {
        return binary_heap;
    }

    public short[] getF() {
        return f;
    }

    public short[] getG() {
        return g;
    }

    public short[] getH() {
        return h;
    }

    public short[] getParent() {
        return parent;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public float[][] getZ() {
        return z;
    }
}

package com.mmoscene.h4j.habbohotel.rooms.models;

import com.mmoscene.h4j.H4J;
import com.mmoscene.h4j.habbohotel.pathfinding.Position;

public class Model {
    private Position door;

    private String name, heightmap, relative_heightmap;

    private int limit_x, limit_y;

    private SquareState[][] squares;

    private double[][] square_height;

    private int[][] square_states;

    public Position getDoor() {
        return door;
    }

    public void setDoor(Position door) {
        this.door = door;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeightmap() {
        return heightmap;
    }

    public void setHeightmap(String heightmap) {
        this.heightmap = heightmap;
    }

    public String getRelativeHeightmap() {
        return relative_heightmap;
    }

    public void setRelativeHeightmap(String relative_heightmap) {
        this.relative_heightmap = relative_heightmap;
    }

    public SquareState[][] getSquares() {
        return squares;
    }

    public void setSquares(SquareState[][] squares) {
        this.squares = squares;
    }

    public double[][] getSquareHeight() {
        return square_height;
    }

    public void setSquareHeight(double[][] square_height) {
        this.square_height = square_height;
    }

    public int[][] getSquareStates() {
        return square_states;
    }

    public void setSquareStates(int[][] square_states) {
        this.square_states = square_states;
    }

    public int getLimitX() {
        return limit_x;
    }

    public void setLimitX(int limit_x) {
        this.limit_x = limit_x;
    }

    public int getLimitY() {
        return limit_y;
    }

    public void setLimitY(int limit_y) {
        this.limit_y = limit_y;
    }
}

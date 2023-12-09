package com.exemple.GGIT;

public enum MapDirection{
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0);

    private Vector2D Un;

    MapDirection(int x, int y) {
        Un = new Vector2D(x, y);
    }

    public Vector2D getUnitVector() {
        return Un;
    }
}

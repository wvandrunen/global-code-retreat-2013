package com.coderetreat;

public class Cell {

    private final boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public boolean isAlive() {
        return alive;
    }
}

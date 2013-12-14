package com.coderetreat;

import java.util.Collections;
import java.util.List;

public class Grid {
    public Cell getCell(int x, int y) {
        return new Cell(false);
    }

    protected List<Cell> getNeighbors(int x, int y) {
        return Collections.emptyList();
    }

    public boolean isUnderpopulated(int x, int y) {
        return false;
    }
}

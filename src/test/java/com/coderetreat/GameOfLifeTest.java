package com.coderetreat;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class GameOfLifeTest {

    @Test
    public void shouldFetch10by10GridWithDeadCell() {
        Grid grid = new Grid();

        assertThat(grid.getCell(10,10).isDead()).isTrue();
    }

    @Test
    public void shouldReturnAllNeighbors() {
        Grid grid = new Grid();
        assertThat(grid.getNeighbors(5,5).size()).equals(8);

         ;
    }

    @Test
    public void cellFromGridShouldByUnderPopulatedWhenHavingNoNeighbors() {
        Grid grid = new Grid();

        assertThat(grid.isUnderpopulated(10,10)).isTrue();
    }

    @Test
    public void cellShouldBeDeadWhenGivenFalse() {
        Cell cell = new Cell(false);

        assertThat(cell.isDead()).isTrue();
    }

    @Test
    public void cellShouldBeAliveWhenGivenTrue() {
        Cell cell = new Cell(true);

        assertThat(cell.isAlive()).isTrue();
    }
}

package com.coderetreat;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class GameOfLifeTest {

    @Test
    public void shouldDieWithLessThenTwoNeighbors() {
        Cell nextGen = createNextGenCellBasedOnNeighbors(1);

        assertThat(nextGen.isAlive()).isFalse();
    }

    private Cell createNextGenCellBasedOnNeighbors(int neighbors) {
        Cell cell = new Cell(true);
        return cell.constructNextGen(neighbors);
    }

    @Test
    public void shouldSurviveWithTwoNeighbors() {
        Cell nextGen = createNextGenCellBasedOnNeighbors(2);
        assertThat(nextGen.isAlive()).isTrue();
    }


    @Test
    public void shouldSurviveWithThreeNeighbors() {

        Cell nextGen = createNextGenCellBasedOnNeighbors(3);
        assertThat(nextGen.isAlive()).isTrue();
    }
    @Test
    public void shouldDieWithMoreThenThreeNeighbors() {

        Cell nextGen = createNextGenCellBasedOnNeighbors(5);

        assertThat(nextGen.isAlive()).isFalse();
    }

    @Test
    public void shouldReviveDeadCellWithWith3Neighbors() {
        assertThat(becomesAlive(3)).isTrue();
    }

    private boolean becomesAlive(int i) {
        return (i == 3);
    }

    @Test
    public void shouldReturnTrueWhenIsAlive() {
        Cell cell = new Cell(true);

        assertThat(cell.isAlive()).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIsAlive() {
        Cell cell = new Cell(false);

        assertThat(cell.isAlive()).isFalse();
    }

    @Test
    public void calculateNextgenWhenGivenThreeNeighbors() {
        Cell cell = new Cell(true);

        Cell nextGen = cell.constructNextGen(3);

        assertThat(nextGen.isAlive()).isTrue();
    }

     @Test
    public void shouldReturnZeroAliveNeighborsWhenGettingFromWorld() {
        World world = new World();

        assertThat(world.getAliveNeighbors(1,1)).isEqualTo(0);
    }

    private class World {
        public int getAliveNeighbors(int x, int y) {
            return 0;
        }
    }

    private class Cell {
        private boolean alive;

        public Cell(boolean alive) {
            this.alive = alive;
        }

        public boolean isAlive() {
            return alive;
        }

        public Cell constructNextGen(int neighbors) {
            return new Cell(remainsAliveWith(neighbors));
        }

        private boolean remainsAliveWith(int neighbors) {
            return !(neighbors > 3 || neighbors < 2);
        }
    }
}

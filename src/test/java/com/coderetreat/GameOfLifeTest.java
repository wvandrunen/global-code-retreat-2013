package com.coderetreat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class GameOfLifeTest {


    @Test
    public void shouldDieWithAmountOfNeighboursLowerThenTwo() {
        Cell cell = new Cell(0, 0, true);
        Cell nextGen = cell.moveNextGen(1);


        assertThat(nextGen.isDead()).isTrue();
    }

    @Test
    public void shouldSurviveGivenTwoNeighbours() {
        Cell cell = new Cell(0, 0, true);
        Cell nextGen = cell.moveNextGen(2);

        assertThat(nextGen.isAlive()).isTrue();
    }

    @Test
    public void shouldSurviveGivenThreeNeighbours() {
        Cell cell = new Cell(0, 0, true);
        Cell nextGen = cell.moveNextGen(3);


        assertThat(nextGen.isAlive()).isTrue();
    }

    @Test
    public void shouldDieGivenOvercrowding() {
        Cell cell = new Cell(0, 0, true);
        Cell nextGen = cell.moveNextGen(4);

        assertThat(nextGen.isDead()).isTrue();
    }

    @Test
    public void shouldReproduceGivenDeadCellWithThreeNeighbours() {
        Cell cell = new Cell(0, 0, false);
        Cell nextGen = cell.moveNextGen(3);


        assertThat(nextGen.isAlive()).isTrue();
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToZeroGivenAllCellsDead() {
        assertThat(getAliveNeighbours(new ArrayList<Cell>(), 0, 0)).isEqualTo(0);
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToOneGivenRightNeighbourAlive() {
        List<Cell> livingCells = new ArrayList<Cell>();
        livingCells.add(new Cell(1, 0));
        assertThat(getAliveNeighbours(livingCells, 0, 0)).isEqualTo(1);
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToTwoGivenUpAndRightCellsAlive() {
        List<Cell> livingCells = new ArrayList<Cell>();
        livingCells.add(new Cell(1, 0));
        livingCells.add(new Cell(2, 1));
        livingCells.add(new Cell(100, 100));
        assertThat(getAliveNeighbours(livingCells, 1, 1)).isEqualTo(2);
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToEightGivenSurroundingCellsAlive() {
        List<Cell> livingCells = new ArrayList<Cell>();
        livingCells.add(new Cell(0, 0)); livingCells.add(new Cell(1, 0)); livingCells.add(new Cell(2, 0));
        livingCells.add(new Cell(0, 1)); livingCells.add(new Cell(1, 1)); livingCells.add(new Cell(2, 1));
        livingCells.add(new Cell(0, 2)); livingCells.add(new Cell(1, 2)); livingCells.add(new Cell(2, 2));
        livingCells.add(new Cell(100, 100));
        assertThat(getAliveNeighbours(livingCells, 1, 1)).isEqualTo(8);
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToThreeGivenUpAndRightCellsAlive() {
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(new Cell(1, 0));
        cells.add(new Cell(2, 1));
        cells.add(new Cell(2, 2));
        cells.add(new Cell(100, 100));
        assertThat(getAliveNeighbours(cells, 1, 1)).isEqualTo(3);
    }

    @Test
    public void shouldCalculateTheNextGeneration() {
        List<Cell> generation1 = new ArrayList<Cell>();
        List<Cell> generation2 = nextGeneration(generation1);
        assertThat(generation2.size()).isEqualTo(0);
    }

    @Test
    public void nextGenerationShouldContainSurvivingCells() {
        List<Cell> generation1 = new ArrayList<Cell>();
        generation1.add(new Cell(0, 0, false)); generation1.add(new Cell(1, 0, false)); generation1.add(new Cell(2, 0, false));
        generation1.add(new Cell(0, 1, true)); generation1.add(new Cell(1, 1, false)); generation1.add(new Cell(2, 1, false));
        generation1.add(new Cell(0, 2, false)); generation1.add(new Cell(1, 2, true)); generation1.add(new Cell(2, 2, true));

        List<Cell> generation2 = nextGeneration(generation1);

        Cell cell = getCell(generation2, 1, 1);
        assertThat(cell.isAlive()).isTrue();
    }

    public Cell getCell(List<Cell> cells, int x, int y) {
        for(Cell cell : cells) {
            if(cell.isAt(x, y)) {
                return cell;
            }
        }

        return null;
    }

    private List<Cell> nextGeneration(List<Cell> currentGeneration) {
        List<Cell> nextGeneration = new ArrayList<Cell>();

        for(Cell cell : currentGeneration) {
            int aliveNeighbours = getAliveNeighbours(currentGeneration, cell.getX(), cell.getY());

            nextGeneration.add(cell.moveNextGen(aliveNeighbours));
        }
        
        return nextGeneration;
    }

    private boolean isAlive(List<Cell> cells, int x, int y) {
        for(Cell cell : cells) {
            if(cell.equals(new Cell(x, y))) {
                return cell.isAlive();
            }
        }

        return false;
    }

    private int getAliveNeighbours(List<Cell> cells, int x, int y) {
        int amountAlive = 0;

        List<Cell> possiblePoints = calculatePossiblePoints(x, y);

        for(Cell cell : cells) {
            for(Cell possiblePoint : possiblePoints) {

                if(possiblePoint.isAt(cell)) {
                    if(cell.isAlive()) {
                        amountAlive++;
                    }
                }
            }
        }

        return amountAlive;
    }

    private List<Cell> calculatePossiblePoints(int x, int y) {
        List<Cell> possiblePoints = new ArrayList<Cell>();

        possiblePoints.add(new Cell(x - 1, y));
        possiblePoints.add(new Cell(x + 1, y));
        possiblePoints.add(new Cell(x, y + 1));
        possiblePoints.add(new Cell(x, y - 1));
        possiblePoints.add(new Cell(x - 1, y -1));
        possiblePoints.add(new Cell(x + 1, y + 1));
        possiblePoints.add(new Cell(x - 1, y + 1));
        possiblePoints.add(new Cell(x + 1, y - 1));

        return possiblePoints;
    }

    @Test
    public void shouldReturnZombieCellWhenDiedCellIsRevived() {
        Cell cell = new Cell(0,0, true);

        cell.die();
        cell.live();

        assertThat(cell.isZombie()).isTrue();
    }


    class Cell {

        private final int x;
        private final int y;
        private boolean alive;
        private boolean diedAlready = false;

        public Cell(int x, int y) {
            this(x, y, true);
        }

        public Cell(Cell cell) {
            this.x = cell.x;
            this.y = cell.y;
            this.alive = cell.alive;
            this.diedAlready = cell.diedAlready;
        }

        public Cell(int x, int y, boolean alive) {
            this.x = x;
            this.y = y;
            this.alive = alive;
        }

        public int getX() {
            return x;
        }

        public boolean isAlive() {
            return alive;
        }

        public boolean isDead () {
            return !alive;
        }

        public void die() {
            diedAlready = true;
            alive = false;
        }

        public void live() {
            alive = true;
        }

        public boolean isZombie() {
            return diedAlready && alive;
        }

        public int getY() {
            return y;
        }

        public boolean isAt(int x, int y) {
            return (this.x == x && this.y == y);
        }

        public Cell moveNextGen(int neighbours) {
            Cell clonedCell = new Cell(this);

            if(isAlive() && (neighbours < 2 || neighbours > 3)) {
                clonedCell.die();
            } else if(isDead() && neighbours == 3) {
                clonedCell.live();
            }

            return clonedCell;
        }

        public boolean isAt(Cell otherCell) {
            return isAt(otherCell.x, otherCell.y);
        }
    }

}

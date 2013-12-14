package com.coderetreat;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class GameOfLifeTest {

    @Test
    public void shouldDieWithAmountOfNeigborsLowerThenTwo() {
        assertThat(isAlive(1)).isFalse();
    }

    @Test
    public void shouldSurviveGivenTwoNeighbours() {
        assertThat(isAlive(2)).isTrue();
    }

    @Test
    public void shouldSurviveGivenThreeNeighbours() {
        assertThat(isAlive(3)).isTrue();
    }

    @Test
    public void shouldDieGivenOvercrowding() {
        assertThat(isAlive(4)).isFalse();
    }

    @Test
    public void shouldReproduceGivenDeadCellWithThreeNeighbours() {
        assertThat(isDead(3)).isTrue();
    }

    @Test
    public void shouldNotReproduceGivenDeadCellWithLessThanThreeNeighbours() {
        assertThat(isDead(2)).isFalse();
    }

    @Test
    public void shouldNotReproduceGivenDeadCellWithGreaterThanThreeNeighbours() {
        assertThat(isDead(4)).isFalse();
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToZeroGivenAllCellsDead() {
        assertThat(getAliveNeighbours(new ArrayList<Point>(), 0, 0)).isEqualTo(0);
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToOneGivenRightNeighbourAlive() {
        List<Point> livingCells = new ArrayList<Point>();
        livingCells.add(new Point(1, 0));
        assertThat(getAliveNeighbours(livingCells, 0, 0)).isEqualTo(1);
    }


    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToTwoGivenUpAndRightCellsAlive() {
        List<Point> livingCells = new ArrayList<Point>();
        livingCells.add(new Point(1, 0));
        livingCells.add(new Point(2, 1));
        livingCells.add(new Point(100, 100));
        assertThat(getAliveNeighbours(livingCells, 1, 1)).isEqualTo(2);
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToEightGivenSurroundingCellsAlive() {
        List<Point> livingCells = new ArrayList<Point>();
        livingCells.add(new Point(0, 0)); livingCells.add(new Point(1, 0)); livingCells.add(new Point(2, 0));
        livingCells.add(new Point(0, 1)); livingCells.add(new Point(1, 1)); livingCells.add(new Point(2, 1));
        livingCells.add(new Point(0, 2)); livingCells.add(new Point(1, 2)); livingCells.add(new Point(2, 2));
        livingCells.add(new Point(100, 100));
        assertThat(getAliveNeighbours(livingCells, 1, 1)).isEqualTo(8);
    }

    @Test
    public void shouldReturnNumberOfAliveNeighboursEqualToThreeGivenUpAndRightCellsAlive() {
        List<Point> livingCells = new ArrayList<Point>();
        livingCells.add(new Point(1, 0));
        livingCells.add(new Point(2, 1));
        livingCells.add(new Point(2, 2));
        livingCells.add(new Point(100, 100));
        assertThat(getAliveNeighbours(livingCells, 1, 1)).isEqualTo(3);
    }

    @Test
    public void shouldCellBeAliveWhenCreatingNewCell() {
        Cell cell = new Cell();

        assertThat(cell.isAlive()).isTrue();
    }

    @Test
    public void shouldReturnTrueIfCellIsAlive() {
        List<Point> livingCells = new ArrayList<Point>();
        livingCells.add(new Point(0, 0));
        assertThat(isAlive(livingCells, 0, 0)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfCellIsDeadWithEmptyList() {
        List<Point> livingCells = new ArrayList<Point>();
        assertThat(isAlive(livingCells, 0, 0)).isFalse();
    }

    @Test
    public void shouldReturnFalseIfCellIsDeadWithNonEmptyList() {
        List<Point> livingCells = new ArrayList<Point>();
        livingCells.add(new Point(1, 0));
        assertThat(isAlive(livingCells, 0, 0)).isFalse();
    }

    @Test
    public void shouldCalculateTheNextGeneration() {
        List<Point> generation1 = new ArrayList<Point>();
        List<Point> generation2 = nextGeneration(generation1);
        assertThat(generation2.size()).isEqualTo(0);
    }

    @Test
    public void shouldCalculateTheNextGenerationWhenGivenNonEmptyGeneration() {
        List<Point> generation1 = new ArrayList<Point>();
        generation1.add(new Point(0, 1));
        generation1.add(new Point(2, 1));
        generation1.add(new Point(2, 2));
        List<Point> generation2 = nextGeneration(generation1);
        assertThat(generation2.size()).isEqualTo(1);
    }


    @Test
    public void nextGenerationShouldContainSurvivingCells() {
        List<Point> generation1 = new ArrayList<Point>();
        generation1.add(new Point(0, 1));
        generation1.add(new Point(1, 1));
        generation1.add(new Point(2, 1));
        generation1.add(new Point(2, 2));
        List<Point> generation2 = nextGeneration(generation1);
        assertThat(generation2.size()).isEqualTo(1);
    }

    private List<Point> nextGeneration(List<Point> generation) {
        if (generation.size() == 0)
            return Collections.emptyList();

        List<Point> result = new ArrayList<Point>();
        result.add(null);
        return result;
    }

    private boolean isAlive(List<Point> livingCells, int x, int y) {
        for(Point livingCell : livingCells) {
            if(livingCell.equals(new Point(x, y))) {
                return true;
            }
        }

        return false;
    }

    private int getAliveNeighbours(List<Point> livingCells, int x, int y) {
        int amountAlive = 0;

        List<Point> possiblePoints = calculatePossiblePoints(x, y);

        for(Point livingCell : livingCells) {
            for(Point possiblePoint : possiblePoints) {
                if(possiblePoint.equals(livingCell)) {
                    amountAlive++;
                }
            }
        }

        return amountAlive;
    }

    private List<Point> calculatePossiblePoints(int x, int y) {
        List<Point> possiblePoints = new ArrayList<Point>();

        possiblePoints.add(new Point(x - 1, y));
        possiblePoints.add(new Point(x + 1, y));
        possiblePoints.add(new Point(x, y + 1));
        possiblePoints.add(new Point(x, y - 1));
        possiblePoints.add(new Point(x - 1, y -1));
        possiblePoints.add(new Point(x + 1, y + 1));
        possiblePoints.add(new Point(x - 1, y + 1));
        possiblePoints.add(new Point(x + 1, y - 1));

        return possiblePoints;
    }

    private boolean isDead(int neighbours) {
        return neighbours == 3;
    }

    public boolean isAlive(int neighbours) {
        return neighbours >= 2 && neighbours < 4;
    }

    class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {

            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    private class Cell {

        public boolean isAlive() {
            return true;
        }
    }
}

package com.coderetreat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class GameOfLifeTest {

    private final LivingCell livingCell = new LivingCell();
    private final DeadCell deadCell = new DeadCell();

    private Map<Cell, Map<Integer, Cell>> rulesMap = createRulesMap();

    private Map<Cell, Map<Integer, Cell>> createRulesMap() {
        Map<Cell, Map<Integer, Cell>> rulesMap = new HashMap<Cell, Map<Integer, Cell>>();

        rulesMap.put(livingCell, createLivingCellMap());
        rulesMap.put(deadCell, createDeadCellMap());

        return rulesMap;
    }

    private Map<Integer, Cell> createLivingCellMap() {
        Map<Integer, Cell> cellMapping = new HashMap<Integer, Cell>();

        cellMapping.put(2, new LivingCell());
        cellMapping.put(3, new LivingCell());

        return cellMapping;
    }

    private Map<Integer, Cell> createDeadCellMap() {
        Map<Integer, Cell> cellMapping = new HashMap<Integer, Cell>();

        cellMapping.put(3, new LivingCell());

        return cellMapping;
    }

    private Map<Integer, Map<Integer, Cell>> grid = new HashMap<Integer, Map<Integer, Cell>>();

    class Accumulator {
        private Object currentState;
        private List<Step> remainingSteps;
    }

    class NilAccumulator extends Accumulator {}

    abstract class Step {
        public abstract Accumulator next(Accumulator accumulator);
    }

    class ConsStep extends Step {

        private List<Step> steps;

        public ConsStep(List<Step> steps) {
            this.steps = steps;
        }

        public Accumulator next(Accumulator accumulator) {
            Step currentStep = accumulator.remainingSteps.remove(0);

            // do something with the step

            // creata new accumaltor
                // -> contains old accu + new state
                // -> remaining steps

            return accumulator;
        }
    }

    private int calculateNeigbours(grid, base_x, base_y)

    class Cell {}
    class LivingCell extends Cell {}
    class DeadCell extends Cell {}

    @Test
    public void shouldDieWhenUnderpopulated() {
        Cell cell = rulesMap.get(livingCell).get(1);

        assertThat(cell).isNull();
    }
    @Test
    public void shouldSurviveWhen2Or3Neighbours() {
        Cell cell = rulesMap.get(livingCell).get(2);
        Cell cell2 = rulesMap.get(livingCell).get(3);

        assertThat(cell).isInstanceOf(LivingCell.class);
        assertThat(cell2).isInstanceOf(LivingCell.class);
    }

    @Test
    public void shouldReproduceWhenHasThreeNeighbours() {
        Cell cell = rulesMap.get(deadCell).get(3);

        assertThat(cell).isInstanceOf(LivingCell.class);
    }

    @Test
    public void shouldDieWhenOvercrowed() {
        Cell cell = rulesMap.get(livingCell).get(4);

        assertThat(cell).isNull();
    }
}

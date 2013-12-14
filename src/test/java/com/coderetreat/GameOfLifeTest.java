package com.coderetreat;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class GameOfLifeTest {

    @Test
    public void shouldDieWithLessThenTwoNeighbors() {
        assertThat(remainsAliveWith(1)).isFalse();
    }

    @Test
    public void shouldSurviveWithTwoNeighbors() {
        assertThat(remainsAliveWith(2)).isTrue();
    }

    @Test
    public void shouldSurviveWithThreeNeighbors() {
        assertThat(remainsAliveWith(3)).isTrue();
    }

    @Test
    public void shouldDieWithMoreThenThreeNeighbors() {
        assertThat(remainsAliveWith(5)).isFalse();
    }

    @Test
    public void shouldReviveDeadCellWithWith3Neighbors() {
        assertThat(becomesAlive(3)).isTrue();
    }

    private boolean becomesAlive(int i) {
        return (i == 3);
    }

    private boolean remainsAliveWith(int neighbors) {
        return !(neighbors > 3 || neighbors < 2);
    }
}

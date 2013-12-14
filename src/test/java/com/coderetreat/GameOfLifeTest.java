package com.coderetreat;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class GameOfLifeTest {

    @Test
    public void shouldDieWithLessThenTwoNeighbors() {
        boolean alive = true;

        int neighbors = 1;

        if(neighbors < 2) {
            alive = false;
        }

        assertThat(alive).isFalse();
    }

    @Test
    public void shouldDieWithMoreThenThreeNeighbors() {
        boolean alive = true;

        int neighbors = 5;

        if(neighbors > 3) {
            alive = false;
        }

        assertThat(alive).isFalse();

    }
}

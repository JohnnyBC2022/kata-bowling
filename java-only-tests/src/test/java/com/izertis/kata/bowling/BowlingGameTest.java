package com.izertis.kata.bowling;

import org.junit.Assert;
import org.junit.Test;

import es.izertis.kata.bowling.BowlingGame;

/* public class BowlingGameTest {

    @Test
    public void testGame0() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 0);
        Assert.assertEquals(0, game.getScore());
    }

    @Test
    public void testGameAll1() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 1);
        Assert.assertEquals(20, game.getScore());
    }

    @Test
    public void testGameSpare() {
        BowlingGame game = new BowlingGame();
        game.roll(5);
        game.roll(5);
        game.roll(3);
        rollMany(game, 17, 0);
        Assert.assertEquals(16, game.getScore());
    }

    @Test
    public void testGameNotSpare() {
        BowlingGame game = new BowlingGame();
        game.roll(0);
        game.roll(5);
        game.roll(5);
        game.roll(3);
        rollMany(game, 16, 0);
        Assert.assertEquals(13, game.getScore());
    }

    @Test
    public void testGameStrike() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(3);
        game.roll(2);
        rollMany(game, 16, 0);
        Assert.assertEquals(20, game.getScore());
    }

    @Test
    public void testGameNotStrikeYesSpare() {
        BowlingGame game = new BowlingGame();
        game.roll(0);
        game.roll(10);
        game.roll(3);
        game.roll(2);
        rollMany(game, 16, 0);
        Assert.assertEquals(18, game.getScore());
    }

    @Test
    public void testStrikeInLast() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 18, 0);
        game.roll(10);
        game.roll(1);
        game.roll(1);
        Assert.assertEquals(12, game.getScore());
    }

    @Test
    public void testGame300() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 12, 10);
        Assert.assertEquals(300, game.getScore());
    }

    private void rollMany(BowlingGame game, int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }

}
 */

public class BowlingGameTest {

    private void rollMany(BowlingGame game, int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }

    @Test
    public void testWorstGame() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 0);

        int score = game.getScore();
        Assert.assertEquals(0, score);
    }

    @Test
    public void testOnePinDown() {
        BowlingGame game = new BowlingGame();
        game.roll(1);
        rollMany(game, 19, 0);

        int score = game.getScore();
        Assert.assertEquals(1, score);
    }

    @Test
    public void testGameAll1() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 1);

        int score = game.getScore();
        Assert.assertEquals(20, score);
    }

    @Test
    public void tooLargeRoll() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 11);

        int score = game.getScore();
        Assert.assertEquals(20, score);
    }

}
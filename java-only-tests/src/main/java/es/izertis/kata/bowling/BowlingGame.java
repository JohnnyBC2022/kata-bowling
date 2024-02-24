package es.izertis.kata.bowling;

public class BowlingGame {
    private int count;
    public void roll(int pins) {
        count += pins;
    }

    public int getScore() {
        return count;
    }

}

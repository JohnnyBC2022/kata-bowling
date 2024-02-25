package es.izertis.kata.bowling;

public class BowlingGame {
    private int count;
    public void roll(int pins) {
        if (pins > 10) {
            throw new IllegalArgumentException();
        }
        count += pins;
    }

    public int getScore() {
        return count;
    }


}

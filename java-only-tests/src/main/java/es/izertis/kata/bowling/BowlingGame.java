package es.izertis.kata.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private List<Integer> rolls = new ArrayList<>();
    private int bonus;

    public void roll(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException();
        }
        if (rolls.size() >= 2 
        && rolls.get(rolls.size() - 1) + rolls.get(rolls.size() - 2) == 10) {
            bonus += pins;
        }
        rolls.add(pins);
    }

    public int getScore() {
        int baseScore = rolls.stream().mapToInt(i -> i).sum();

        return baseScore + bonus;
    }

}

package es.izertis.kata.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private List<Integer> rolls = new ArrayList<>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public int getScore() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + rolls.get(frameIndex + 1) + rolls.get(frameIndex + 2);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + rolls.get(frameIndex + 2);
                frameIndex += 2;
            } else {
                score += rolls.get(frameIndex) + rolls.get(frameIndex + 1);
                frameIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int frameIndex) {
        return rolls.get(frameIndex) == 10;
    }

    private boolean isSpare(int frameIndex) {
        return rolls.get(frameIndex) + rolls.get(frameIndex + 1) == 10;
    }
}

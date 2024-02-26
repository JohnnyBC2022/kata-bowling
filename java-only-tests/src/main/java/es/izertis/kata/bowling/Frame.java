package es.izertis.kata.bowling;

import java.util.List;

public class Frame {
    private final int roll1;
    private final int roll2;

    public Frame(int roll1, int roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    public int baseScore() {
        return roll1 + roll2;
    }

    public int calculateBonus(List<Integer> rolls, int frameIndex) {
        int bonus = 0;
        if (isStrike(rolls, frameIndex)) {
            if (frameIndex == rolls.size() - 3) {
                bonus += rolls.get(frameIndex + 1);
                bonus += rolls.get(frameIndex + 2);
            } else if (frameIndex == rolls.size() - 2) {
                bonus += rolls.get(frameIndex + 1);
            } else if (frameIndex < rolls.size() - 3) {
                bonus += rolls.get(frameIndex + 1);
                if (isStrike(rolls, frameIndex + 1)) {
                    bonus += rolls.get(frameIndex + 2);
                }
            }
        } else if (isSpare(rolls, frameIndex)) {
            bonus += rolls.get(frameIndex + 1);
        }
        return bonus;
    }

    public boolean isStrike(List<Integer> rolls, int frameIndex) {
        return frameIndex < rolls.size() && rolls.get(frameIndex) == 10;
    }

    public boolean isSpare(List<Integer> rolls, int frameIndex) {
        return !isStrike(rolls, frameIndex) && rolls.get(frameIndex) + rolls.get(frameIndex + 1) == 10;
    }

    public int getRoll1() {
        return roll1;
    }

    public int getRoll2() {
        return roll2;
    }
}

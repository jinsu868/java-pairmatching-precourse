package pairmatching.constant;

import java.util.Arrays;
import pairmatching.error.ErrorCode;

public enum Choice {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    QUIT("Q");

    private String selectedNumber;

    private Choice(String selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public static void isContain(String input) {
        Arrays.stream(Choice.values())
                .filter(choice -> input.equals(choice.getSelectedNumber()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String getSelectedNumber() {
        return selectedNumber;
    }
}

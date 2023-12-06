package pairmatching.constant;

import java.util.Arrays;
import pairmatching.error.ErrorCode;

public enum Choice {
    MATCHING("1"),
    RETRIEVE("2"),
    INIT("3"),
    QUIT("Q");

    private String selectedValue;

    private Choice(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public static Choice getChoiceByValue(String value) {
        return  Arrays.stream(Choice.values())
                .filter(choice -> value.equals(choice.getSelectedValue()))
                .findAny()
                .orElseThrow(() -> ErrorCode.INVALID_SELECT.getException());
    }

    public String getSelectedValue() {
        return selectedValue;
    }
}

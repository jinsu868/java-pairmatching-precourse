package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.constant.Level;

public class OutputView {
    private final static String FUNCTION_SELECT_MESSAGE = "기능을 선택하세요.\n"
            + "1. 페어 매칭\n"
            + "2. 페어 조회\n"
            + "3. 페어 초기화\n"
            + "Q. 종료";
    private final static String PAIR_RETRIEVE_INTRO_MESSAGE = "#############################################\n"
            + "과정: 백엔드 | 프론트엔드\n"
            + "미션:";
    private final static String PAIR_RETRIEVE_END_MESSAGE = "############################################\n"
            + "과정, 레벨, 미션을 선택하세요.\n"
            + "ex) 백엔드, 레벨1, 자동차경주\n";
    private final static String PREFIX_DASH = "  - ";
    private final static String COLON = ": ";
    private final static String DELIMITER = " | ";

    public void printFunctionSelectMessage() {
        System.out.println(FUNCTION_SELECT_MESSAGE);
    }

    public void printPairRetrieveIntroMessage() {
        System.out.println(PAIR_RETRIEVE_INTRO_MESSAGE);
        Arrays.stream(Level.values())
                        .map(level -> makePrintForm(level.getName(), level.getMissions()))
                                .forEach(form -> System.out.println(form));
        System.out.println(PAIR_RETRIEVE_END_MESSAGE);
    }

    private String makePrintForm(String name, List<String> missions) {
        String missionForm = missions.stream()
                .collect(Collectors.joining(DELIMITER));
        return PREFIX_DASH + name + COLON + missionForm;
    }
}

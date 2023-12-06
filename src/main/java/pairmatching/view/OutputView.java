package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.constant.Level;

public class OutputView {
    private final static String FUNCTION_SELECT_MESSAGE = "기능을 선택하세요.\n"
            + "1. 페어 매칭\n2. 페어 조회\n3. 페어 초기화\nQ. 종료";
    private final static String PAIR_RETRIEVE_INTRO_MESSAGE = "#############################################\n"
            + "과정: 백엔드 | 프론트엔드\n미션:";
    private final static String PAIR_RETRIEVE_END_MESSAGE = "############################################\n"
            + "과정, 레벨, 미션을 선택하세요.\nex) 백엔드, 레벨1, 자동차경주\n";
    private final static String MATCHING_RESULT_INTRO_MESSAGE = "페어 매칭 결과입니다.";
    private final static String REMATCHING_MESSAGE = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오";
    private final static String INIT_MESSAGE = "초기화 되었습니다. \n";
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

    public void printPairMatchingResultIntroMessage() {
        System.out.println(MATCHING_RESULT_INTRO_MESSAGE);
    }

    public void printReMatchingMessage() {
        System.out.println(REMATCHING_MESSAGE);
    }

    public void printInitMessage() {
        System.out.println(INIT_MESSAGE);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private String makePrintForm(String name, List<String> missions) {
        String missionForm = missions.stream()
                .collect(Collectors.joining(DELIMITER));
        return PREFIX_DASH + name + COLON + missionForm;
    }
}

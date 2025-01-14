package pairmatching.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pairmatching.constant.Choice;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.domain.MatchingManager;
import pairmatching.domain.Pair;
import pairmatching.dto.CrewCreateDto;
import pairmatching.dto.PairRetrieveDto;
import pairmatching.error.ErrorCode;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
    private final InputView inputView;
    private final OutputView outputView;

    public MatchingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Crews backendCrews = setBackendCrews();
        Crews frontendCrews = setFrontendCrews();
        MatchingManager matchingManager = new MatchingManager(backendCrews, frontendCrews);
        while (true) {
            Choice choice = getChoice();
            if (choice.equals(Choice.MATCHING)) {
                matchPairs(matchingManager);
            } else if (choice.equals(Choice.RETRIEVE)) {
                retrievePairs(matchingManager);
            } else if (choice.equals(Choice.INIT)) {
                matchingManager.initMatching();
                outputView.printInitMessage();
            } else if (choice.equals(Choice.QUIT)) {
                break;
            }
        }
    }

    private void retrievePairs(MatchingManager matchingManager) {
        while (true) {
            try {
                outputView.printPairRetrieveIntroMessage();
                PairRetrieveDto pairRetrieveDto = inputView.inputRetrieveInfo();
                Course course = Course.getCourse(pairRetrieveDto.getCourse());
                Level level = Level.getLevel(pairRetrieveDto.getLevel());
                String mission = pairRetrieveDto.getMission();
                if (!level.getMissions().contains(mission)) {
                    throw ErrorCode.MISSION_LEVEL_NOT_MATCH.getException();
                }
                List<Pair> pairs = matchingManager.retrieveMatchingResult(course, level, mission);
                outputView.printMatchingResult(pairs);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void matchPairs(MatchingManager matchingManager) {
        while (true) {
            try {
                outputView.printPairRetrieveIntroMessage();
                PairRetrieveDto pairRetrieveDto = inputView.inputRetrieveInfo();
                Course course = Course.getCourse(pairRetrieveDto.getCourse());
                Level level = Level.getLevel(pairRetrieveDto.getLevel());
                String mission = pairRetrieveDto.getMission();
                if (!level.getMissions().contains(mission)) {
                    throw ErrorCode.MISSION_LEVEL_NOT_MATCH.getException();
                }
                List<Pair> pairs = matchingManager.matchPair(course, level, mission);
                outputView.printMatchingResult(pairs);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private Crews setBackendCrews() {
        List<CrewCreateDto> backendCrewCreateDto = inputView.inputBackendCrewNames();
        List<Crew> crews = backendCrewCreateDto.stream()
                .map(dto -> new Crew(dto.getCourse(), dto.getName()))
                .collect(Collectors.toList());
        return new Crews(crews);
    }

    private Crews setFrontendCrews() {
        List<CrewCreateDto> frontendCrewCreateDto = inputView.inputFrontendCrewNames();
        List<Crew> crews = frontendCrewCreateDto.stream()
                .map(dto -> new Crew(dto.getCourse(), dto.getName()))
                .collect(Collectors.toList());
        return new Crews(crews);
    }

    private Choice getChoice() {
        while (true) {
            try {
                outputView.printFunctionSelectMessage();
                return Choice.getChoiceByValue(inputView.inputChoice());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}

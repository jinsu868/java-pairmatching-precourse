package pairmatching.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pairmatching.constant.Choice;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.dto.CrewCreateDto;
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
        
        while (true) {
            Choice choice = getChoice();
            if (choice.equals(Choice.MATCHING)) {
                System.out.println("matching");

            } else if (choice.equals(Choice.RETRIEVE)) {
                System.out.println("retrieve");
            } else if (choice.equals(Choice.INIT)) {
                System.out.println("init");
            } else if (choice.equals(Choice.QUIT)) {
                System.out.println("exit");
                break;
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

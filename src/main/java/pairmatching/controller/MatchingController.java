package pairmatching.controller;

import pairmatching.constant.Choice;
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

package pairmatching;

import pairmatching.controller.MatchingController;
import pairmatching.dto.PairRetrieveDto;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MatchingController matchingController = new MatchingController(new InputView(), new OutputView());
        matchingController.run();
    }
}

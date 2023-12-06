package pairmatching;

import pairmatching.dto.PairRetrieveDto;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        inputView.inputChoice();
        OutputView outputView = new OutputView();
        outputView.printPairRetrieveIntroMessage();
        PairRetrieveDto pairRetrieveDto = inputView.inputRetrieveInfo();

    }
}

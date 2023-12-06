package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.constant.Choice;
import pairmatching.dto.PairRetrieveDto;
import pairmatching.error.ErrorCode;

public class InputView {
    private static final String BACKEND_SOURCE = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_SOURCE = "src/main/resources/frontend-crew.md";
    private static final int RETRIEVE_INFO_SIZE = 3;
    private static final String DELIMITER = ", ";

    public List<String> inputBackendCrewNames() {
        try {
            return readCrewNameFromFile(BACKEND_SOURCE);
        } catch (FileNotFoundException e) {
            throw ErrorCode.FILE_NOT_EXIST.getException();
        }
    }

    public List<String> inputFrontendCrewNames() {
        try {
            return readCrewNameFromFile(FRONTEND_SOURCE);
        } catch (FileNotFoundException e) {
            throw ErrorCode.FILE_NOT_EXIST.getException();
        }
    }

    public String inputChoice() {
        String input = Console.readLine();
        return input;
    }

    public PairRetrieveDto inputRetrieveInfo() {
        String input = Console.readLine();
        return parseRetrieveInfo(input);
    }

    private PairRetrieveDto parseRetrieveInfo(String input) {
        String[] data = input.split(DELIMITER);
        if (data.length != RETRIEVE_INFO_SIZE) {
            throw ErrorCode.INVALID_RETRIEVE_INFO.getException();
        }
        return new PairRetrieveDto(data[0], data[1], data[2]);
    }

    private List<String> readCrewNameFromFile(String source) throws FileNotFoundException {
        File file = new File (BACKEND_SOURCE);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(fileReader);
        List<String> crewNames = new ArrayList<>();
        try {
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                crewNames.add(line.trim());
            }
            bufReader.close();
            return crewNames;
        } catch(IOException e) {
            throw ErrorCode.IO_FAIL.getException();
        }
    }
}

package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    private static final String BACKEND_SOURCE = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_SOURCE = "src/main/resources/frontend-crew.md";

    public List<String> inputBackendCrewNames() {
        try {
            return readCrewNameFromFile(BACKEND_SOURCE);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> inputFrontendCrewNames() {
        try {
            return readCrewNameFromFile(FRONTEND_SOURCE);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    public int inputNumber() {
        String input = Console.readLine();
        return parseInt(input);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
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
            throw new IllegalArgumentException();
        }
    }
}

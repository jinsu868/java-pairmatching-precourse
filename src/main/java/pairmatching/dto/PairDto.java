package pairmatching.dto;

import java.util.ArrayList;
import java.util.List;

public class PairDto {
    List<String> crewNames;

    public PairDto() {
        crewNames = new ArrayList<>();
    }

    public void addCrew(String crewName) {
        crewNames.add(crewName);
    }

    public List<String> getCrewNames() {
        return crewNames;
    }
}

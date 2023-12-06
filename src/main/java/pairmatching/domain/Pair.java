package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    List<String> crewNames;

    public Pair() {
        crewNames = new ArrayList<>();
    }

    public void addCrew(String crewName) {
        crewNames.add(crewName);
    }

    public List<String> getCrewNames() {
        return crewNames;
    }

    public boolean contains(String crewName) {
        return crewName.contains(crewName);
    }
}

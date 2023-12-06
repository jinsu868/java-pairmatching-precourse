package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.error.ErrorCode;

public class Matching {
    private final Course course;
    private final Level level;
    private final String mission;

    public Matching(Course course, Level level, String mission) {
        validateMission(level, mission);
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public List<Pair> matchPair(Crews crews) {
        List<Crew> shuffledCrews = crews.getShuffledCrews();
        List<Pair> pairs = new ArrayList<>();
        if (shuffledCrews.size() % 2 == 0) {
            for (int i = 0; i < shuffledCrews.size(); i += 2) {
                getPairDto(shuffledCrews, pairs, i);
            }
            return pairs;
        }
        for (int i = 0; i < shuffledCrews.size(); i += 2) {
            if (i+1 == shuffledCrews.size()) {
                List<String> crewNames = pairs.get(pairs.size() - 1).getCrewNames();
                crewNames.add(shuffledCrews.get(i+1).getName());
            }
            getPairDto(shuffledCrews, pairs, i);
        }
        return pairs;
    }

    private void getPairDto(List<Crew> shuffledCrews, List<Pair> pairs, int i) {
        Pair pair = new Pair();
        pair.addCrew(shuffledCrews.get(i).getName());
        pair.addCrew(shuffledCrews.get(i +1).getName());
        pairs.add(pair);
    }


    private void validateMission(Level level, String mission) {
        level.getMissions().stream()
                .filter(m -> m.equals(mission))
                .findAny()
                .orElseThrow(() -> ErrorCode.MISSION_LEVEL_NOT_MATCH.getException());
    }
}


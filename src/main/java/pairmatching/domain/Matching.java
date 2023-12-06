package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.dto.PairDto;
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

    public List<PairDto> matchPair(Crews crews) {
        List<Crew> shuffledCrews = crews.getShuffledCrews();
        List<PairDto> pairs = new ArrayList<>();
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

    private void getPairDto(List<Crew> shuffledCrews, List<PairDto> pairs, int i) {
        PairDto pairDto = new PairDto();
        pairDto.addCrew(shuffledCrews.get(i).getName());
        pairDto.addCrew(shuffledCrews.get(i +1).getName());
        pairs.add(pairDto);
    }


    private void validateMission(Level level, String mission) {
        level.getMissions().stream()
                .filter(m -> m.equals(mission))
                .findAny()
                .orElseThrow(() -> ErrorCode.MISSION_LEVEL_NOT_MATCH.getException());
    }
}


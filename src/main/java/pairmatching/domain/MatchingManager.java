package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.constant.Course;
import pairmatching.constant.Level;

public class MatchingManager {
    private List<MatchingResult> matchingResults;
    private Crews backendCrews;
    private Crews frontendCrews;

    public MatchingManager(Crews backendCrews, Crews frontendCrews) {
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
        matchingResults = new ArrayList<>();
    }

    public List<Pair> matchPair(Course course, Level level, String mission) {
        List<MatchingResult> matchingResultsByLevel = getMatchingResultsByLevel(level);
        Matching matching = new Matching(course, level, mission);
        boolean isMatch = true;
        for (int i = 0; i < 3; i++) {
            List<Pair> matchPairs = getMatchPairsByCourse(course, matching);
            for (MatchingResult matchingResult : matchingResultsByLevel) {
                List<Pair> pairs = matchingResult.getPairs();
                for (Pair pair : pairs) {
                    if (isPairDuplicateInSameLevel(matchPairs, pair)) {
                        isMatch = false;
                        break;
                    }
                }
            }
            if (isMatch == true) {

                MatchingResult matchingResult = new MatchingResult(matchPairs, level, course, mission);
                matchingResults.add(matchingResult);
                return matchingResult.getPairs();
            }
        }
        if (isMatch == false) {
            throw new IllegalStateException();
        }
        return new ArrayList<>();
    }

    public List<Pair> retrieveMatchingResult(Course course, Level level, String mission) {
        return matchingResults.stream()
                .filter(result -> result.getCourse() == course
                        && result.getLevel() == level
                        && result.getMission().equals(mission))
                .findAny()
                .map(result -> result.getPairs())
                .orElse(new ArrayList<>());
    }

    public void initMatching() {
        matchingResults = new ArrayList<>();
    }

    private List<Pair> getMatchPairsByCourse(Course course, Matching matching) {
        if (course == Course.BACKEND) {
            return matching.matchPair(backendCrews);
        }
        return matching.matchPair(frontendCrews);
    }

    private boolean isPairDuplicateInSameLevel(List<Pair> matchPairs, Pair pair) {
        boolean isDuplicate = true;
        for (Pair matchPair : matchPairs) {
            int matchCount = 0;
            List<String> crewNames = matchPair.getCrewNames();
            for (int i = 0; i < crewNames.size(); i++) {
                if (pair.contains(crewNames.get(i))) {
                    matchCount++;
                }
            }
            if (matchCount >= 2) {
                isDuplicate = false;
            }
        }
        return isDuplicate;
    }

    private List<MatchingResult> getMatchingResultsByLevel(Level level) {
        return matchingResults.stream()
                .filter(result -> result.getLevel().equals(level))
                .collect(Collectors.toList());
    }
}

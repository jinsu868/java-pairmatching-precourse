package pairmatching.domain;

import java.util.List;
import pairmatching.constant.Course;
import pairmatching.constant.Level;

public class MatchingResult {
    private List<Pair> pairs;
    private Level level;
    private Course course;
    private String mission;

    public MatchingResult(List<Pair> pairs, Level level, Course course, String mission) {
        this.pairs = pairs;
        this.level = level;
        this.course = course;
        this.mission = mission;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public Level getLevel() {
        return level;
    }
}

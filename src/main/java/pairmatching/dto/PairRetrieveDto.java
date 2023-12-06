package pairmatching.dto;

public class PairRetrieveDto {
    private String course;
    private String level;
    private String mission;

    public PairRetrieveDto(String course, String level, String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public String getCourse() {
        return course;
    }

    public String getLevel() {
        return level;
    }

    public String getMission() {
        return mission;
    }
}

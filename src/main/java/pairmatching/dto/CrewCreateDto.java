package pairmatching.dto;

import pairmatching.constant.Course;

public class CrewCreateDto {
    private String name;
    private Course course;

    public CrewCreateDto(String name, Course course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }
}

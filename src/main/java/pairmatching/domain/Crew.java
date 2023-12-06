package pairmatching.domain;

import pairmatching.constant.Course;

public class Crew {
    private Course course;
    private String name;

    public Crew(String course, String name) {
        this.course = Course.getCourse(course);
        this.name = name;
    }
}

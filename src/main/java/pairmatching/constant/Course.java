package pairmatching.constant;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    private Course(String name) {
        this.name = name;
    }

    public static Course getCourse(String course) {
        return Arrays.stream(Course.values())
                .filter(c -> c.getName().equals(course))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String getName() {
        return name;
    }
}

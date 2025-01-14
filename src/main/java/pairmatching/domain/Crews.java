package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pairmatching.error.ErrorCode;

public class Crews {
    private List<Crew> crews;

    public Crews(List<Crew> crews) {
        validateDuplication(crews);
        this.crews = crews;
    }

    public List<Crew> getShuffledCrews() {
        return Randoms.shuffle(crews);
    }

    private void validateDuplication(List<Crew> crews) {
        Set<String> check = new HashSet<>();
        for (Crew crew : crews) {
            if (check.contains(crew.getName())) {
                throw new IllegalStateException();
            }
        }
    }
}

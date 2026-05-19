package research.interfaces;

import research.models.*;
import research.models.ResearchPaper;
import research.exceptions.*;

import java.util.*;

public interface Researcher {

    List<ResearchPaper> getResearchPapers();

    default int calculateHIndex() {
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper p : getResearchPapers()) {
            citations.add(p.getCitations());
        }

        citations.sort(Collections.reverseOrder());

        int h = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                h = i + 1;
            }
        }
        return h;
    }

    default void checkSupervisorEligibility() throws LowHIndexException {
        if (calculateHIndex() < 3) {
            throw new LowHIndexException("H-index < 3: cannot be supervisor");
        }
    }

    default void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> list = new ArrayList<>(getResearchPapers());
        list.sort(comparator);
        list.forEach(System.out::println);
    }

    void publishPaper(ResearchPaper paper);
    void joinProject(ResearchProject project) throws NotResearcherException;
}
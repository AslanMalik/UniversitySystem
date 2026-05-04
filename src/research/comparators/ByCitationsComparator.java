package comparators;

import models.ResearchPaper;
import java.util.Comparator;

public class ByCitationsComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper a, ResearchPaper b) {
        return Integer.compare(b.getCitations(), a.getCitations());
    }
}
package research.comparators;

import models.ResearchPaper;
import java.util.Comparator;

public class ByPagesComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper a, ResearchPaper b) {
        return Integer.compare(a.getPages(), b.getPages());
    }
}
package research.comparators;

import models.ResearchPaper;
import java.util.Comparator;

public class ByDateComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper a, ResearchPaper b) {
        return a.getDatePublished().compareTo(b.getDatePublished());
    }
}
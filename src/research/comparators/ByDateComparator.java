package research.comparators;

import research.models.ResearchPaper;
import java.util.Comparator;

public class ByDateComparator implements Comparator<ResearchPaper> {
    public int compare(ResearchPaper a, ResearchPaper b) {
        return a.getDatePublished().compareTo(b.getDatePublished());
    }
}
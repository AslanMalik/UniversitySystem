package university.interfaces;

import university.exceptions.NotResearcherException;
import university.models.ResearchPaper;
import university.models.ResearchProject;

import java.util.Comparator;
import java.util.List;

public interface Researcher {

    void printPapers(Comparator<ResearchPaper> c);

    int calcHIndex();

    List<ResearchPaper> getResearchPapers();

    void joinProject(ResearchProject project) throws NotResearcherException;

    void publishPaper(ResearchPaper paper);
}

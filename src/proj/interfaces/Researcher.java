package proj.interfaces;

import proj.research.ResearchPaper;
import proj.research.ResearchProject;

import java.util.List;

public interface Researcher {
    int calculateHIndex();
    void printPapers(java.util.Comparator<ResearchPaper> comparator);
    void addResearchPaper(ResearchPaper paper);
    void addResearchProject(ResearchProject project);
    List<ResearchPaper> getResearchPapers();
    List<ResearchProject> getResearchProjects();
}
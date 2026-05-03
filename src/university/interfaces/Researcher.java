package university.interfaces;

import university.exceptions.NotResearcherException;
import university.models.ResearchPaper;
import university.models.ResearchProject;

import java.util.Comparator;
import java.util.List;

/**
 * Represents a member of the university who can conduct research,
 * publish papers, and participate in research projects.
 */
public interface Researcher {

    /**
     * Prints all research papers owned by this researcher, sorted by the given comparator.
     *
     * @param c the comparator used to order the papers
     */
    void printPapers(Comparator<ResearchPaper> c);

    /**
     * Calculates and returns the h-index of this researcher based on their published papers.
     * The h-index is the largest value h such that h papers each have at least h citations.
     *
     * @return the h-index value
     */
    int calcHIndex();

    /**
     * Returns the list of research papers published by this researcher.
     *
     * @return list of {@link ResearchPaper} objects
     */
    List<ResearchPaper> getResearchPapers();

    /**
     * Joins the given research project.
     *
     * @param project the project to join
     * @throws NotResearcherException if this object is not eligible to join the project
     */
    void joinProject(ResearchProject project) throws NotResearcherException;

    /**
     * Publishes (adds) a research paper to this researcher's publication list.
     *
     * @param paper the paper to publish
     */
    void publishPaper(ResearchPaper paper);
}

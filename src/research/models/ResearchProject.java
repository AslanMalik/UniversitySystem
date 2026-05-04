package models;

import interfaces.Researcher;
import exceptions.NotResearcherException;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a research project with a topic, published papers, and researcher participants.
 */
public class ResearchProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String topic;
    private List<ResearchPaper> papers = new ArrayList<>();
    private List<Researcher> participants = new ArrayList<>();

    /**
     * Constructs a ResearchProject with the given topic.
     *
     * @param topic the research topic or title of the project
     */
    public ResearchProject(String topic) {
        this.topic = topic;
    }

    // ==================== Getters ====================

    /** @return the research topic */
    public String getTopic() { return topic; }

    /** @return the list of published papers */
    public List<ResearchPaper> getPapers() { return papers; }

    /** @return the list of research participants */
    public List<Researcher> getParticipants() { return participants; }

    // ==================== Core Methods ====================

    /**
     * Adds a participant to the project. The object must implement {@link Researcher};
     * throws {@link NotResearcherException} otherwise.
     *
     * @param obj the person or entity to add as a participant
     * @throws NotResearcherException if {@code obj} does not implement {@link Researcher}
     */
    public void addParticipant(Object obj) throws NotResearcherException {
        if (!(obj instanceof Researcher)) {
            throw new NotResearcherException("Only researchers are allowed in a research project.");
        }
        participants.add((Researcher) obj);
    }

    /**
     * Publishes (adds) a research paper to this project.
     *
     * @param paper the paper to publish
     */
    public void publishPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    /**
     * Removes a researcher from the project's participant list.
     *
     * @param r the researcher to remove
     */
    public void removeParticipant(Researcher r) {
        participants.remove(r);
    }

    // ==================== Object overrides ====================

    @Override
    public String toString() {
        return "Project{" + topic + ", participants=" + participants.size()
            + ", papers=" + papers.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchProject)) return false;
        ResearchProject that = (ResearchProject) o;
        return Objects.equals(topic, that.topic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic);
    }
}

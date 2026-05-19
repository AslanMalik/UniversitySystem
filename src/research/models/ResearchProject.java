package models;

import interfaces.Researcher;
import exceptions.NotResearcherException;

import java.io.Serializable;
import java.util.*;

public class ResearchProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String topic;
    private List<ResearchPaper> papers = new ArrayList<>();
    private List<Researcher> participants = new ArrayList<>();

    public ResearchProject(String topic) {
        this.topic = topic;
    }

    public String getTopic() { return topic; }

    public List<ResearchPaper> getPapers() { return papers; }

    public List<Researcher> getParticipants() { return participants; }

    public void addParticipant(Object obj) throws NotResearcherException {
        if (!(obj instanceof Researcher)) {
            throw new NotResearcherException("Only researchers are allowed in a research project.");
        }
        participants.add((Researcher) obj);
    }

    public void publishPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public void removeParticipant(Researcher r) {
        participants.remove(r);
    }

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

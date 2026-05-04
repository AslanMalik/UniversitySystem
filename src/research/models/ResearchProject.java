package models;

import interfaces.Researcher;
import exceptions.NotResearcherException;

import java.util.*;

public class ResearchProject {
    private String topic;
    private List<ResearchPaper> papers = new ArrayList<>();
    private List<Researcher> participants = new ArrayList<>();

    public ResearchProject(String topic) {
        this.topic = topic;
    }

    public void addParticipant(Object obj) throws NotResearcherException {
        if (!(obj instanceof Researcher)) {
            throw new NotResearcherException("Only researchers allowed");
        }
        participants.add((Researcher) obj);
    }

    public void publishPaper(ResearchPaper p) {
        papers.add(p);
    }

    @Override
    public String toString() {
        return "Project{" + topic + "}";
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
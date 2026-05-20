package proj.research;

import proj.exceptions.NotResearcherException;
import proj.interfaces.Researcher;
import proj.models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject implements Serializable {
    private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<User> participants;
    
    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }
    
    public String getTopic() { return topic; }
    public List<ResearchPaper> getPublishedPapers() { return publishedPapers; }
    public List<User> getParticipants() { return participants; }
    
    public void addPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }
    
    public void addParticipant(User user) throws NotResearcherException {
        if (!(user instanceof Researcher)) {
            throw new NotResearcherException(user.getName() + " is not a researcher!");
        }
        participants.add(user);
    }
}
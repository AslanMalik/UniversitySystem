package proj;

import java.util.ArrayList;
import java.util.List;

public class ResearchJournal {
    private String name;
    private List<JournalSubscriber> subscribers;
    private List<ResearchPaper> papers;
    
    public ResearchJournal(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
        this.papers = new ArrayList<>();
    }
    
    public void subscribe(JournalSubscriber subscriber) {
        subscribers.add(subscriber);
    }
    
    public void unsubscribe(JournalSubscriber subscriber) {
        subscribers.remove(subscriber);
    }
    
    public void publishPaper(ResearchPaper paper) {
        papers.add(paper);
        notifySubscribers(paper);
    }
    
    private void notifySubscribers(ResearchPaper paper) {
        for (JournalSubscriber subscriber : subscribers) {
            subscriber.update(name, paper);
        }
    }
    
    public String getName() { return name; }
}
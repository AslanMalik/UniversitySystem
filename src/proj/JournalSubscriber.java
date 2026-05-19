package proj;

public interface JournalSubscriber {
    void update(String journalName, ResearchPaper newPaper);
}
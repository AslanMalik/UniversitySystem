package proj.interfaces;

import proj.research.ResearchPaper;

public interface JournalSubscriber {
    void update(String journalName, ResearchPaper newPaper);
}
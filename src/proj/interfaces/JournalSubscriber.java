package proj.interfaces;

import proj.models.research.ResearchPaper;

public interface JournalSubscriber {
    void update(String journalName, ResearchPaper newPaper);
}
package interfaces;

import models.ResearchPaper;

public interface Subscriber {
    void update(ResearchPaper paper);
}
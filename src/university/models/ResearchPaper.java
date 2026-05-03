package university.models;

import java.io.Serializable;

/**
 * Placeholder — implemented by Participant 3.
 * Represents a published academic research paper.
 */
public class ResearchPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private int citations;

    /** @param title paper title */
    public ResearchPaper(String title, int citations) {
        this.title = title;
        this.citations = citations;
    }

    /** @return paper title */
    public String getTitle() { return title; }

    /** @return number of citations */
    public int getCitations() { return citations; }

    @Override
    public String toString() {
        return "ResearchPaper{title='" + title + "', citations=" + citations + '}';
    }
}

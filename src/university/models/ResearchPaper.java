package university.models;

import java.io.Serializable;

public class ResearchPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private int citations;

    public ResearchPaper(String title, int citations) {
        this.title = title;
        this.citations = citations;
    }

    public String getTitle() { return title; }

    public int getCitations() { return citations; }

    @Override
    public String toString() {
        return "ResearchPaper{title='" + title + "', citations=" + citations + '}';
    }
}

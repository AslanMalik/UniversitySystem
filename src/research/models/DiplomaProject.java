package models;

import java.util.*;

public class DiplomaProject {
    private String title;
    private List<ResearchPaper> papers = new ArrayList<>();

    public DiplomaProject(String title) {
        this.title = title;
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    @Override
    public String toString() {
        return "Diploma{" + title + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiplomaProject)) return false;
        DiplomaProject that = (DiplomaProject) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
package models;

import interfaces.Researcher;

import java.io.Serializable;
import java.util.*;

public class DiplomaProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private Researcher student;
    private Researcher supervisor;
    private List<ResearchPaper> papers = new ArrayList<>();

    public DiplomaProject(String title, Researcher student, Researcher supervisor) {
        this.title = title;
        this.student = student;
        this.supervisor = supervisor;
    }

    public DiplomaProject(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Researcher getStudent() { return student; }

    public void setStudent(Researcher student) { this.student = student; }

    public Researcher getSupervisor() { return supervisor; }

    public void setSupervisor(Researcher supervisor) { this.supervisor = supervisor; }

    public List<ResearchPaper> getPapers() { return papers; }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    public boolean isComplete() {
        return papers.size() >= 1;
    }

    @Override
    public String toString() {
        return "Diploma{" + title
            + ", complete=" + isComplete()
            + ", papers=" + papers.size() + "}";
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

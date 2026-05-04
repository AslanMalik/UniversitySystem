package models;

import interfaces.Researcher;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a diploma (graduation) project authored by a graduate student
 * under the supervision of a researcher.
 * The project is considered complete when at least one research paper is attached.
 */
public class DiplomaProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    /**
     * The graduate student authoring this diploma project.
     * Typed as {@link Researcher} since GraduateStudent implements Researcher.
     */
    private Researcher student;
    /** The academic supervisor overseeing the diploma project. */
    private Researcher supervisor;
    /** Research papers attached to this project (at least one required for completion). */
    private List<ResearchPaper> papers = new ArrayList<>();

    /**
     * Constructs a DiplomaProject with a title, student, and supervisor.
     *
     * @param title      the title of the diploma project
     * @param student    the graduate student (must implement {@link Researcher})
     * @param supervisor the supervising researcher
     */
    public DiplomaProject(String title, Researcher student, Researcher supervisor) {
        this.title = title;
        this.student = student;
        this.supervisor = supervisor;
    }

    /**
     * Constructs a DiplomaProject with only a title (student/supervisor assigned later).
     *
     * @param title the title of the diploma project
     */
    public DiplomaProject(String title) {
        this.title = title;
    }

    // ==================== Getters / Setters ====================

    /** @return the diploma project title */
    public String getTitle() { return title; }

    /** @param title the new title */
    public void setTitle(String title) { this.title = title; }

    /** @return the graduate student author */
    public Researcher getStudent() { return student; }

    /** @param student the graduate student to set */
    public void setStudent(Researcher student) { this.student = student; }

    /** @return the academic supervisor */
    public Researcher getSupervisor() { return supervisor; }

    /** @param supervisor the supervisor to set */
    public void setSupervisor(Researcher supervisor) { this.supervisor = supervisor; }

    /** @return the list of attached research papers */
    public List<ResearchPaper> getPapers() { return papers; }

    // ==================== Core Methods ====================

    /**
     * Attaches a research paper to this diploma project.
     *
     * @param paper the paper to add
     */
    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    /**
     * Returns {@code true} if the project has at least one research paper attached,
     * which is the minimum requirement for a complete diploma project.
     *
     * @return {@code true} if at least one paper is present
     */
    public boolean isComplete() {
        return papers.size() >= 1;
    }

    // ==================== Object overrides ====================

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

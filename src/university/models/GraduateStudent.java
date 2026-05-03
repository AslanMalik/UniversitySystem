package university.models;

import university.enums.Degree;
import university.enums.Language;
import university.exceptions.LowHIndexException;
import university.exceptions.NotResearcherException;
import university.interfaces.Researcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents a graduate (Master's or PhD) student who also acts as a researcher.
 * Extends {@link Student} and implements the {@link Researcher} interface, enabling
 * publication tracking and h-index calculation.
 */
public class GraduateStudent extends Student implements Researcher, Serializable {

    private static final long serialVersionUID = 1L;

    /** Minimum h-index a supervisor must have to be assigned. */
    private static final int MIN_SUPERVISOR_H_INDEX = 3;

    private Degree degree;
    private Researcher supervisor;
    private DiplomaProject diploma;
    private List<ResearchPaper> papers = new ArrayList<>();

    /**
     * Constructs a GraduateStudent with the given credentials and degree level.
     *
     * @param login     unique login name
     * @param password  password
     * @param language  preferred language
     * @param firstName first name
     * @param lastName  last name
     * @param degree    degree level ({@link Degree#MASTER} or {@link Degree#PHD})
     */
    public GraduateStudent(String login, String password, Language language,
                           String firstName, String lastName, Degree degree) {
        super(login, password, language, firstName, lastName);
        this.degree = degree;
    }

    /**
     * Assigns a research supervisor to this graduate student.
     * The supervisor's h-index must be at least {@value #MIN_SUPERVISOR_H_INDEX}.
     *
     * @param supervisor the researcher to assign as supervisor
     * @throws LowHIndexException if the supervisor's h-index is below the minimum threshold
     */
    public void setSupervisor(Researcher supervisor) {
        if (supervisor.calcHIndex() < MIN_SUPERVISOR_H_INDEX) {
            throw new LowHIndexException(
                    "Supervisor's h-index (" + supervisor.calcHIndex() +
                    ") is below the required minimum of " + MIN_SUPERVISOR_H_INDEX + ".");
        }
        this.supervisor = supervisor;
    }

    // -------------------------------------------------------------------------
    // Researcher interface implementation
    // -------------------------------------------------------------------------

    /**
     * Prints all research papers sorted by the given comparator.
     *
     * @param c comparator defining the sort order
     */
    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream()
              .sorted(c)
              .forEach(System.out::println);
    }

    /**
     * Calculates the h-index using the standard algorithm:
     * the largest value {@code h} such that {@code h} papers each have at least {@code h} citations.
     *
     * @return the h-index value
     */
    @Override
    public int calcHIndex() {
        List<Integer> citations = new ArrayList<>();
        for (ResearchPaper paper : papers) {
            citations.add(paper.getCitations());
        }
        citations.sort(Comparator.reverseOrder());

        int h = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    /**
     * Returns the list of research papers published by this graduate student.
     *
     * @return list of {@link ResearchPaper}
     */
    @Override
    public List<ResearchPaper> getResearchPapers() {
        return papers;
    }

    /**
     * Joins a research project. Throws {@link NotResearcherException} if the project
     * does not accept graduate students as members.
     *
     * @param project the project to join
     * @throws NotResearcherException if this student cannot join the project
     */
    @Override
    public void joinProject(ResearchProject project) throws NotResearcherException {
        project.addMember(this);
        System.out.println("Joined research project: " + project.getName());
    }

    /**
     * Publishes a research paper by adding it to this student's paper list.
     *
     * @param paper the paper to publish
     */
    @Override
    public void publishPaper(ResearchPaper paper) {
        papers.add(paper);
        System.out.println("Paper published: " + paper.getTitle());
    }

    /**
     * Displays the graduate student menu options.
     */
    @Override
    public void displayMenu() {
        System.out.println("=== Graduate Student Menu ===");
        System.out.println("1. View marks / transcript");
        System.out.println("2. Register for a course");
        System.out.println("3. Rate a teacher");
        System.out.println("4. Publish a research paper");
        System.out.println("5. View research papers");
        System.out.println("6. Join a research project");
        System.out.println("7. View h-index");
        System.out.println("8. View diploma project");
        System.out.println("0. Logout");
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

    /** @return degree level */
    public Degree getDegree() { return degree; }

    /** @param degree new degree level */
    public void setDegree(Degree degree) { this.degree = degree; }

    /** @return assigned supervisor (may be null) */
    public Researcher getSupervisor() { return supervisor; }

    /** @return the diploma project (may be null) */
    public DiplomaProject getDiploma() { return diploma; }

    /** @param diploma the diploma project to assign */
    public void setDiploma(DiplomaProject diploma) { this.diploma = diploma; }

    /** @param papers new list of research papers */
    public void setPapers(List<ResearchPaper> papers) { this.papers = papers; }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "GraduateStudent{" +
                "login='" + getLogin() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", degree=" + degree +
                ", papers=" + papers.size() +
                ", hIndex=" + calcHIndex() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraduateStudent)) return false;
        if (!super.equals(o)) return false;
        GraduateStudent that = (GraduateStudent) o;
        return degree == that.degree &&
                Objects.equals(diploma, that.diploma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), degree, diploma);
    }
}

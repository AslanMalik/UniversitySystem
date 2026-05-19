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

public class GraduateStudent extends Student implements Researcher, Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MIN_SUPERVISOR_H_INDEX = 3;

    private Degree degree;
    private Researcher supervisor;
    private DiplomaProject diploma;
    private List<ResearchPaper> papers = new ArrayList<>();

    public GraduateStudent(String login, String password, Language language,
                           String firstName, String lastName, Degree degree) {
        super(login, password, language, firstName, lastName);
        this.degree = degree;
    }

    public void setSupervisor(Researcher supervisor) {
        if (supervisor.calcHIndex() < MIN_SUPERVISOR_H_INDEX) {
            throw new LowHIndexException(
                    "Supervisor's h-index (" + supervisor.calcHIndex() +
                    ") is below the required minimum of " + MIN_SUPERVISOR_H_INDEX + ".");
        }
        this.supervisor = supervisor;
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> c) {
        papers.stream()
              .sorted(c)
              .forEach(System.out::println);
    }

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

    @Override
    public List<ResearchPaper> getResearchPapers() {
        return papers;
    }

    @Override
    public void joinProject(ResearchProject project) throws NotResearcherException {
        project.addMember(this);
        System.out.println("Joined research project: " + project.getName());
    }

    @Override
    public void publishPaper(ResearchPaper paper) {
        papers.add(paper);
        System.out.println("Paper published: " + paper.getTitle());
    }

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

    public Degree getDegree() { return degree; }

    public void setDegree(Degree degree) { this.degree = degree; }

    public Researcher getSupervisor() { return supervisor; }

    public DiplomaProject getDiploma() { return diploma; }

    public void setDiploma(DiplomaProject diploma) { this.diploma = diploma; }

    public void setPapers(List<ResearchPaper> papers) { this.papers = papers; }

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

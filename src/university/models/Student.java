package university.models;

import university.enums.Language;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an undergraduate student in the university system.
 * Tracks academic progress (GPA, credits, transcript) and extracurricular memberships.
 */
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Maximum allowed credits per term. */
    private static final int MAX_CREDITS = 21;
    /** Maximum number of failed courses before academic dismissal. */
    private static final int MAX_FAIL_COUNT = 3;

    private String firstName;
    private String lastName;
    private double gpa = 0.0;
    private int credits = 0;
    private List<Mark> transcript = new ArrayList<>();
    private int failCount = 0;
    private List<String> organizations = new ArrayList<>();

    /**
     * Constructs a Student with basic identity and credential information.
     *
     * @param login     unique login name
     * @param password  password
     * @param language  preferred language
     * @param firstName student's first name
     * @param lastName  student's last name
     */
    public Student(String login, String password, Language language,
                   String firstName, String lastName) {
        super(login, password, language);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Registers the student for a course, adding that course's credits to the total.
     * Throws {@link IllegalStateException} if the credit limit of 21 would be exceeded.
     *
     * @param course the course to register for
     * @throws IllegalStateException if credits >= {@value #MAX_CREDITS}
     */
    public void registerCourse(Course course) {
        if (credits >= MAX_CREDITS) {
            throw new IllegalStateException(
                    "Cannot register: credit limit of " + MAX_CREDITS + " already reached.");
        }
        credits += course.getCredits();
        System.out.println("Registered for course: " + course.getName());
    }

    /**
     * Adds a mark to the student's transcript.
     * If the mark is not a pass, the fail counter is incremented.
     *
     * @param mark the mark to record
     */
    public void addMark(Mark mark) {
        transcript.add(mark);
        if (!mark.isPassed()) {
            failCount++;
        }
    }

    /**
     * Returns an unmodifiable view of the student's transcript.
     *
     * @return list of marks
     */
    public List<Mark> viewMarks() {
        return List.copyOf(transcript);
    }

    /**
     * Returns a formatted string listing all marks in the transcript.
     *
     * @return multi-line transcript string
     */
    public String getTranscript() {
        StringBuilder sb = new StringBuilder("Transcript for " + firstName + " " + lastName + ":\n");
        for (Mark mark : transcript) {
            sb.append("  ").append(mark).append("\n");
        }
        return sb.toString();
    }

    /**
     * Rates a teacher by delegating to {@link Teacher#addRating(double)}.
     *
     * @param teacher the teacher to rate
     * @param rating  the rating value to submit
     */
    public void rateTeacher(Teacher teacher, double rating) {
        teacher.addRating(rating);
        System.out.println("Rating submitted for: " + teacher.getFirstName() + " " + teacher.getLastName());
    }

    /**
     * Joins a student organization by name.
     *
     * @param orgName name of the organization to join
     */
    public void joinOrganization(String orgName) {
        organizations.add(orgName);
        System.out.println("Joined organization: " + orgName);
    }

    /**
     * Displays the student-specific menu options.
     */
    @Override
    public void displayMenu() {
        System.out.println("=== Student Menu ===");
        System.out.println("1. View marks / transcript");
        System.out.println("2. Register for a course");
        System.out.println("3. Rate a teacher");
        System.out.println("4. Join an organization");
        System.out.println("5. View organizations");
        System.out.println("0. Logout");
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

    /** @return first name */
    public String getFirstName() { return firstName; }

    /** @param firstName new first name */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /** @return last name */
    public String getLastName() { return lastName; }

    /** @param lastName new last name */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /** @return current GPA */
    public double getGpa() { return gpa; }

    /** @param gpa new GPA value */
    public void setGpa(double gpa) { this.gpa = gpa; }

    /** @return number of credits registered */
    public int getCredits() { return credits; }

    /** @param credits new credits count */
    public void setCredits(int credits) { this.credits = credits; }

    /** @return full transcript list */
    public List<Mark> getTranscriptList() { return transcript; }

    /** @return number of failed courses */
    public int getFailCount() { return failCount; }

    /** @param failCount new fail count */
    public void setFailCount(int failCount) { this.failCount = failCount; }

    /** @return list of joined organization names */
    public List<String> getOrganizations() { return organizations; }

    /** @param organizations new organizations list */
    public void setOrganizations(List<String> organizations) { this.organizations = organizations; }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Student{" +
                "login='" + getLogin() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gpa=" + gpa +
                ", credits=" + credits +
                ", failCount=" + failCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Double.compare(student.gpa, gpa) == 0 &&
                credits == student.credits &&
                failCount == student.failCount &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, gpa, credits, failCount);
    }
}

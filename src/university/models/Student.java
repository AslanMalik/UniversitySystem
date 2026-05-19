package university.models;

import university.enums.Language;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int MAX_CREDITS = 21;
    private static final int MAX_FAIL_COUNT = 3;

    private String firstName;
    private String lastName;
    private double gpa = 0.0;
    private int credits = 0;
    private List<Mark> transcript = new ArrayList<>();
    private int failCount = 0;
    private List<String> organizations = new ArrayList<>();

    public Student(String login, String password, Language language,
                   String firstName, String lastName) {
        super(login, password, language);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void registerCourse(Course course) {
        if (credits >= MAX_CREDITS) {
            throw new IllegalStateException(
                    "Cannot register: credit limit of " + MAX_CREDITS + " already reached.");
        }
        credits += course.getCredits();
        System.out.println("Registered for course: " + course.getName());
    }

    public void addMark(Mark mark) {
        transcript.add(mark);
        if (!mark.isPassed()) {
            failCount++;
        }
    }

    public List<Mark> viewMarks() {
        return List.copyOf(transcript);
    }

    public String getTranscript() {
        StringBuilder sb = new StringBuilder("Transcript for " + firstName + " " + lastName + ":\n");
        for (Mark mark : transcript) {
            sb.append("  ").append(mark).append("\n");
        }
        return sb.toString();
    }

    public void rateTeacher(Teacher teacher, double rating) {
        teacher.addRating(rating);
        System.out.println("Rating submitted for: " + teacher.getFirstName() + " " + teacher.getLastName());
    }

    public void joinOrganization(String orgName) {
        organizations.add(orgName);
        System.out.println("Joined organization: " + orgName);
    }

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

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getGpa() { return gpa; }

    public void setGpa(double gpa) { this.gpa = gpa; }

    public int getCredits() { return credits; }

    public void setCredits(int credits) { this.credits = credits; }

    public List<Mark> getTranscriptList() { return transcript; }

    public int getFailCount() { return failCount; }

    public void setFailCount(int failCount) { this.failCount = failCount; }

    public List<String> getOrganizations() { return organizations; }

    public void setOrganizations(List<String> organizations) { this.organizations = organizations; }

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

package proj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Represents a teacher (academic employee) in the university system.
 * Implements {@link Researcher} to support research activities.
 */
public class Teacher extends Employee implements Researcher, Serializable {

    private static final long serialVersionUID = 1L;

    /** Legacy status field retained for backward compatibility. */
    private Status teacherStatus;
    /** Academic title of the teacher. */
    private TeacherTitle title;
    private Vector<Course> courses;
    private String experience;
    private double rating;
    private int ratingCnt;
    private List<Complaint> complaints;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public Teacher() {}

    /**
     * Constructs a Teacher using the legacy {@link Status} type.
     *
     * @param name          first name
     * @param surname       last name
     * @param birthDate     date of birth
     * @param phoneNumber   contact phone
     * @param email         contact email
     * @param password      account password
     * @param teacherStatus legacy status value
     * @param experience    description of work experience
     */
    public Teacher(String name, String surname, String birthDate, String phoneNumber, String email,
                   String password, Status teacherStatus, String experience) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.teacherStatus = teacherStatus;
        this.courses = new Vector<>();
        this.experience = experience;
        this.rating = 0.0;
        this.ratingCnt = 0;
        this.complaints = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    /**
     * Constructs a Teacher using the new {@link TeacherTitle} type.
     *
     * @param name        first name
     * @param surname     last name
     * @param birthDate   date of birth
     * @param phoneNumber contact phone
     * @param email       contact email
     * @param password    account password
     * @param title       academic title
     * @param experience  description of work experience
     */
    public Teacher(String name, String surname, String birthDate, String phoneNumber, String email,
                   String password, TeacherTitle title, String experience) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.title = title;
        this.courses = new Vector<>();
        this.experience = experience;
        this.rating = 0.0;
        this.ratingCnt = 0;
        this.complaints = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    // ==================== Getters / Setters ====================

    /** @return the current average rating */
    public double getRating() { return rating; }

    /** @param rating raw rating value to set directly */
    public void setRating(double rating) { this.rating = rating; }

    /** @return the number of ratings received */
    public int getRatingCnt() { return ratingCnt; }

    /** @param ratingCnt new rating count */
    public void setRatingCnt(int ratingCnt) { this.ratingCnt = ratingCnt; }

    /** @return legacy teacher status */
    public Status getTeacherStatus() { return teacherStatus; }

    /** @param teacherStatus legacy status to set */
    public void setTeacherStatus(Status teacherStatus) { this.teacherStatus = teacherStatus; }

    /** @return academic title */
    public TeacherTitle getTitle() { return title; }

    /** @param title academic title to set */
    public void setTitle(TeacherTitle title) { this.title = title; }

    /** @return list of assigned courses */
    public Vector<Course> getCourses() { return courses; }

    /** @param courses new list of courses */
    public void setCourses(Vector<Course> courses) { this.courses = courses; }

    /** @return work experience description */
    public String getExperience() { return experience; }

    /** @param experience new experience description */
    public void setExperience(String experience) { this.experience = experience; }

    /** @return list of complaints filed by this teacher */
    public List<Complaint> getComplaints() { return complaints; }

    // ==================== Researcher Interface ====================

    /**
     * Calculates the H-index from the teacher's research papers.
     *
     * @return the H-index value
     */
    @Override
    public int calculateHIndex() {
        researchPapers.sort((a, b) -> Integer.compare(b.getCitations(), a.getCitations()));
        int h = 0;
        for (int i = 0; i < researchPapers.size(); i++) {
            if (researchPapers.get(i).getCitations() >= i + 1) h = i + 1;
            else break;
        }
        return h;
    }

    /**
     * Prints all research papers sorted by the given comparator.
     *
     * @param comparator the comparator to sort papers by
     */
    @Override
    public void printPapers(java.util.Comparator<ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (ResearchPaper paper : researchPapers) System.out.println(paper);
    }

    /**
     * Adds a research paper and publishes a news item about it.
     *
     * @param paper the paper to add
     */
    @Override
    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
        News news = new News("paper_" + System.currentTimeMillis(),
                "New Research Paper Published",
                getName() + " published: " + paper.getTitle());
        Database.news.add(news);
    }

    /**
     * Adds a research project to the teacher's project list.
     *
     * @param project the project to add
     */
    @Override
    public void addResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }

    /** @return the teacher's research papers */
    @Override
    public List<ResearchPaper> getResearchPapers() { return researchPapers; }

    /** @return the teacher's research projects */
    @Override
    public List<ResearchProject> getResearchProjects() { return researchProjects; }

    // ==================== Core Teacher Methods ====================

    /**
     * Records a mark for a student and persists it in the database.
     *
     * @param student the student receiving the mark
     * @param mark    the mark to assign
     */
    public void putMark(Student student, Mark mark) {
        Database.marks.add(mark);
    }

    /**
     * Legacy overload — prefer {@link #putMark(Student, Mark)}.
     *
     * @param courseName  course name
     * @param studentId   student identifier
     * @param firstAtt    first attestation score
     * @param secondAtt   second attestation score
     * @param finalGrade  final exam score
     */
    public void putMark(String courseName, String studentId,
                        Double firstAtt, Double secondAtt, Double finalGrade) {
        Mark m = new Mark(courseName, studentId, firstAtt, secondAtt, finalGrade);
        Database.marks.add(m);
    }

    /**
     * Sends a complaint about each student in the list to the dean's office.
     *
     * @param students list of students to complain about
     * @param urgency  the urgency level of the complaint
     */
    public void sendComplaint(List<Student> students, UrgencyLevel urgency) {
        for (Student student : students) {
            Complaint complaint = new Complaint(getName(), student.getName(), "", urgency);
            complaints.add(complaint);
            System.out.println("Complaint sent to dean: " + complaint);
        }
    }

    /**
     * Legacy overload — prefer {@link #sendComplaint(List, UrgencyLevel)}.
     *
     * @param studentName name of the student
     * @param reason      reason for the complaint
     * @param urgency     urgency level
     */
    public void sendComplaint(String studentName, String reason, UrgencyLevel urgency) {
        Complaint complaint = new Complaint(getName(), studentName, reason, urgency);
        complaints.add(complaint);
        System.out.println("Complaint sent to dean: " + complaint);
    }

    /**
     * Incorporates a new rating value using a running-average formula:
     * {@code avg = (old_avg * count + r) / (count + 1)}.
     *
     * @param r the new rating value (e.g., 1–5)
     */
    public void addRating(double r) {
        this.rating = (this.rating * this.ratingCnt + r) / (this.ratingCnt + 1);
        this.ratingCnt++;
    }

    /**
     * Legacy accumulator — kept for backward compatibility with {@code Student.rateTeacher}.
     * Prefer {@link #addRating(double)} which correctly computes the running average.
     *
     * @param rating raw integer rating to add to accumulator
     */
    public void increaseRating(int rating) {
        this.ratingCnt++;
        this.rating += rating;
    }

    /**
     * Returns the current average rating, or 0.0 if no ratings have been received.
     * When ratings were added via {@link #addRating}, this returns the running average directly.
     * When ratings were added via the legacy {@link #increaseRating}, divide by ratingCnt externally.
     *
     * @return the average rating
     */
    public double viewRating() {
        if (ratingCnt == 0) return 0.0;
        return rating;
    }

    /**
     * Generates a text report listing all students enrolled in the given course.
     *
     * @param course the course to generate the report for
     * @return a formatted multi-line report string
     */
    public String generateReport(Course course) {
        StringBuilder sb = new StringBuilder("Report for course: ")
                .append(course.getCourseName()).append("\n");
        for (Student st : course.getStudents()) {
            sb.append("  ").append(st.getName())
              .append(" ").append(st.getSurname()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the teacher's interactive menu options to standard output.
     */
    public void displayMenu() {
        System.out.println("=== Teacher Menu ===");
        System.out.println("1. View students");
        System.out.println("2. View student info");
        System.out.println("3. Put mark");
        System.out.println("4. View marks");
        System.out.println("5. View courses");
        System.out.println("6. Send complaint");
        System.out.println("7. Generate report");
        System.out.println("8. Add course file");
        System.out.println("9. View messages");
        System.out.println("0. Log out");
    }

    /**
     * Returns a formatted string with all teacher information including H-index.
     *
     * @return full info string
     */
    public String getAllInfo() {
        return "{" +
            " name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", email='" + getEmail() + "'" +
            ", teacherStatus='" + getTeacherStatus() + "'" +
            ", title='" + getTitle() + "'" +
            ", experience='" + getExperience() + "'" +
            ", h-index='" + calculateHIndex() + "'" +
            "}";
    }

    // ==================== File Management ====================

    /**
     * Uploads a course material file to the database.
     *
     * @param fileName    name of the file
     * @param courseId    identifier of the associated course
     * @param description brief description of the file content
     */
    public void addCourseFile(String fileName, String courseId, String description) {
        File f = new File(fileName, courseId, description);
        Database.files.add(f);
    }

    /**
     * Removes a course material file from the database by file name and course ID.
     *
     * @param fileName name of the file to remove
     * @param courseId identifier of the associated course
     */
    public void deleteCourseFile(String fileName, String courseId) {
        Database.files.removeIf(file ->
            file.getFileName().equals(fileName) && file.getCourseId().equals(courseId));
    }

    // ==================== View Helpers ====================

    /**
     * Returns a formatted list of all non-graduate students in the system.
     *
     * @return multi-line student listing
     */
    public String viewStudents() {
        int i = 0;
        String ans = "";
        for (User user : Database.users) {
            if (user instanceof Student && !(user instanceof GraduateStudent)) {
                Student st = (Student) user;
                i++;
                ans += i + ") Student Name: " + st.getName()
                    + "\n    Student surname: " + st.getSurname()
                    + "\n    Birth Date: " + st.getBirthDate()
                    + "\n    Email: " + st.getEmail()
                    + "\n    ID: " + st.getId()
                    + "\n    Year of Study: " + st.getYearOfStudy()
                    + "\n    Faculty: " + st.getFaculty()
                    + "\n    Degree: " + st.getDegree() + "\n\n";
            }
        }
        return ans;
    }

    /**
     * Returns detailed information about a student found by first name.
     *
     * @param name the student's first name to search for
     * @return formatted student info, or empty string if not found
     */
    public String viewStudentInfo(String name) {
        String ans = "";
        for (User user : Database.users) {
            if (user instanceof Student) {
                Student st = (Student) user;
                if (st.getName().equals(name)) {
                    ans += "    Student Name: " + st.getName()
                        + "\n    Student surname: " + st.getSurname()
                        + "\n    Birth Date: " + st.getBirthDate()
                        + "\n    Email: " + st.getEmail()
                        + "\n    ID: " + st.getId()
                        + "\n    Year of Study: " + st.getYearOfStudy()
                        + "\n    Faculty: " + st.getFaculty()
                        + "\n    Degree: " + st.getDegree() + "\n\n";
                }
            }
        }
        return ans;
    }

    /**
     * Returns all marks for the specified course as a formatted string.
     *
     * @param courseName the name of the course to retrieve marks for
     * @return formatted marks listing
     */
    public String viewMarks(String courseName) {
        int i = 0;
        String s = "";
        for (Mark mark : Database.marks) {
            if (mark.getCourseName().equals(courseName)) {
                i++;
                s += i + ") Student Id: " + mark.getStudentId()
                    + "\n    First Att.: " + mark.getFirstAtt()
                    + "\n    Second Att.: " + mark.getSecondAtt()
                    + "\n    Final Grade: " + mark.getFinalGrade()
                    + "\n    Total Grade: " + mark.getTotal()
                    + " " + mark.convertToLetter()
                    + " " + mark.convertToGPA() + "\n\n";
            }
        }
        return s;
    }

    /**
     * Returns all courses in the system as a formatted string.
     *
     * @return formatted course listing
     */
    public String viewCourses() {
        String s = "";
        int i = 0;
        for (Course course : Database.courses) {
            i++;
            s += i + ") Course Name: " + course.getCourseName()
                + "\n    Description: " + course.getDescription()
                + "\n    Course ID: " + course.getCourseId() + "\n\n";
        }
        return s;
    }

    // ==================== Object overrides ====================

    @Override
    public String toString() {
        return "Teacher{" +
            "name='" + getName() + '\'' +
            ", surname='" + getSurname() + '\'' +
            ", title=" + title +
            ", teacherStatus=" + teacherStatus +
            ", experience='" + experience + '\'' +
            ", rating=" + viewRating() +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getEmail(), teacher.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}

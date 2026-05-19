package proj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Teacher extends Employee implements Researcher, Serializable {

    private static final long serialVersionUID = 1L;

    private Status teacherStatus;
    private TeacherTitle title;
    private List<Course> courses;
    private String experience;
    private double rating;
    private int ratingCnt;
    private List<Complaint> complaints;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public Teacher() {}

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

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getRatingCnt() { return ratingCnt; }

    public void setRatingCnt(int ratingCnt) { this.ratingCnt = ratingCnt; }

    public Status getTeacherStatus() { return teacherStatus; }

    public void setTeacherStatus(Status teacherStatus) { this.teacherStatus = teacherStatus; }

    public TeacherTitle getTitle() { return title; }

    public void setTitle(TeacherTitle title) { this.title = title; }

    public List<Course> getCourses() { return courses; }

    public void setCourses(List<Course> courses) { this.courses = courses; }

    public String getExperience() { return experience; }

    public void setExperience(String experience) { this.experience = experience; }

    public List<Complaint> getComplaints() { return complaints; }

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

    @Override
    public void printPapers(java.util.Comparator<ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (ResearchPaper paper : researchPapers) System.out.println(paper);
    }

    @Override
    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
        News news = new News("paper_" + System.currentTimeMillis(),
                "New Research Paper Published",
                getName() + " published: " + paper.getTitle());
        Database.news.add(news);
    }

    @Override
    public void addResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }

    @Override
    public List<ResearchPaper> getResearchPapers() { return researchPapers; }

    @Override
    public List<ResearchProject> getResearchProjects() { return researchProjects; }

    public void putMark(Student student, Mark mark) {
        Database.marks.add(mark);
    }

    public void putMark(String courseName, String studentId,
                        Double firstAtt, Double secondAtt, Double finalGrade) {
        Mark m = new Mark(courseName, studentId, firstAtt, secondAtt, finalGrade);
        Database.marks.add(m);
    }

    public void sendComplaint(List<Student> students, UrgencyLevel urgency) {
        for (Student student : students) {
            Complaint complaint = new Complaint(getName(), student.getName(), "", urgency);
            complaints.add(complaint);
            System.out.println("Complaint sent to dean: " + complaint);
        }
    }

    public void sendComplaint(String studentName, String reason, UrgencyLevel urgency) {
        Complaint complaint = new Complaint(getName(), studentName, reason, urgency);
        complaints.add(complaint);
        System.out.println("Complaint sent to dean: " + complaint);
    }

    public void addRating(double r) {
        this.rating = (this.rating * this.ratingCnt + r) / (this.ratingCnt + 1);
        this.ratingCnt++;
    }

    public void increaseRating(int rating) {
        this.ratingCnt++;
        this.rating += rating;
    }

    public double viewRating() {
        if (ratingCnt == 0) return 0.0;
        return rating;
    }

    public String generateReport(Course course) {
        StringBuilder sb = new StringBuilder("Report for course: ")
                .append(course.getCourseName()).append("\n");
        for (Student st : course.getStudents()) {
            sb.append("  ").append(st.getName())
              .append(" ").append(st.getSurname()).append("\n");
        }
        return sb.toString();
    }

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

    public void addCourseFile(String fileName, String courseId, String description) {
        File f = new File(fileName, courseId, description);
        Database.files.add(f);
    }

    public void deleteCourseFile(String fileName, String courseId) {
        Database.files.removeIf(file ->
            file.getFileName().equals(fileName) && file.getCourseId().equals(courseId));
    }

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

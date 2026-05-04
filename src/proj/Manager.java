package proj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Represents a manager in the university system.
 * Managers handle course administration, student registration, and news.
 */
public class Manager extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /** New canonical manager type. */
    private ManagerType type;
    /** Legacy manager type retained for backward compatibility. */
    private Managers managerType;
    /** Courses managed by this manager. */
    private List<Course> managedCourses;

    /**
     * Constructs a Manager with the new {@link ManagerType} enum.
     *
     * @param name        first name
     * @param surname     last name
     * @param birthDate   date of birth
     * @param phoneNumber contact phone
     * @param email       contact email
     * @param password    account password
     * @param type        manager type (OR / DEPARTMENT / DEAN_OFFICE)
     */
    public Manager(String name, String surname, String birthDate, String phoneNumber, String email,
                   String password, ManagerType type) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.type = type;
        this.managedCourses = new ArrayList<>();
    }

    /**
     * Constructs a Manager with the legacy {@link Managers} enum.
     *
     * @param name          first name
     * @param surname       last name
     * @param birthDate     date of birth
     * @param phoneNumber   contact phone
     * @param email         contact email
     * @param password      account password
     * @param managerType   legacy manager type
     */
    public Manager(String name, String surname, String birthDate, String phoneNumber, String email,
                   String password, Managers managerType) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.managerType = managerType;
        this.managedCourses = new ArrayList<>();
    }

    // ==================== Getters / Setters ====================

    /** @return the manager type */
    public ManagerType getType() { return type; }

    /** @param type new manager type */
    public void setType(ManagerType type) { this.type = type; }

    /** @return legacy manager type */
    public Managers getManagerType() { return managerType; }

    /** @param managerType legacy manager type to set */
    public void setManagerType(Managers managerType) { this.managerType = managerType; }

    /** @return list of courses managed by this manager */
    public List<Course> getManagedCourses() { return managedCourses; }

    // ==================== Core Manager Methods ====================

    /**
     * Approves a student's registration for a course, enrolling them immediately.
     *
     * @param student the student whose registration is approved
     * @param course  the course the student is approved for
     */
    public void approveRegistration(Student student, Course course) {
        student.increaseCredits(course.getCredits());
        student.courses.add(course);
        Database.studentRegistration.remove(student.getId());
        System.out.println("Approved: " + student.getName() + " → " + course.getCourseName());
    }

    /**
     * Adds a course to the manager's managed list and to the global database if absent.
     *
     * @param course the course to add
     */
    public void addCourse(Course course) {
        managedCourses.add(course);
        if (!Database.courses.contains(course)) {
            Database.courses.add(course);
        }
    }

    /**
     * Assigns a teacher to a course by adding them to the course's instructor list.
     *
     * @param teacher the teacher to assign
     * @param course  the target course
     */
    public void assignTeacher(Teacher teacher, Course course) {
        course.addInstructor(teacher);
    }

    /**
     * Generates a summary report listing the names and IDs of the given students.
     *
     * @param students the list of students to include in the report
     * @return a formatted multi-line report string
     */
    public String createReport(List<Student> students) {
        StringBuilder sb = new StringBuilder("=== Student Report ===\n");
        int i = 0;
        for (Student s : students) {
            sb.append(++i).append(") ")
              .append(s.getName()).append(" ").append(s.getSurname())
              .append(" [ID: ").append(s.getId()).append("]\n");
        }
        return sb.toString();
    }

    /**
     * Adds or updates a news item in the database (no duplicates by ID).
     *
     * @param news the news item to manage
     */
    public void manageNews(News news) {
        if (!Database.news.contains(news)) {
            Database.news.add(news);
        }
    }

    /**
     * Displays a list of incoming messages/requests to standard output.
     *
     * @param messages the messages to display
     */
    public void viewRequests(List<Message> messages) {
        int i = 0;
        for (Message m : messages) {
            System.out.println(++i + ") " + m);
        }
    }

    /**
     * Prints the manager's interactive menu options to standard output.
     */
    public void displayMenu() {
        System.out.println("=== Manager Menu ===");
        System.out.println("1. View students");
        System.out.println("2. View teacher info");
        System.out.println("3. Approve registration");
        System.out.println("4. Add course");
        System.out.println("5. Assign teacher to course");
        System.out.println("6. Create student report");
        System.out.println("7. Manage news");
        System.out.println("8. View requests");
        System.out.println("9. View student stats");
        System.out.println("0. Log out");
    }

    // ==================== Legacy Operations ====================

    /**
     * Creates and registers a new course by name, credits, and ID.
     *
     * @param name     the course name
     * @param credits  the credit count
     * @param courseId the unique course identifier
     */
    public void createCourse(String name, int credits, String courseId) {
        Course newCourse = new Course(name, credits, courseId);
        for (Course course : Database.courses) {
            if (!course.getCourseId().equals(courseId)) {
                Database.courses.add(newCourse);
            }
        }
    }

    /**
     * Returns a formatted listing of all students in the system.
     *
     * @return formatted student list string
     */
    public String infoStudents() {
        int i = 0;
        String ans = "";
        for (User user : Database.users) {
            if (user instanceof Student) {
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
     * Returns full information for a teacher found by first name.
     *
     * @param teacherName the teacher's first name to search for
     * @return the teacher's full info string, or empty string if not found
     */
    public String infoTeachers(String teacherName) {
        for (User user : Database.users) {
            if (user instanceof Teacher) {
                Teacher t = (Teacher) user;
                if (t.getName().equals(teacherName)) {
                    return t.getAllInfo();
                }
            }
        }
        return "";
    }

    /**
     * Returns all pending student registration requests as a string.
     *
     * @return registration map string representation
     */
    public String viewRequests() {
        return Database.studentRegistration.toString();
    }

    /**
     * Legacy approval method that accepts/rejects a registration by string ID.
     *
     * @param studentId student identifier
     * @param courseId  course identifier
     * @param approve   "ACCEPT" or "REJECT"
     * @return result message
     */
    public String approveRegistration(String studentId, String courseId, String approve) {
        Student st = new Student();
        for (User user : Database.users) {
            if (user instanceof Student) {
                Student s = (Student) user;
                if (s.getId().equals(studentId)) st = s;
            }
        }
        Course c = new Course();
        for (Course course : Database.courses) {
            if (course.getCourseId().equals(courseId)) c = course;
        }
        for (java.util.HashMap.Entry<String, Course> item : Database.studentRegistration.entrySet()) {
            if (item.getKey().equals(studentId) && item.getValue().equals(c)) {
                if (approve.equals("ACCEPT")) {
                    Database.studentRegistration.remove(studentId, c);
                    st.increaseCredits(c.getCredits());
                    st.courses.add(c);
                    return "Student's registration is accepted";
                } else if (approve.equals("REJECT")) {
                    return "Student's registration is rejected";
                }
            } else return "This order does not exist";
        }
        return "Orders does not exist";
    }

    /**
     * Assigns a teacher to a course using name-based lookup (legacy).
     *
     * @param courseId    the course identifier
     * @param teacherName the teacher's name to add
     */
    public void assignCourseToTeachers(String courseId, String teacherName) {
        for (Course course : Database.courses) {
            if (course.getCourseId().equals(courseId)) {
                course.teacher.add(teacherName);
            }
        }
    }

    /**
     * Adds a news item by fields (legacy — prefer {@link #manageNews(News)}).
     *
     * @param id    the news identifier
     * @param title the news title
     * @param text  the news body text
     */
    public void addNews(String id, String title, String text) {
        News news = new News(id, title, text);
        if (!Database.news.contains(news)) {
            Database.news.add(news);
        }
    }

    /**
     * Removes a news item by its identifier.
     *
     * @param id the identifier of the news to remove
     */
    public void removeNews(String id) {
        Database.news.removeIf(news -> news.getId().equals(id));
    }

    /**
     * Replaces a news item identified by {@code oldId} with new content.
     *
     * @param oldId old identifier
     * @param id    new identifier
     * @param title new title
     * @param text  new body text
     */
    public void updateNews(String oldId, String id, String title, String text) {
        News n = new News(id, title, text);
        for (int i = 0; i < Database.news.size(); i++) {
            if (Database.news.get(i).getId().equals(oldId)) {
                Database.news.set(i, n);
                break;
            }
        }
    }

    /**
     * Returns the maximum total grade across all marks in the database.
     *
     * @return the highest total grade
     */
    public Double getMaxScore() {
        Double mx = 0.0;
        for (Mark mark : Database.marks) {
            if (mark.getTotal() > mx) mx = mark.getTotal();
        }
        return mx;
    }

    /**
     * Returns the minimum total grade across all marks in the database.
     *
     * @return the lowest total grade
     */
    public Double getMinScore() {
        Double mn = 1000.0;
        for (Mark mark : Database.marks) {
            if (mark.getTotal() < mn) mn = mark.getTotal();
        }
        return mn;
    }

    /**
     * Returns the average total grade across all marks in the database.
     *
     * @return the average total grade
     */
    public Double getAvgScore() {
        Double avg = 0.0;
        int cnt = 0;
        for (Mark mark : Database.marks) {
            cnt++;
            avg += mark.getTotal();
        }
        return cnt == 0 ? 0.0 : avg / cnt;
    }

    /**
     * Returns the number of marks with a total grade below 50 (failed).
     *
     * @return count of failing marks
     */
    public int retakeCount() {
        int retakeCount = 0;
        for (Mark mark : Database.marks) {
            if (mark.getTotal() < 50) retakeCount++;
        }
        return retakeCount;
    }

    /**
     * Returns all students ordered by GPA descending.
     *
     * @return formatted student list sorted by GPA
     */
    public String orderStudentsByGPA() {
        String ans = "";
        int i = 0;
        Vector<Student> s = new Vector<>();
        for (User user : Database.users) {
            if (user instanceof Student) s.add((Student) user);
        }
        s.sort(new GPASorter());
        for (Student st : s) {
            i++;
            ans += i + ")  Student Name: " + st.getName()
                + "\n    Student surname: " + st.getSurname()
                + "\n    Birth Date: " + st.getBirthDate()
                + "\n    Email: " + st.getEmail()
                + "\n    ID: " + st.getId()
                + "\n    Year of Study: " + st.getYearOfStudy()
                + "\n    Faculty: " + st.getFaculty()
                + "\n    Degree: " + st.getDegree()
                + "\n    GPA: " + st.totalGpa() + "\n\n";
        }
        return ans;
    }

    /**
     * Returns all students ordered alphabetically by name.
     *
     * @return formatted student list sorted alphabetically
     */
    public String orderStudentsAlphabetically() {
        String ans = "";
        int i = 0;
        Vector<Student> s = new Vector<>();
        for (User user : Database.users) {
            if (user instanceof Student) s.add((Student) user);
        }
        s.sort(new NameComparator());
        for (Student st : s) {
            i++;
            ans += i + ")  Student Name: " + st.getName()
                + "\n    Student surname: " + st.getSurname()
                + "\n    Birth Date: " + st.getBirthDate()
                + "\n    Email: " + st.getEmail()
                + "\n    ID: " + st.getId()
                + "\n    Year of Study: " + st.getYearOfStudy()
                + "\n    Faculty: " + st.getFaculty()
                + "\n    Degree: " + st.getDegree()
                + "\n    GPA: " + st.totalGpa() + "\n\n";
        }
        return ans;
    }

    /**
     * Returns all teachers ordered alphabetically by name.
     *
     * @return formatted teacher list sorted alphabetically
     */
    public String orderTeachersAlphabetically() {
        int i = 0;
        String ans = "";
        Vector<Teacher> t = new Vector<>();
        for (User user : Database.users) {
            if (user instanceof Teacher) t.add((Teacher) user);
        }
        t.sort(new NameComparator());
        for (Teacher tt : t) {
            i++;
            ans += i + ")  Teacher Name: " + tt.getName()
                + "\n    Teacher surname: " + tt.getSurname()
                + "\n    Birth Date: " + tt.getBirthDate()
                + "\n    Email: " + tt.getEmail()
                + "\n    Status: " + tt.getTeacherStatus()
                + "\n    Experience: " + tt.getExperience()
                + "\n    Rating: " + tt.getRating() + " out of 5\n\n";
        }
        return ans;
    }

    /**
     * Returns all teachers ordered by status.
     *
     * @return formatted teacher list sorted by status
     */
    public String orderTeachersStatus() {
        int i = 0;
        String ans = "";
        Vector<Teacher> t = new Vector<>();
        for (User user : Database.users) {
            if (user instanceof Teacher) t.add((Teacher) user);
        }
        t.sort(new StatusComparator());
        for (Teacher tt : t) {
            i++;
            ans += i + ")  Teacher Name: " + tt.getName()
                + "\n    Teacher surname: " + tt.getSurname()
                + "\n    Birth Date: " + tt.getBirthDate()
                + "\n    Email: " + tt.getEmail()
                + "\n    Status: " + tt.getTeacherStatus()
                + "\n    Experience: " + tt.getExperience()
                + "\n    Rating: " + tt.getRating() + " out of 5\n\n";
        }
        return ans;
    }

    // ==================== Object overrides ====================

    @Override
    public String toString() {
        return "Manager{" +
            "name='" + getName() + '\'' +
            ", surname='" + getSurname() + '\'' +
            ", type=" + type +
            ", managerType=" + managerType +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(getEmail(), manager.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}

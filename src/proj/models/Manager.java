package proj.models;

import proj.academic.Course;
import proj.academic.Mark;
import proj.communication.Message;
import proj.communication.News;
import proj.enums.ManagerType;
import proj.enums.Managers;
import proj.utils.Database;
import proj.utils.GPASorter;
import proj.utils.NameComparator;
import proj.utils.StatusComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Manager extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private ManagerType type;
    private Managers managerType;
    private List<Course> managedCourses;

    public Manager(String name, String surname, String birthDate, String phoneNumber, String email,
                   String password, ManagerType type) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.type = type;
        this.managedCourses = new ArrayList<>();
    }

    public Manager(String name, String surname, String birthDate, String phoneNumber, String email,
                   String password, Managers managerType) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.managerType = managerType;
        this.managedCourses = new ArrayList<>();
    }

    public ManagerType getType() { return type; }

    public void setType(ManagerType type) { this.type = type; }

    public Managers getManagerType() { return managerType; }

    public void setManagerType(Managers managerType) { this.managerType = managerType; }

    public List<Course> getManagedCourses() { return managedCourses; }

    public void approveRegistration(Student student, Course course) {
        student.increaseCredits(course.getCredits());
        student.courses.add(course);
        Database.getStudentRegistration().remove(student.getId());
        System.out.println("Approved: " + student.getName() + " → " + course.getCourseName());
    }

    public void addCourse(Course course) {
        managedCourses.add(course);
        if (!Database.getCourses().contains(course)) {
            Database.getCourses().add(course);
        }
    }

    public void assignTeacher(Teacher teacher, Course course) {
        course.addInstructor(teacher);
    }

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

    public void manageNews(News news) {
        if (!Database.getNews().contains(news)) {
            Database.getNews().add(news);
        }
    }

    public void viewRequests(List<Message> messages) {
        int i = 0;
        for (Message m : messages) {
            System.out.println(++i + ") " + m);
        }
    }

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

    public void createCourse(String name, int credits, String courseId) {
        Course newCourse = new Course(name, credits, courseId);
        for (Course course : Database.getCourses()) {
            if (!course.getCourseId().equals(courseId)) {
                Database.getCourses().add(newCourse);
            }
        }
    }

    public String infoStudents() {
        int i = 0;
        String ans = "";
        for (User user : Database.getUsers()) {
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

    public String infoTeachers(String teacherName) {
        for (User user : Database.getUsers()) {
            if (user instanceof Teacher) {
                Teacher t = (Teacher) user;
                if (t.getName().equals(teacherName)) {
                    return t.getAllInfo();
                }
            }
        }
        return "";
    }

    public String viewRequests() {
        return Database.getStudentRegistration().toString();
    }

    public String approveRegistration(String studentId, String courseId, String approve) {
        Student st = new Student();
        for (User user : Database.getUsers()) {
            if (user instanceof Student) {
                Student s = (Student) user;
                if (s.getId().equals(studentId)) st = s;
            }
        }
        Course c = new Course();
        for (Course course : Database.getCourses()) {
            if (course.getCourseId().equals(courseId)) c = course;
        }
        for (java.util.Map.Entry<String, Course> item : Database.getStudentRegistration().entrySet()) {
            if (item.getKey().equals(studentId) && item.getValue().equals(c)) {
                if (approve.equals("ACCEPT")) {
                    Database.getStudentRegistration().remove(studentId, c);
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

    public void assignCourseToTeachers(String courseId, String teacherName) {
        for (Course course : Database.getCourses()) {
            if (course.getCourseId().equals(courseId)) {
                course.getTeacher().add(teacherName);
            }
        }
    }

    public void addNews(String id, String title, String text) {
        News news = new News(id, title, text);
        if (!Database.getNews().contains(news)) {
            Database.getNews().add(news);
        }
    }

    public void removeNews(String id) {
        Database.getNews().removeIf(news -> news.getId().equals(id));
    }

    public void updateNews(String oldId, String id, String title, String text) {
        News n = new News(id, title, text);
        for (int i = 0; i < Database.getNews().size(); i++) {
            if (Database.getNews().get(i).getId().equals(oldId)) {
                Database.getNews().set(i, n);
                break;
            }
        }
    }

    public Double getMaxScore() {
        Double mx = 0.0;
        for (Mark mark : Database.getMarks()) {
            if (mark.getTotal() > mx) mx = mark.getTotal();
        }
        return mx;
    }

    public Double getMinScore() {
        Double mn = 1000.0;
        for (Mark mark : Database.getMarks()) {
            if (mark.getTotal() < mn) mn = mark.getTotal();
        }
        return mn;
    }

    public Double getAvgScore() {
        Double avg = 0.0;
        int cnt = 0;
        for (Mark mark : Database.getMarks()) {
            cnt++;
            avg += mark.getTotal();
        }
        return cnt == 0 ? 0.0 : avg / cnt;
    }

    public int retakeCount() {
        int retakeCount = 0;
        for (Mark mark : Database.getMarks()) {
            if (mark.getTotal() < 50) retakeCount++;
        }
        return retakeCount;
    }

    public String orderStudentsByGPA() {
        String ans = "";
        int i = 0;
        List<Student> s = new Vector<>();
        for (User user : Database.getUsers()) {
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

    public String orderStudentsAlphabetically() {
        String ans = "";
        int i = 0;
        List<Student> s = new Vector<>();
        for (User user : Database.getUsers()) {
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

    public String orderTeachersAlphabetically() {
        int i = 0;
        String ans = "";
        List<Teacher> t = new Vector<>();
        for (User user : Database.getUsers()) {
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

    public String orderTeachersStatus() {
        int i = 0;
        String ans = "";
        List<Teacher> t = new Vector<>();
        for (User user : Database.getUsers()) {
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

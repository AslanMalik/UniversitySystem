package proj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseName;
    private Integer credits;
    private String description;
    private String courseId;
    private Boolean isAvailable;
    protected List<String> teacher;
    private List<Student> students;
    protected List<File> courseFiles;
    private List<String> prerequisite;
    private Boolean isElective;

    private CourseType type;
    private List<Teacher> instructors;
    private List<Lesson> lessons;
    private int maxStudents = 30;

    public Course() {}

    public Course(String courseName, Integer credits, String description, String courseId,
                  Boolean isAvailable, List<String> teacher, List<Student> students,
                  List<File> courseFiles, Map<Student, Mark> marks,
                  List<String> prerequisite, Boolean isElective) {
        this.courseName = courseName;
        this.credits = credits;
        this.description = description;
        this.courseId = courseId;
        this.isAvailable = isAvailable;
        this.teacher = teacher;
        this.students = students;
        this.courseFiles = courseFiles;
        this.prerequisite = prerequisite;
        this.isElective = isElective;
        this.instructors = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public Course(String courseName, String description, int credits, String courseId) {
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.courseId = courseId;
        this.isAvailable = true;
        this.teacher = new Vector<>();
        this.students = new Vector<>();
        this.courseFiles = new Vector<>();
        this.prerequisite = new Vector<>();
        this.instructors = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public Course(String courseName, int credits, String courseId) {
        this.courseName = courseName;
        this.credits = credits;
        this.courseId = courseId;
        this.isAvailable = true;
        this.teacher = new Vector<>();
        this.students = new Vector<>();
        this.courseFiles = new Vector<>();
        this.prerequisite = new Vector<>();
        this.instructors = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return this.credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public List<String> getTeacher() {
        return this.teacher;
    }

    public void setTeacher(List<String> teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<String> getPrerequisite() {
        return this.prerequisite;
    }

    public void setPrerequisite(List<String> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public List<File> getCourseFiles() {
        return this.courseFiles;
    }

    public void setCourseFiles(List<File> courseFiles) {
        this.courseFiles = courseFiles;
    }

    public Boolean getIsElective() { return this.isElective; }

    public void setIsElective(Boolean isElective) { this.isElective = isElective; }

    public CourseType getType() { return type; }

    public void setType(CourseType type) { this.type = type; }

    public List<Teacher> getInstructors() { return instructors; }

    public List<Lesson> getLessons() { return lessons; }

    public int getMaxStudents() { return maxStudents; }

    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    public boolean addStudent(String studentId) {
        for (User user : Database.users) {
            if(user instanceof Student) {
                Student st = (Student) user;
                if(st.getId().equals(studentId)) {
                    this.students.add(st);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeStudent(String studentId) {
        for (User user : Database.users) {
            if(user instanceof Student) {
                Student st = (Student) user;
                if(st.getId().equals(studentId)) {
                    this.students.remove(st);
                    return true;
                }
            }
        }
        return false;
    }

    public void addCourseFile(String fileName, String courseId, String description) {
        File file = new File(fileName, courseId, description);
        if(!courseFiles.contains(file)) {
            courseFiles.add(file);
        }
    }

    public void removeCourseFile(String fileName, String courseId) {
        for (File file2 : courseFiles) {
            if(file2.getFileName().equals(fileName) && file2.getCourseId().equals(courseId)) {
                courseFiles.remove(file2);
            }
        }
    }

    public List<String> showPrereq(String courseId) {
        for (Course course2 : Database.courses) {
            if(course2.getCourseId().equals(courseId)) {
                if(course2.prerequisite != null) {
                    return course2.prerequisite;
                }
            }
        }
        return null;
    }

    public void addPrereq(String prereq) {
        this.prerequisite.add(prereq);
    }

    public void removePrereq(String prereq) {
        this.prerequisite.remove(prereq);
    }

    public void addInstructor(Teacher teacher) {
        if (!instructors.contains(teacher)) {
            instructors.add(teacher);
        }
    }

    public void enrollStudent(Student student) {
        if (students.size() >= maxStudents) {
            throw new IllegalStateException(
                "Course '" + courseName + "' is full (max " + maxStudents + " students).");
        }
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public String getSchedule() {
        if (lessons == null || lessons.isEmpty()) {
            return "No lessons scheduled for " + courseName + ".";
        }
        StringBuilder sb = new StringBuilder("Schedule for ")
                .append(courseName).append(":\n");
        int i = 0;
        for (Lesson lesson : lessons) {
            sb.append(++i).append(") ").append(lesson.getInfo()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Course{" +
            "courseName='" + courseName + '\'' +
            ", courseId='" + courseId + '\'' +
            ", credits=" + credits +
            ", type=" + type +
            ", maxStudents=" + maxStudents +
            ", enrolled=" + (students != null ? students.size() : 0) +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}

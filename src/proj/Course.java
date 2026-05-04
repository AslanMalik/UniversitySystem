package proj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Represents a university course with enrolled students, instructors, and scheduled lessons.
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseName;
    private Integer credits;
    private String description;
    private String courseId;
    private Boolean isAvailable;
    /** Legacy teacher-name list retained for backward compatibility. */
    protected Vector<String> teacher;
    private Vector<Student> students;
    protected Vector<File> courseFiles;
    private Vector<String> prerequisite;
    private Boolean isElective;

    /** Course category: MAJOR, MINOR, or FREE_ELECTIVE. */
    private CourseType type;
    /** Type-safe list of assigned instructor objects (supports more than one teacher). */
    private List<Teacher> instructors;
    /** Scheduled lessons for this course. */
    private List<Lesson> lessons;
    /** Maximum number of students allowed; default is 30. */
    private int maxStudents = 30;

    public Course() {}

    /**
     * Full constructor for all fields (legacy).
     */
    public Course(String courseName, Integer credits, String description, String courseId,
                  Boolean isAvailable, Vector<String> teacher, Vector<Student> students,
                  Vector<File> courseFiles, HashMap<Student, Mark> marks,
                  Vector<String> prerequisite, Boolean isElective) {
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

    /**
     * Constructs a course with name, description, credits, and ID.
     *
     * @param courseName  human-readable name
     * @param description short description
     * @param credits     credit count
     * @param courseId    unique identifier
     */
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

    /**
     * Minimal constructor used when only name, credits, and ID are known.
     *
     * @param courseName human-readable name
     * @param credits    credit count
     * @param courseId   unique identifier
     */
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

    public Vector<String> getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Vector<String> teacher) {
        this.teacher = teacher;
    }

    public Vector<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Vector<Student> students) {
        this.students = students;
    }

    public Vector<String> getPrerequisite() {
        return this.prerequisite;
    }

    public void setPrerequisite(Vector<String> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public List<File> getCourseFiles() {
        return this.courseFiles;
    }

    public void setCourseFiles(Vector<File> courseFiles) {
        this.courseFiles = courseFiles;
    }

    /** @return the elective flag */
    public Boolean getIsElective() { return this.isElective; }

    /** @param isElective new elective flag value */
    public void setIsElective(Boolean isElective) { this.isElective = isElective; }

    /** @return the course type (MAJOR / MINOR / FREE_ELECTIVE) */
    public CourseType getType() { return type; }

    /** @param type the course type to set */
    public void setType(CourseType type) { this.type = type; }

    /** @return the list of assigned Teacher objects */
    public List<Teacher> getInstructors() { return instructors; }

    /** @return the list of scheduled lessons */
    public List<Lesson> getLessons() { return lessons; }

    /** @return the maximum number of students allowed */
    public int getMaxStudents() { return maxStudents; }

    /** @param maxStudents new student capacity */
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    //                          Operations
    
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
    /**
    * @generated
    */
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
    /**
    * @generated
    */
    public void addCourseFile(String fileName, String courseId, String description) {
        File file = new File(fileName, courseId, description);
        if(!courseFiles.contains(file)) {
            courseFiles.add(file);
        }
    }
    /**
    * @generated
    */
    public void removeCourseFile(String fileName, String courseId) {
        for (File file2 : courseFiles) {
            if(file2.getFileName().equals(fileName) && file2.getCourseId().equals(courseId)) {
                courseFiles.remove(file2);
            }
        }
    }
    /**
    * @generated
    */
    public Vector<String> showPrereq(String courseId) {
        for (Course course2 : Database.courses) {
            if(course2.getCourseId().equals(courseId)) {
                if(course2.prerequisite != null) {
                    return course2.prerequisite;
                } 
            }   
        }
        return null;
    }
    /**
    * @generated
    */
    public void addPrereq(String prereq) {
        this.prerequisite.add(prereq);
    }
    /**
    * @generated
    */
    /** @param prereq prerequisite course name to remove */
    public void removePrereq(String prereq) {
        this.prerequisite.remove(prereq);
    }

    /**
     * Adds a Teacher object to the typed instructor list.
     * The course supports more than one instructor simultaneously.
     *
     * @param teacher the teacher to add as an instructor
     */
    public void addInstructor(Teacher teacher) {
        if (!instructors.contains(teacher)) {
            instructors.add(teacher);
        }
    }

    /**
     * Enrolls a student in this course.
     * Throws {@link IllegalStateException} if the course is already at capacity.
     *
     * @param student the student to enroll
     * @throws IllegalStateException if {@code enrolledStudents.size() >= maxStudents}
     */
    public void enrollStudent(Student student) {
        if (students.size() >= maxStudents) {
            throw new IllegalStateException(
                "Course '" + courseName + "' is full (max " + maxStudents + " students).");
        }
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    /**
     * Returns a formatted schedule listing all lessons for this course.
     *
     * @return multi-line schedule string, or a message if no lessons are scheduled
     */
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

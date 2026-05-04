package proj;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;



public class Course implements Serializable {
    
    private String courseName;
    private Integer credits;
    private String description;
    private String courseId;
    private Boolean isAvailable;
    protected Vector<String> teacher;
    private Vector<Student> students;
    protected Vector<File> courseFiles;
    private Vector<String> prerequisite;

    
    private Boolean isElective;

    public Course() {

    }

    public Course(String courseName, Integer credits, String description, String courseId, Boolean isAvailable, Vector<String> teacher, Vector<Student> students, Vector<File> courseFiles, HashMap<Student,Mark> marks, Vector<String> prerequisite, Boolean isElective) {
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
    }
    public Course(String courseName, String description, int credits, String courseId) {
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.courseId = courseId;
        this.isAvailable = true;
        Vector<String> teacher = new Vector<>();
        Vector<Student> students = new Vector<>();
        List<File> courseFiles = new Vector<>();
        Vector<String> prerequisite = new Vector<>();
        
    }

    public Course(String courseName, int credits, String courseId) {
        this.courseName = courseName;
        this.credits = credits;
        this.courseId = courseId;
        this.isAvailable = true;
        Vector<String> teacher = new Vector<>();
        Vector<Student> students = new Vector<>();
        List<File> courseFiles = new Vector<>();
        Vector<String> prerequisite = new Vector<>();
        
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

    public Boolean getIsElective() {
        return this.isElective;
    }

    public void setIsElective(Boolean isElective) {
        this.isElective = isElective;
    }
    

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
    public void removePrereq(String prereq) {
        this.prerequisite.remove(prereq);
    }
    
}

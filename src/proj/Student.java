package proj;

import java.util.List;
import java.util.Vector;

public class Student extends User {

    public Student() {

    }

    public Student(String name, String surname, String birthDate, String phoneNumber, String email, String password, String id, Integer yearOfStudy, Faculty faculty, Degree degree) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.id = id;
        this.yearOfStudy = yearOfStudy;
        this.totalCredits = 0;
        this.GPA = 0.0;
        this.courses = new Vector<Course>();
        this.faculty = faculty;
        this.degree = degree;
        this.creditLimit = 21;
    }

    public Student(String name, String surname, String birthDate, String phoneNumber, String email,
            String password, String id, Integer yearOfStudy, Integer totalCredits, Double GPA, List<Course> courses, Faculty faculty, Degree degree, Integer creditLimit) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.id = id;
        this.yearOfStudy = yearOfStudy;
        this.totalCredits = totalCredits;
        this.GPA = GPA;
        this.courses = courses;
        this.faculty = faculty;
        this.degree = degree;
        this.creditLimit = creditLimit;
    }

    private String id;
    private Integer yearOfStudy;
    private Integer totalCredits;
    private Double GPA;
    protected List<Course> courses;
    private Faculty faculty;
    private Degree degree;
    private Integer creditLimit;
    private Integer chosenCredits = 0;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYearOfStudy() {
        return this.yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Integer getTotalCredits() {
        return this.totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Double getGPA() {
        return this.GPA;
    }

    public void setGPA(Double GPA) {
        this.GPA = GPA;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Degree getDegree() {
        return this.degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Integer getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void increaseCredits(int creditCount) {
        this.chosenCredits += creditCount;
    }

    public void registerToCourse(String courseID) throws CreditOverFlow {
        Course newCourse = null;
        for (Course course : Database.courses) {
            if (course.getCourseId().equals(courseID)) {
                newCourse = course;
                if (this.chosenCredits <= this.creditLimit && newCourse.getIsAvailable()) {
                    Database.studentRegistration.put(this.id, newCourse);
                } else {
                    throw new CreditOverFlow("Number of credits exceeded or Course is not available!!!");
                }
            }
        }
    }

    public void orderBook(String bookId) {
        Book b = new Book();
        for(Book book : Database.books) {
            if(book.getId().equals(bookId)) {
                b = book;
            }
        }
        Database.orders.put(this.getId(), b);
    }

    public String viewTeacherInfo(String name) {
        for (User user : Database.users) {
            if(user instanceof Teacher) {
                Teacher t = (Teacher) user;
                if(t.getName().equals(name)) {
                    return t.getAllInfo();
                }
            }
        }
        return "";
    }

    public void rateTeacher(String teacherName, int rating) {
        for (User user : Database.users) {
            if(user instanceof Teacher) {
                Teacher t = (Teacher) user;
                if(t.getName().equals(teacherName)) {
                    t.increaseRating(rating);
                }
            }
        }
    }

    public void dropCourse(String courseId) {
        for (Course course :this.courses) {
            if (course.getCourseId().equals(courseId)) {
                this.courses.remove(course);
            }
        }
    }

    public String convertToGPA(Double totalGrade) {
        if (totalGrade >= 95 && totalGrade <= 100)
            return "4.0";
            if (totalGrade >= 90)
                return "3.67";
            if (totalGrade >= 85)
                return "3.33";
            if (totalGrade >= 80)
                return "3.0";
            if (totalGrade >= 75)
                return "2.67";
            if (totalGrade >= 70)
                return "2.33";
            if (totalGrade >= 65)
                return "2.0";
            if (totalGrade >= 60)
                return "1.67";
            if (totalGrade >= 55)
                return "1.33";
            if (totalGrade >= 50)
                return "1.00";
            else
                return "0.0";
    }

    public String totalGpa() {
        int i = 0;
        double points = 0.0;
        for (Mark mark : Database.marks) {
            if(mark.getStudentId().equals(this.id)) {
                points += mark.getTotalGrade();
                i ++;
            }
        }
        points /= i;
        return(convertToGPA(points));
    }

    public String viewTranscript() {
        int i = 0;
        String s = "";
        double points = 0.0;
        for (Mark mark : Database.marks) {
            if(mark.getStudentId().equals(this.id)) {
                points += mark.getTotalGrade();
                i ++;
                s += i + ") Course Name: " + mark.getCourseName()
                + "\n    First Att.: " + mark.getFirstAtt()
                + "\n    Second Att.: " + mark.getSecondAtt()
                + "\n    Final Grade: " + mark.getFinalGrade()
                + "\n    Total Grade: " + mark.getTotalGrade() + " " + mark.convertToLetter() + " " + mark.convertToGPA() + "\n";
            }
        }
        points /= i;
        s += "Total GPA: " + convertToGPA(points);
        return s;
    }

    public String getTranscript() {
        return "Transcript is downloaded";
    }

    public String viewCourses() {
        String s = "";
        int i = 0;
        for (Course course : Database.courses) {
            i ++;
            s += i + ") Course Name: " + course.getCourseName()
            + "\n    Description: " + course.getDescription()
            + "\n    Course ID: " + course.getCourseId() + "\n\n";
        }
        return s;
    }

    public String viewMarks() {
        String s = "";
        int i = 0;
        for (Mark mark : Database.marks) {
            if(mark.getStudentId().equals(this.id)) {
                i ++;
                s += i + ") Course Name: " + mark.getCourseName()
                + "\n    First Att.: " + mark.getFirstAtt()
                + "\n    Second Att.: " + mark.getSecondAtt()
                + "\n    Final Grade: " + mark.getFinalGrade()
                + "\n    Total Grade: " + mark.getTotalGrade() + " " + mark.convertToLetter() + " " + mark.convertToGPA() + "\n\n";
            }
        }
        return s;
    }

    public String viewAvailableCourses() {
        int i = 0;
        String s = "";
        for (Course course : Database.courses) {
            if(course.getIsAvailable()) {
                i ++;
                s += i + ") Course Name: " + course.getCourseName()
                + "\n    Course ID: " + course.getCourseId()
                + "\n    Course credits: " + course.getCredits()
                + "\n    Course description: " + course.getDescription() + "\n\n";
            }
        }
        return s;
    }

    public String viewCourseFiles(String courseId) {
        int i = 0;
        String s = "";
        for(File file : Database.files) {
            if(file.getCourseId().equals(courseId)) {
                i ++;
                s += i + ") File Name: " + file.getFileName()
                + "\n    CourseId: " + file.getCourseId()
                + "\n    File description: " + file.getDescription()
                + "\n    Post date: " + file.getPostDate() + "\n\n";
            }
        }
        return s;
    }

    public void viewSchedule() {
    }

    public void viewAttendance() {
    }

    public String getAllInfo() {
        return "{" +
            " name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            " id='" + getId() + "'" +
            ", yearOfStudy='" + getYearOfStudy() + "'" +
            ", faculty='" + getFaculty() + "'" +
            ", degree='" + getDegree() + "'" +
            "}";
    }
}

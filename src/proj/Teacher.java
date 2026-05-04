package proj;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee implements Researcher {
    
    private Status teacherStatus;
    private Vector<Course> courses;
    private String experience;
    private double rating;
    private int ratingCnt;
    private List<Complaint> complaints;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;

    public Teacher() {

    }

    public Teacher(String name, String surname, String birthDate, String phoneNumber, String email,
                String password, Status teacherStatus, String experience) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.teacherStatus = teacherStatus;
        this.courses = new Vector<Course>();
        this.experience = experience;
        this.rating = 0.0;
        this.ratingCnt = 0;
        this.complaints = new ArrayList<>();
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCnt() {
        return this.ratingCnt;
    }

    public void setRatingCnt(int ratingCnt) {
        this.ratingCnt = ratingCnt;
    }

    public Status getTeacherStatus() {
        return this.teacherStatus;
    }

    public void setTeacherStatus(Status teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public Vector<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }   
    
    public List<Complaint> getComplaints() {
        return this.complaints;
    }

    public void sendComplaint(String studentName, String reason, UrgencyLevel urgency) {
        Complaint complaint = new Complaint(getName(), studentName, reason, urgency);
        complaints.add(complaint);
        System.out.println("Complaint sent to dean: " + complaint);
    }

    // ========== Researcher Interface Methods ==========
    
    @Override
    public int calculateHIndex() {
        researchPapers.sort((a, b) -> Integer.compare(b.getCitations(), a.getCitations()));
        int h = 0;
        for (int i = 0; i < researchPapers.size(); i++) {
            if (researchPapers.get(i).getCitations() >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }
    
    @Override
    public void printPapers(java.util.Comparator<ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (ResearchPaper paper : researchPapers) {
            System.out.println(paper);
        }
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
    public List<ResearchPaper> getResearchPapers() { 
        return researchPapers; 
    }
    
    @Override
    public List<ResearchProject> getResearchProjects() { 
        return researchProjects; 
    }

    // ========== End of Researcher Methods ==========

    public void addCourseFile(String fileName, String courseId, String description) {
        File f = new File(fileName, courseId, description);
        Database.files.add(f);
    }

    public void deleteCourseFile(String fileName, String courseId) {
        for (File file : Database.files) {
            if(file.getFileName().equals(fileName) && file.getCourseId().equals(courseId)) {
                Database.files.remove(file);
            }
        }
    }

    public void increaseRating(int rating) {
        this.ratingCnt ++;
        this.rating += rating;
    }

    public Double viewRating() {
        if (ratingCnt == 0) return 0.0;
        return rating / ratingCnt * 1.0;
    }

    public String viewStudents() {
        int i = 0;
        String ans = "";
        for (User user : Database.users) {
            if(user instanceof Student && !(user instanceof GraduateStudent)) {
                Student st = (Student) user;
                i ++;
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
                    ans +=
                  "    Student Name: " + st.getName()
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

    public void putMark(String courseName, String studentId, Double firstAtt, Double secondAtt, Double finalGrade) {
        Mark m = new Mark(courseName, studentId, firstAtt, secondAtt, finalGrade);
        Database.marks.add(m);
    }

    public String viewMarks(String courseName) {
        int i = 0;
        String s = "";
        for (Mark mark : Database.marks) {
            if(mark.getCourseName().equals(courseName)) {
                i ++;
                s += i + ") Student Id: " + mark.getStudentId()
                + "\n    First Att.: " + mark.getFirstAtt() 
                + "\n    Second Att.: " + mark.getSecondAtt()
                + "\n    Final Grade: " + mark.getFinalGrade()
                + "\n    Total Grade: " + mark.getTotalGrade() + " " + mark.convertToLetter() + " " + mark.convertToGPA() + "\n\n";
            }
        }
        return s;
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

    public String getAllInfo() {
        return "{" +
            " name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", email='" + getEmail() + "'" +
            " teacherStatus='" + getTeacherStatus() + "'" +
            ", experience='" + getExperience() + "'" +
            ", h-index='" + calculateHIndex() + "'" +
            "}";
    }
}
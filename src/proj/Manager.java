package proj;

import java.util.HashMap;
import java.util.Vector;

/**
* @generated
*/
public class Manager extends Employee {
    
    private Managers managerType;

    public Manager(String name, String surname, String birthDate, String phoneNumber, String email,
            String password, Managers managerType) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.managerType = managerType;
    }

    
    public Managers getManagerType() {
        return this.managerType;
    }

    public void setManagerType(Managers managerType) {
        this.managerType = managerType;
    }

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void createCourse(String name, int credits, String courseId) {
        
        Course newCourse = new Course(name, credits, courseId);
    	for (Course course: Database.courses) {
        	if (!course.getCourseId().equals(courseId)) {
        		Database.courses.add(newCourse);
        	}
    	}
    }
    /**
    * @generated
    */
    public String infoStudents() {
        int i = 0;
        String ans = "";
        for (User user : Database.users) {
            if(user instanceof Student) {
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
    /**
    * @generated
    */
    public String infoTeachers(String teacherName) {
        for (User user : Database.users) {
            if(user instanceof Teacher) {
                Teacher t = (Teacher) user;
                if(t.getName().equals(teacherName)) {
                    return t.getAllInfo();
                }
            }
        }
        return "";
    }

    public String viewRequests() {
        return Database.studentRegistration.toString();
    }

    public String approveRegistration(String studentId, String courseId, String approve) {
        Student st = new Student();
        for(User user : Database.users) {
            if(user instanceof Student) {
                Student s = (Student) user;
                if(s.getId().equals(studentId)) {
                    st = s;
                }   
            }
        }
        Course c = new Course();
        for (Course course : Database.courses) {
            if(course.getCourseId().equals(courseId)) {
                c = course;
            }
        }

        for(HashMap.Entry<String, Course> item : Database.studentRegistration.entrySet()) {
            if(item.getKey().equals(studentId) && item.getValue().equals(c)) {
                if(approve.equals("ACCEPT")) {
                    Database.studentRegistration.remove(studentId, c);
                    st.increaseCredits(c.getCredits());
                    st.courses.add(c);
                    return "Student's registration is accepted";
                } else if(approve.equals("REJECT")) {
                    return "Student's registration is rejected";
                }
            } else return "This order does not exist";
        }
        return "Orders does not exist";
    }
    /**
    * @generated
    */
    public void assignCourseToTeachers(String courseId, String teacherName) {
        for (Course course : Database.courses) {
            if (course.getCourseId().equals(courseId)) {
                course.teacher.add(teacherName);
            }
        }
    }
    /**
    * @generated
    */
    public void addNews(String id, String title, String text) {
        News news = new News(id, title, text);
        if(!Database.news.contains(news)) {
            Database.news.add(news);
        }
        // newsExistingException
    }
    /**
    * @generated
    */
    public void removeNews(String id) {
        for (News news : Database.news) {
            if(news.getId().equals(id)) {
                Database.news.remove(news);
            }   
        }
        // newsDoesNotExistingException
    }
    /**
    * @generated
    */
    public void updateNews(String oldId, String id, String title, String text) {
        News n = new News(id, title, text);
        for (News news : Database.news) {
            if(news.getId().equals(oldId)) {
                Database.news.remove(news);
                Database.news.add(n);
            }   
        }
        // newsDoesNotExistingException
    }
    /**
    * @generated
    */
    public Double getMaxScore() {
        Double mx = 0.0;
        for (Mark mark : Database.marks) {
            if(mark.getTotalGrade() > mx) {
                mx = mark.getTotalGrade();
            }
        }
        return mx;
    }
    /**
    * @generated
    */
    public Double getMinScore() {
        Double mn = 1000.0;
        for (Mark mark : Database.marks) {
            if(mark.getTotalGrade() < mn) {
                mn = mark.getTotalGrade();
            }
        }
        return mn;
    }
    /**
    * @generated
    */
    public Double getAvgScore() {
        Double avg = 0.0;
        int cnt = 0;
        for (Mark mark : Database.marks) {
            cnt ++;
            avg += mark.getTotalGrade();            
        }
        return avg / cnt;
    }
    /**
    * @generated
    */
    public int retakeCount() {
        int retakeCount = 0;
        for(Mark mark : Database.marks) {
            if(mark.getTotalGrade() < 50) {
                retakeCount ++;
            }
        }
        return retakeCount;
    }
    /**
    * @generated
    */
    public String orderStudentsByGPA() {
        String ans = "";
        int i = 0;
        Vector<Student> s = new Vector<Student>();
        for (User user: Database.users) {
            if(user instanceof Student) {
                s.add((Student) user);
            }
        }
        s.sort(new GPASorter());
        for (User user : s) {
            Student st = (Student) user;
            i ++;
            ans +=
                i + ")  Student Name: " + st.getName()
            + "\n    Student surname: " + st.getSurname() 
            + "\n    Birth Date: " + st.getBirthDate()
            + "\n    Email: " + st.getEmail()
            + "\n    ID: " + st.getId()
            + "\n    Year of Study: " + st.getYearOfStudy()
            + "\n    Faculty: " + st.getFaculty()
            + "\n    Degree: " + st.getDegree()
            + "\n    GPA: " + st.totalGpa() 
            + "\n\n";
        }
        return ans;
    }
    /**
    * @generated
    */
    public String orderStudentsAlphabetically() {
        String ans = "";
        int i = 0;
        Vector<Student> s = new Vector<Student>();
        for (User user: Database.users) {
            if(user instanceof Student) {
                s.add((Student) user);
            }
        }
        s.sort(new NameComparator());
        for (User user : s) {
            Student st = (Student) user;
            i ++;
            ans +=
                i + ")  Student Name: " + st.getName()
            + "\n    Student surname: " + st.getSurname() 
            + "\n    Birth Date: " + st.getBirthDate()
            + "\n    Email: " + st.getEmail()
            + "\n    ID: " + st.getId()
            + "\n    Year of Study: " + st.getYearOfStudy()
            + "\n    Faculty: " + st.getFaculty()
            + "\n    Degree: " + st.getDegree()
            + "\n    GPA: " + st.totalGpa() 
            + "\n\n";
        }
        return ans;

    }
    /**
    * @generated
    */
    public String orderTeachersAlphabetically() {
        int i = 0;
        String ans = "";
        Vector<Teacher> t = new Vector<Teacher>();
        for (User user: Database.users) {
            if(user instanceof Teacher) {
                t.add((Teacher) user);
            }
        }
        t.sort(new NameComparator());
        for (User user : t) {
            Teacher tt = (Teacher) user;
            i ++;
            ans +=
                i + ")  Student Name: " + tt.getName()
            + "\n    Student surname: " + tt.getSurname() 
            + "\n    Birth Date: " + tt.getBirthDate()
            + "\n    Email: " + tt.getEmail()
            + "\n    Status: " + tt.getTeacherStatus()
            + "\n    Experience: " + tt.getExperience()
            + "\n    Rating: " + tt.getRating() + " out of 5"
            + "\n\n";
        }
        return ans;
    }
    /**
    * @generated
    */
    public String orderTeachersStatus() {
        int i = 0;
        String ans = "";
        Vector<Teacher> t = new Vector<Teacher>();
        for (User user: Database.users) {
            if(user instanceof Teacher) {
                t.add((Teacher) user);
            }
        }
        t.sort(new StatusComparator());
        for (User user : t) {
            Teacher tt = (Teacher) user;
            i ++;
            ans +=
                i + ")  Student Name: " + tt.getName()
            + "\n    Student surname: " + tt.getSurname() 
            + "\n    Birth Date: " + tt.getBirthDate()
            + "\n    Email: " + tt.getEmail()
            + "\n    Status: " + tt.getTeacherStatus()
            + "\n    Experience: " + tt.getExperience()
            + "\n    Rating: " + tt.getRating() + " out of 5"
            + "\n\n";
        }
        return ans;
    }
    
}
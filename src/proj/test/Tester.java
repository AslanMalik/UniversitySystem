package proj.test;

import proj.academic.Book;
import proj.academic.Course;
import proj.academic.Mark;
import proj.communication.Message;
import proj.communication.News;
import proj.communication.SupportRequest;
import proj.enums.*;
import proj.exceptions.CreditOverFlow;
import proj.exceptions.NotResearcherException;
import proj.models.*;
import proj.research.ResearchJournal;
import proj.research.ResearchPaper;
import proj.research.ResearchProject;
import proj.utils.Database;
import proj.utils.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;


public class Tester {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException, ClassNotFoundException, CreditOverFlow {

        Student s1 = new Student("Ernazar", "Tolegen", "23/10/2001", "8 777-777-77-77", "ernazartolegen@gmail.com", "123456", "19B030729", 1, Faculty.FIT, Degree.BACHELOR);
        Student s2 = new Student("Zhaisan", "Sarsengaliyev", "16/08/2001", "8 771-191-23-49", "zhaisansars@gmail.com", "12345", "19B030552", 2, Faculty.FIT, Degree.BACHELOR);
        Database.getUsers().add(s1);
        Database.getUsers().add(s2);

        Teacher t1 = new Teacher("Oscar", "Cardozo", "20/05/1983", "8 707-123-56-22", "oscar@gmail.com", "teacher123", Status.SENIOR_LECTOR, "11 years");
        Teacher t2 = new Teacher("Alimzhan", "Amanov", "26/03/1995", "8 701-947-65-55", "amanov.a@gmail.com", "teacher456", Status.LECTOR, "5 years");
        Database.getUsers().add(t1);
        Database.getUsers().add(t2);

        Manager m1 = new Manager("Dana", "Akhmetzhan", "21/01/1985", "8 707-111-11-11", "dana@gmail.com", "manager123", Managers.DEPARTMENTS);
        Manager m2 = new Manager("Nazym", "Aidarkhanova", "8/03/1983", "8 707-112-12-12", "nazym@gmail.com", "manager456", Managers.OR);
        Database.getUsers().add(m1);
        Database.getUsers().add(m2);

        Admin a1 = new Admin("Admin1", "Admin1", "22/02/1972", "8 707-222-22-22", "admin1@gmail.com", "admin123");
        Admin a2 = new Admin("Admin2", "Admin2", "22/02/1973", "8 707-222-22-23", "admin2@gmail.com", "admin456");
        Database.getUsers().add(a1);
        Database.getUsers().add(a2);

        Librarian l1 = new Librarian("Librarian1", "Librarianov1", "21/12/1960", "8 777-123-34-55", "lib1@gmail.com", "lib123");
        Librarian l2 = new Librarian("Librarian2", "Librarianov2", "20/12/1961", "8 777-123-34-51", "lib2@gmail.com", "lib456");
        Database.getUsers().add(l1);
        Database.getUsers().add(l2);

        Course c1 = new Course("Object-oriented programming", "Object-oriented programming (OOP) is a programming paradigm based on the concept of objects, which can contain data and code: data in the form of fields (often known as attributes or properties), and code, in the form of procedures (often known as methods).", 3, "CSCI2106");
        Course c2 = new Course("Databases", 3, "CSCI2104");
        Database.getCourses().add(c1);
        Database.getCourses().add(c2);

        Book b1 = new Book("Thomas Calculus", "B1", "Thomas");
        Book b2 = new Book("Ryabushko", "B2", "Evgeniy");
        Database.getBooks().add(b1);
        Database.getBooks().add(b2);

        Database.getStudentRegistration().put("19B030729", c1);

        Database.getTeacherRatings().put("Oscar", 5);
        Database.getTeacherRatings().put("Oscar", 4);
        Database.getTeacherRatings().put("Oscar", 4);

        News n1 = new News("1", "Registration 2021-2022 Fall", "Registration will start 10th of July");
        News n2 = new News("2", "Books", "Here should be text");
        Database.getNews().add(n1);
        Database.getNews().add(n2);

        File f1 = new File("OOP_Project", "CSCI2106", "Here should be file info");
        File f2 = new File("OOP_Diagram", "CSCI2106", "Here should be another file info");
        File f3 = new File("DB_Lab1", "CSCI2104", "This is your labwork");
        File f4 = new File("DB_Lab2", "CSCI2104", "This is your second labwork");
        Database.getFiles().add(f1);
        Database.getFiles().add(f2);
        Database.getFiles().add(f3);
        Database.getFiles().add(f4);

        Mark ma1 = new Mark("Object-oriented programming", "19B030729", 24.0, 24.6, 40.0);
        Mark ma2 = new Mark("Databases", "19B030729", 30.0, 29.0, 37.0);
        Database.getMarks().add(ma1);
        Database.getMarks().add(ma2);

        Message me1 = new Message("Alimzhan", "o_cardozo@kbtu.kz", "Greetings", "Hello Oscar. My name is Alimzhan and Welcome to KBTU.");
        Message me2 = new Message("Oscar", "a_amanov@kbtu.kz", "Hi", "Good morning Alimzhan. Thank you!");
        Database.getMessages().add(me1);
        Database.getMessages().add(me2);

        Database.getOrders().put("19B030729", b1);
        Database.getOrders().put("19B030729", b2);

        Teacher researcherTeacher = new Teacher("Research", "Professor", "15/03/1975",
            "8 777-111-22-33", "research@gmail.com", "prof123", Status.PROFESSOR, "20 years");
        Database.getUsers().add(researcherTeacher);

        GraduateStudent gradStudent = new GraduateStudent("Master", "Student", "01/01/2000",
            "8 777-000-00-00", "master@gmail.com", "master123", "MS001", 1,
            Faculty.FIT, Degree.MASTER, researcherTeacher);
        Database.getUsers().add(gradStudent);

        GraduateStudent phdStudent = new GraduateStudent("PhD", "Candidate", "15/05/1998",
            "8 777-111-22-33", "phd@gmail.com", "phd123", "PHD001", 3,
            Faculty.FIT, Degree.PHD, researcherTeacher);
        Database.getUsers().add(phdStudent);

        TechSupportSpecialist tech = new TechSupportSpecialist("Tech", "Support", "10/10/1990",
            "8 777-555-66-77", "tech@gmail.com", "tech123");
        Database.getUsers().add(tech);

        ResearchPaper paper1 = new ResearchPaper(
            "AI in Modern Education",
            new String[]{"Prof. Research", "Master Student"},
            "KBTU Journal of Computer Science",
            15,
            new Date(),
            "10.1234/kbtu.cs.2024.001"
        );
        paper1.addCitation();

        ResearchPaper paper2 = new ResearchPaper(
            "Machine Learning for Student Performance Prediction",
            new String[]{"Prof. Research", "PhD Candidate", "Master Student"},
            "International Journal of AI in Education",
            25,
            new Date(),
            "10.5678/ijai.2024.045"
        );
        paper2.addCitation();
        paper2.addCitation();

        gradStudent.addResearchPaper(paper1);
        phdStudent.addResearchPaper(paper2);
        researcherTeacher.addResearchPaper(paper1);
        researcherTeacher.addResearchPaper(paper2);

        ResearchProject project = new ResearchProject("AI for Education Improvement");
        try {
            project.addParticipant(researcherTeacher);
            project.addParticipant(gradStudent);
            project.addParticipant(phdStudent);
            project.addPaper(paper1);
            project.addPaper(paper2);
        } catch (NotResearcherException e) {
            System.err.println(e.getMessage());
        }

        SupportRequest request = new SupportRequest("REQ001", "Projector is not working in room 305", tech.getLogin());
        tech.addRequest(request);
        Database.getSupportRequests().add(request);

        t1.sendComplaint("Bad Student", "Cheating on the final exam", UrgencyLevel.HIGH);

        ResearchJournal journal = new ResearchJournal("KBTU Research Journal");
        journal.subscribe(gradStudent);
        journal.publishPaper(paper1);

        System.out.println("\n========== RESEARCHER DEMO ==========");
        System.out.println("Teacher H-Index: " + researcherTeacher.calculateHIndex());
        System.out.println("Graduate Student H-Index: " + gradStudent.calculateHIndex());
        System.out.println("PhD Student H-Index: " + phdStudent.calculateHIndex());
        System.out.println("\nResearch Papers (sorted by citations):");
        gradStudent.printPapers((p1, p2) -> Integer.compare(p2.getCitations(), p1.getCitations()));

        Database.save();

        System.out.println("\nPress q to quit.");
        System.out.println("Enter your login: ");
        String input = reader.readLine();
        if(input.equals("q")) {
            System.exit(0);
        }
        for(User u : Database.getUsers()) {
            if(u.getLogin().equals(input)) {
                System.out.println("Enter password: ");
                String input1 = reader.readLine();
                if(u.getPassword().equals(input1)) {
                    System.out.println("Successfully authorized.");
                    System.out.println("Welcome " + u.getName() + ".");
                    if(u instanceof Student && !(u instanceof GraduateStudent)) {
                        boolean flag = true;
                        while(flag) {
                            Student st = (Student) u;
                            System.out.println("/--------------------Student's mode--------------------/");
                            System.out.println("    [1]          Information about Student\n" +
                                "    [2]          View courses\n" +
                                "    [3]          View available courses\n" +
                                "    [4]          View course files\n" +
                                "    [5]          View Teacher info\n" +
                                "    [6]          View marks\n" +
                                "    [7]          View transcript\n" +
                                "    [8]          Rate teacher\n" +
                                "    [9]          Get Transcript\n" +
                                "    [10]         Order book\n" +
                                "    [11]         Register to Course\n" +
                                "    [12]         Drop Course\n" +
                                "    [13]         View news\n" +
                                "    [14]         Quit\n" +
                                "    [0]          Change password\n"
                                );

                            int chosen = Integer.parseInt(reader.readLine());

                            switch(chosen) {
                                case 1:
                                    System.out.println(st.getAllInfo());
                                    break;
                                case 2:
                                    System.out.println(st.viewCourses());
                                    break;
                                case 3:
                                    System.out.println(st.viewAvailableCourses());
                                    break;
                                case 4:
                                    System.out.println("Enter courseId: ");
                                    String courseId = reader.readLine();
                                    System.out.println(st.viewCourseFiles(courseId));
                                    break;
                                case 5:
                                    System.out.println("Enter Teacher's name: ");
                                    String name = reader.readLine();
                                    System.out.println(st.viewTeacherInfo(name));
                                    break;
                                case 6:
                                    System.out.println(st.viewMarks());
                                    break;
                                case 7:
                                    System.out.println(st.viewTranscript());
                                    break;
                                case 8:
                                    System.out.println("Enter Teacher's name: ");
                                    String teacherName = reader.readLine();
                                    System.out.println("Enter 1-5 stars: ");
                                    int rating = Integer.parseInt(reader.readLine());
                                    st.rateTeacher(teacherName, rating);
                                    Database.save();
                                    System.out.println("Thank you for your feedback!");
                                    break;
                                case 9:
                                    System.out.println(st.getTranscript());
                                    break;
                                case 10:
                                    System.out.println("Enter BookId: ");
                                    String bookId = reader.readLine();
                                    st.orderBook(bookId);
                                    Database.save();
                                    System.out.println("Your order has been completed.");
                                    break;
                                case 11:
                                    System.out.println("Enter courseId: ");
                                    String courseId1 = reader.readLine();
                                    st.registerToCourse(courseId1);
                                    Database.save();
                                    System.out.println("Your request is fulfilled");
                                    break;
                                case 12:
                                    System.out.println("Enter courseId: ");
                                    String courseId2 = reader.readLine();
                                    st.dropCourse(courseId2);
                                    Database.save();
                                    System.out.println("Your course has been removed.");
                                    break;
                                case 13:
                                    System.out.println(st.viewNewsTab());
                                    break;
                                case 14:
                                    System.exit(0);
                                case 0:
                                    System.out.println("Enter old password: ");
                                    String oldPassword = reader.readLine();
                                    System.out.println("Enter new password: ");
                                    String newPassword = reader.readLine();
                                    if(st.changePassword(oldPassword, newPassword)) {
                                        System.out.println("Your password has been changed.");
                                    } else System.out.println("Incorrect password.");
                                    Database.save();
                                    break;
                                }
                        }
                    } else if(u instanceof GraduateStudent) {
                        boolean flag = true;
                        while(flag) {
                            GraduateStudent gs = (GraduateStudent) u;
                            System.out.println("/--------------------Graduate Student mode--------------------/");
                            System.out.println("    [1]          Information about Student\n" +
                                "    [2]          View courses\n" +
                                "    [3]          View available courses\n" +
                                "    [4]          View course files\n" +
                                "    [5]          View Teacher info\n" +
                                "    [6]          View marks\n" +
                                "    [7]          View transcript\n" +
                                "    [8]          Rate teacher\n" +
                                "    [9]          Get Transcript\n" +
                                "    [10]         Order book\n" +
                                "    [11]         Register to Course\n" +
                                "    [12]         Drop Course\n" +
                                "    [13]         View news\n" +
                                "    [14]         View my research papers\n" +
                                "    [15]         Calculate my H-Index\n" +
                                "    [16]         Quit\n" +
                                "    [0]          Change password\n"
                                );

                            int chosen = Integer.parseInt(reader.readLine());

                            switch(chosen) {
                                case 1:
                                    System.out.println(gs.getAllInfo());
                                    break;
                                case 2:
                                    System.out.println(gs.viewCourses());
                                    break;
                                case 3:
                                    System.out.println(gs.viewAvailableCourses());
                                    break;
                                case 4:
                                    System.out.println("Enter courseId: ");
                                    String courseId = reader.readLine();
                                    System.out.println(gs.viewCourseFiles(courseId));
                                    break;
                                case 5:
                                    System.out.println("Enter Teacher's name: ");
                                    String name = reader.readLine();
                                    System.out.println(gs.viewTeacherInfo(name));
                                    break;
                                case 6:
                                    System.out.println(gs.viewMarks());
                                    break;
                                case 7:
                                    System.out.println(gs.viewTranscript());
                                    break;
                                case 8:
                                    System.out.println("Enter Teacher's name: ");
                                    String teacherName = reader.readLine();
                                    System.out.println("Enter 1-5 stars: ");
                                    int rating = Integer.parseInt(reader.readLine());
                                    gs.rateTeacher(teacherName, rating);
                                    Database.save();
                                    System.out.println("Thank you for your feedback!");
                                    break;
                                case 9:
                                    System.out.println(gs.getTranscript());
                                    break;
                                case 10:
                                    System.out.println("Enter BookId: ");
                                    String bookId = reader.readLine();
                                    gs.orderBook(bookId);
                                    Database.save();
                                    System.out.println("Your order has been completed.");
                                    break;
                                case 11:
                                    System.out.println("Enter courseId: ");
                                    String courseId1 = reader.readLine();
                                    gs.registerToCourse(courseId1);
                                    Database.save();
                                    System.out.println("Your request is fulfilled");
                                    break;
                                case 12:
                                    System.out.println("Enter courseId: ");
                                    String courseId2 = reader.readLine();
                                    gs.dropCourse(courseId2);
                                    Database.save();
                                    System.out.println("Your course has been removed.");
                                    break;
                                case 13:
                                    System.out.println(gs.viewNewsTab());
                                    break;
                                case 14:
                                    System.out.println("My Research Papers:");
                                    for(ResearchPaper p : gs.getResearchPapers()) {
                                        System.out.println("  - " + p.getTitle() + " (Citations: " + p.getCitations() + ")");
                                    }
                                    break;
                                case 15:
                                    System.out.println("My H-Index: " + gs.calculateHIndex());
                                    break;
                                case 16:
                                    System.exit(0);
                                case 0:
                                    System.out.println("Enter old password: ");
                                    String oldPassword = reader.readLine();
                                    System.out.println("Enter new password: ");
                                    String newPassword = reader.readLine();
                                    if(gs.changePassword(oldPassword, newPassword)) {
                                        System.out.println("Your password has been changed.");
                                    } else System.out.println("Incorrect password.");
                                    Database.save();
                                    break;
                                }
                        }
                    } else if(u instanceof Teacher) {
                        boolean flag = true;
                        while(flag) {
                            Teacher t = (Teacher) u;
                            System.out.println("/--------------------Teacher's mode--------------------/");
                            System.out.println(
                                "    [1]          Information about Teacher\n" +
                                "    [2]          View courses\n" +
                                "    [3]          Add course file\n" +
                                "    [4]          Delete course file\n" +
                                "    [5]          View list of students\n" +
                                "    [6]          View info about student\n" +
                                "    [7]          Put mark\n" +
                                "    [8]          View marks\n" +
                                "    [9]          Send message\n" +
                                "    [10]         Get messages\n" +
                                "    [11]         View rating\n" +
                                "    [12]         Send complaint\n" +
                                "    [13]         View news\n" +
                                "    [14]         View my research papers\n" +
                                "    [15]         Calculate my H-Index\n" +
                                "    [16]         Quit\n" +
                                "    [0]          Change password\n");

                            int chosen = Integer.parseInt(reader.readLine());
                            switch(chosen){
                            case 1:
                                System.out.println("Information about teacher: ");
                                System.out.println(t.getAllInfo());
                                break;
                            case 2:
                                System.out.println(t.viewCourses());
                                break;
                            case 3:
                                System.out.println("Enter file name: ");
                                String fileName = reader.readLine();
                                System.out.println("Enter CourseID: ");
                                String courseId = reader.readLine();
                                System.out.println("Enter description: ");
                                String description = reader.readLine();
                                t.addCourseFile(fileName, courseId, description);
                                Database.save();
                                System.out.println("Course file successfully added.");
                                break;
                            case 4:
                                System.out.println("Enter File name: ");
                                String fileName1 = reader.readLine();
                                System.out.println("Enter CourseID: ");
                                String courseId1 = reader.readLine();
                                t.deleteCourseFile(fileName1, courseId1);
                                Database.save();
                                System.out.println("Course file successfully deleted.");
                                break;
                            case 5:
                                System.out.println(t.viewStudents());
                                break;
                            case 6:
                                System.out.println("Enter student's name: ");
                                String name = reader.readLine();
                                System.out.println(t.viewStudentInfo(name));
                                break;
                            case 7:
                                System.out.println("Enter name of subject: ");
                                String courseName = reader.readLine();
                                System.out.println("Enter studentId: ");
                                String studentId = reader.readLine();
                                System.out.println("Enter First Attestation Points: ");
                                Double firstAtt = Double.parseDouble(reader.readLine());
                                System.out.println("Enter Second Attestation Points: ");
                                Double secondAtt = Double.parseDouble(reader.readLine());
                                System.out.println("Enter Final grade: ");
                                Double finalGrade = Double.parseDouble(reader.readLine());
                                t.putMark(courseName, studentId, firstAtt, secondAtt, finalGrade);
                                Database.save();
                                System.out.println("Success!");
                                break;
                            case 8:
                                System.out.println("Enter course name: ");
                                String courseName1 = reader.readLine();
                                System.out.println(t.viewMarks(courseName1));
                                break;
                            case 9:
                                System.out.println("Enter your name: ");
                                String messageFrom = reader.readLine();
                                System.out.println("Enter employee login you want to message to: ");
                                String messageTo = reader.readLine();
                                System.out.println("Enter message title: ");
                                String title = reader.readLine();
                                System.out.println("Enter text: ");
                                String text = reader.readLine();
                                t.sendMessage(messageFrom, messageTo, title, text);
                                Database.save();
                                System.out.println("Message sent");
                                break;
                            case 10:
                                System.out.println(t.getMessages());
                                break;
                            case 11:
                                System.out.println(t.viewRating());
                                break;
                            case 12:
                                System.out.println("Enter student name: ");
                                String studentName = reader.readLine();
                                System.out.println("Enter reason for complaint: ");
                                String reason = reader.readLine();
                                System.out.println("Enter urgency level (LOW, MEDIUM, HIGH): ");
                                String urgencyStr = reader.readLine();
                                UrgencyLevel urgency = UrgencyLevel.valueOf(urgencyStr);
                                t.sendComplaint(studentName, reason, urgency);
                                Database.save();
                                System.out.println("Complaint sent to dean!");
                                break;
                            case 13:
                                System.out.println(t.viewNewsTab());
                                break;
                            case 14:
                                System.out.println("My Research Papers:");
                                for(ResearchPaper p : t.getResearchPapers()) {
                                    System.out.println("  - " + p.getTitle() + " (Citations: " + p.getCitations() + ")");
                                }
                                break;
                            case 15:
                                System.out.println("My H-Index: " + t.calculateHIndex());
                                break;
                            case 16:
                                System.exit(0);
                            case 0:
                                System.out.println("Enter old password: ");
                                String oldPassword = reader.readLine();
                                System.out.println("Enter new password: ");
                                String newPassword = reader.readLine();
                                if(t.changePassword(oldPassword, newPassword)) {
                                    System.out.println("Your password has been changed.");
                                } else System.out.println("Incorrect password.");
                                Database.save();
                                break;
                            }
                        }
                    } else if(u instanceof Manager) {
                        boolean flag = true;
                        while(flag) {
                            Manager mgr = (Manager) u;
                            System.out.println("/--------------------Manager's mode--------------------/");
                            System.out.println(
                                "    [1]          View students\n" +
                                "    [2]          Approve registration\n" +
                                "    [3]          Assign teacher to course\n" +
                                "    [4]          Add course\n" +
                                "    [5]          Statistical report\n" +
                                "    [6]          Manage news\n" +
                                "    [7]          View requests\n" +
                                "    [0]          Log out\n");

                            int chosen = Integer.parseInt(reader.readLine());
                            switch(chosen) {
                                case 1:
                                    System.out.println(mgr.infoStudents());
                                    break;
                                case 2:
                                    System.out.println("Enter student ID: ");
                                    String studentId = reader.readLine();
                                    System.out.println("Enter course ID: ");
                                    String courseId = reader.readLine();
                                    System.out.println("Enter action (ACCEPT/REJECT): ");
                                    String approve = reader.readLine();
                                    System.out.println(mgr.approveRegistration(studentId, courseId, approve));
                                    Database.save();
                                    break;
                                case 3:
                                    System.out.println("Enter course ID: ");
                                    String cId = reader.readLine();
                                    System.out.println("Enter teacher name: ");
                                    String tName = reader.readLine();
                                    mgr.assignCourseToTeachers(cId, tName);
                                    Database.save();
                                    System.out.println("Teacher assigned.");
                                    break;
                                case 4:
                                    System.out.println("Enter course name: ");
                                    String cName = reader.readLine();
                                    System.out.println("Enter credits: ");
                                    int credits = Integer.parseInt(reader.readLine());
                                    System.out.println("Enter course ID: ");
                                    String newCourseId = reader.readLine();
                                    mgr.createCourse(cName, credits, newCourseId);
                                    Database.save();
                                    System.out.println("Course added.");
                                    break;
                                case 5:
                                    System.out.println("=== Statistical Report ===");
                                    System.out.println("Max score: " + mgr.getMaxScore());
                                    System.out.println("Min score: " + mgr.getMinScore());
                                    System.out.println("Avg score: " + mgr.getAvgScore());
                                    System.out.println("Retakes:   " + mgr.retakeCount());
                                    System.out.println("\nStudents by GPA:");
                                    System.out.println(mgr.orderStudentsByGPA());
                                    break;
                                case 6:
                                    System.out.println("Enter news ID: ");
                                    String newsId = reader.readLine();
                                    System.out.println("Enter title: ");
                                    String newsTitle = reader.readLine();
                                    System.out.println("Enter text: ");
                                    String newsText = reader.readLine();
                                    mgr.addNews(newsId, newsTitle, newsText);
                                    Database.save();
                                    System.out.println("News added.");
                                    break;
                                case 7:
                                    System.out.println("=== Pending Registration Requests ===");
                                    System.out.println(mgr.viewRequests());
                                    break;
                                case 0:
                                    flag = false;
                                    break;
                            }
                        }
                    } else if(u instanceof Admin) {
                        boolean flag = true;
                        while(flag) {
                            Admin adm = (Admin) u;
                            System.out.println("/--------------------Admin's mode--------------------/");
                            System.out.println(
                                "    [1]          Add user\n" +
                                "    [2]          Remove user\n" +
                                "    [3]          Update user\n" +
                                "    [4]          View logs\n" +
                                "    [0]          Log out\n");

                            int chosen = Integer.parseInt(reader.readLine());
                            switch(chosen) {
                                case 1:
                                    System.out.println("Select type (1-Student, 2-Teacher, 3-Manager, 4-Librarian): ");
                                    int uType = Integer.parseInt(reader.readLine());
                                    System.out.println("Enter name: ");
                                    String uName = reader.readLine();
                                    System.out.println("Enter surname: ");
                                    String uSurname = reader.readLine();
                                    System.out.println("Enter birth date: ");
                                    String uBirth = reader.readLine();
                                    System.out.println("Enter phone: ");
                                    String uPhone = reader.readLine();
                                    System.out.println("Enter email: ");
                                    String uEmail = reader.readLine();
                                    System.out.println("Enter password: ");
                                    String uPass = reader.readLine();
                                    if(uType == 1) {
                                        System.out.println("Enter student ID: ");
                                        String uid = reader.readLine();
                                        System.out.println("Enter year of study: ");
                                        int year = Integer.parseInt(reader.readLine());
                                        adm.createStudent(uName, uSurname, uBirth, uPhone, uEmail, uPass, uid, year);
                                    } else if(uType == 2) {
                                        System.out.println("Enter experience: ");
                                        String exp = reader.readLine();
                                        adm.createTeacher(uName, uSurname, uBirth, uPhone, uEmail, uPass, exp);
                                    } else if(uType == 3) {
                                        adm.createManager(uName, uSurname, uBirth, uPhone, uEmail, uPass);
                                    } else if(uType == 4) {
                                        adm.createLibrarian(uName, uSurname, uBirth, uPhone, uEmail, uPass);
                                    }
                                    Database.save();
                                    System.out.println("User added.");
                                    break;
                                case 2:
                                    System.out.println("Enter login of user to remove: ");
                                    String removeLogin = reader.readLine();
                                    if(adm.deleteUser(removeLogin)) {
                                        System.out.println("User removed.");
                                    } else {
                                        System.out.println("User not found.");
                                    }
                                    Database.save();
                                    break;
                                case 3:
                                    System.out.println("=== All Users ===");
                                    System.out.println(adm.getUsers());
                                    break;
                                case 4:
                                    System.out.println("=== Log Files ===");
                                    adm.seeLogFiles();
                                    System.out.println(Database.getLogFiles());
                                    break;
                                case 0:
                                    flag = false;
                                    break;
                            }
                        }
                    } else if(u instanceof Librarian) {
                        boolean flag = true;
                        while(flag) {
                            Librarian lib = (Librarian) u;
                            System.out.println("/--------------------Librarian's mode--------------------/");
                            System.out.println(
                                "    [1]          View books\n" +
                                "    [2]          Add book\n" +
                                "    [3]          Remove book\n" +
                                "    [0]          Log out\n");

                            int chosen = Integer.parseInt(reader.readLine());
                            switch(chosen) {
                                case 1:
                                    System.out.println("=== Books ===");
                                    int bi = 0;
                                    for(Book book : Database.getBooks()) {
                                        bi++;
                                        System.out.println(bi + ") " + book);
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter book title: ");
                                    String bTitle = reader.readLine();
                                    System.out.println("Enter book ID: ");
                                    String bId = reader.readLine();
                                    System.out.println("Enter author: ");
                                    String bAuthor = reader.readLine();
                                    lib.addBook(bTitle, bId, bAuthor);
                                    Database.save();
                                    System.out.println("Book added.");
                                    break;
                                case 3:
                                    System.out.println("Enter book ID to remove: ");
                                    String removeId = reader.readLine();
                                    lib.removeBook(removeId);
                                    Database.save();
                                    System.out.println("Book removed.");
                                    break;
                                case 0:
                                    flag = false;
                                    break;
                            }
                        }
                    } else if(u instanceof TechSupportSpecialist) {
                        boolean flag = true;
                        while(flag) {
                            TechSupportSpecialist techSpec = (TechSupportSpecialist) u;
                            System.out.println("/--------------------Tech Support mode--------------------/");
                            System.out.println(
                                "    [1]          View new orders\n" +
                                "    [2]          Accept order\n" +
                                "    [3]          Reject order\n" +
                                "    [4]          View done orders\n" +
                                "    [0]          Log out\n");

                            int chosen = Integer.parseInt(reader.readLine());
                            switch(chosen) {
                                case 1:
                                    System.out.println("=== New Requests ===");
                                    int ri = 0;
                                    for(SupportRequest r : techSpec.viewNewRequests()) {
                                        ri++;
                                        System.out.println(ri + ") " + r);
                                    }
                                    break;
                                case 2:
                                    System.out.println("Enter request ID to accept: ");
                                    String acceptId = reader.readLine();
                                    for(SupportRequest r : Database.getSupportRequests()) {
                                        if(r.getId().equals(acceptId)) {
                                            techSpec.updateRequestStatus(r, RequestStatus.DONE);
                                            System.out.println("Request accepted and marked as done.");
                                            break;
                                        }
                                    }
                                    Database.save();
                                    break;
                                case 3:
                                    System.out.println("Enter request ID to reject: ");
                                    String rejectId = reader.readLine();
                                    for(SupportRequest r : Database.getSupportRequests()) {
                                        if(r.getId().equals(rejectId)) {
                                            techSpec.updateRequestStatus(r, RequestStatus.REJECTED);
                                            System.out.println("Request rejected.");
                                            break;
                                        }
                                    }
                                    Database.save();
                                    break;
                                case 4:
                                    System.out.println("=== Done Requests ===");
                                    int di = 0;
                                    for(SupportRequest r : Database.getSupportRequests()) {
                                        if(r.getStatus() == RequestStatus.DONE) {
                                            di++;
                                            System.out.println(di + ") " + r);
                                        }
                                    }
                                    break;
                                case 0:
                                    flag = false;
                                    break;
                            }
                        }
                    }
                } else {
                    System.out.println("Wrong password.");
                }
                break;
            }
        }
        if(input.equals("q")) {
            System.exit(0);
        }
    }
}

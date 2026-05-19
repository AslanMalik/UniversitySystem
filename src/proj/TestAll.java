package proj;

import java.util.*;

public class TestAll {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        sep("PARTICIPANT 2  —  proj.*");
        testMark();
        testTeacher();
        testCourse();
        testLesson();
        testManager();
        testEnums();
        System.out.printf("%n=== proj.* RESULT: %d passed, %d failed ===%n", passed, failed);
    }

    static void testMark() {
        System.out.println("\n--- Mark ---");

        Mark m = new Mark("OOP", "S001", 30.0, 30.0, 50.0);
        double expected = 30 * 0.2 + 30 * 0.2 + 50 * 0.6;
        check("getTotal() weighted formula (expected 42.0)",
                Math.abs(m.getTotal() - expected) < 0.001);

        check("isPassed() false when total=42 < 50",  !m.isPassed());

        Mark m2 = new Mark("OOP", "S002", 40.0, 40.0, 60.0);
        check("isPassed() true when total=52 >= 50",  m2.isPassed());

        check("getTotalGrade() == getTotal() (compat alias)", m.getTotalGrade() == m.getTotal());

        check("compareTo() m < m2 (42 < 52)", m.compareTo(m2) < 0);

        List<Mark> marks = new ArrayList<>(Arrays.asList(m2, m));
        Collections.sort(marks);
        check("sort ascending — m (42) comes first", marks.get(0) == m);

        Mark high = new Mark("Math", "S003", 50.0, 50.0, 100.0);
        check("convertToLetter() 80 → 'B'",  "B".equals(high.convertToLetter()));
        check("convertToGPA()   80 → '3.0'", "3.0".equals(high.convertToGPA()));

        Mark mCopy = new Mark("OOP", "S001", 99.0, 99.0, 99.0);
        check("equals() by courseName+studentId", m.equals(mCopy));
        check("hashCode() consistent",            m.hashCode() == mCopy.hashCode());
        check("toString() not null",              m.toString() != null);
    }

    static void testTeacher() {
        System.out.println("\n--- Teacher ---");

        Teacher t = new Teacher("Ada", "Lovelace", "10/12/1815",
                "700", "ada@uni.edu", "pass", TeacherTitle.PROFESSOR, "30 years");

        check("TeacherTitle field is PROFESSOR", t.getTitle() == TeacherTitle.PROFESSOR);

        t.addRating(4.0);
        t.addRating(2.0);
        check("addRating() running average after [4,2] = 3.0",
                Math.abs(t.viewRating() - 3.0) < 0.001);

        Student st = new Student("John", "Doe", "01/01/2002", "700",
                "john@uni.edu", "pass", "S01", 1, Faculty.FIT, Degree.BACHELOR);
        Mark mk = new Mark("OOP", "S01", 40.0, 40.0, 60.0);
        int before = Database.marks.size();
        t.putMark(st, mk);
        check("putMark(Student, Mark) persists to Database.marks",
                Database.marks.size() == before + 1);

        t.sendComplaint(Arrays.asList(st), UrgencyLevel.HIGH);
        check("sendComplaint(List, UrgencyLevel) adds to teacher.getComplaints()",
                !t.getComplaints().isEmpty());

        Course c = new Course("OOP", "Basics", 3, "CS101");
        c.enrollStudent(st);
        String report = t.generateReport(c);
        check("generateReport() contains enrolled student name", report.contains("John"));

        try { t.displayMenu(); check("displayMenu() runs without exception", true); }
        catch (Exception e) { check("displayMenu() runs without exception", false); }

        Teacher t2 = new Teacher("X", "Y", "Z", "Z", "ada@uni.edu",
                "x", TeacherTitle.TUTOR, "1y");
        check("equals() by email",     t.equals(t2));
        check("hashCode() consistent", t.hashCode() == t2.hashCode());
        check("toString() not null",   t.toString() != null);
    }

    static void testCourse() {
        System.out.println("\n--- Course ---");

        Course c = new Course("Algorithms", "Desc", 3, "CS200");
        c.setMaxStudents(2);

        Student s1 = new Student("A", "A", "d", "p", "a@e.com", "p",
                "SA", 1, Faculty.FIT, Degree.BACHELOR);
        Student s2 = new Student("B", "B", "d", "p", "b@e.com", "p",
                "SB", 1, Faculty.FIT, Degree.BACHELOR);
        c.enrollStudent(s1);
        c.enrollStudent(s2);
        check("enrollStudent() fills to maxStudents=2", c.getStudents().size() == 2);

        boolean threw = false;
        try { c.enrollStudent(new Student()); } catch (IllegalStateException e) { threw = true; }
        check("enrollStudent() throws IllegalStateException when full", threw);

        Teacher t1 = new Teacher("T1", "X", "d", "p", "t1@e.com", "p",
                TeacherTitle.LECTOR, "5y");
        Teacher t2 = new Teacher("T2", "X", "d", "p", "t2@e.com", "p",
                TeacherTitle.PROFESSOR, "10y");
        c.addInstructor(t1);
        c.addInstructor(t2);
        c.addInstructor(t1);
        check("addInstructor() supports 2 teachers, ignores duplicate",
                c.getInstructors().size() == 2);

        check("getSchedule() handles no lessons", c.getSchedule().contains("No lessons"));
        c.getLessons().add(new Lesson(LessonType.LECTURE, new Date(), "Room 101"));
        check("getSchedule() lists lesson room",  c.getSchedule().contains("Room 101"));

        c.setType(CourseType.MAJOR);
        check("CourseType.MAJOR set correctly", c.getType() == CourseType.MAJOR);

        check("maxStudents defaults to 30", new Course("X", 1, "XX").getMaxStudents() == 30);

        Course c2 = new Course("Other", 2, "CS200");
        check("equals() by courseId",    c.equals(c2));
        check("hashCode() consistent",   c.hashCode() == c2.hashCode());
        check("toString() not null",     c.toString() != null);
    }

    static void testLesson() {
        System.out.println("\n--- Lesson ---");

        Lesson l = new Lesson(LessonType.PRACTICE, new Date(), "Lab 5");
        check("LessonType.PRACTICE set via enum constructor", l.getType() == LessonType.PRACTICE);
        check("getInfo() not null",           l.getInfo() != null);
        check("getInfo() contains room",      l.getInfo().contains("Lab 5"));
        check("toString() delegates to getInfo", l.toString().equals(l.getInfo()));

        Lesson l2 = new Lesson("LECTURE", new Date(), "Room 1");
        check("String 'LECTURE' → LessonType.LECTURE", l2.getType() == LessonType.LECTURE);

        Lesson l3 = new Lesson(LessonType.PRACTICE, l.getDate(), "Lab 5");
        check("equals() by type+date+room", l.equals(l3));
        check("hashCode() consistent",      l.hashCode() == l3.hashCode());
    }

    static void testManager() {
        System.out.println("\n--- Manager ---");

        Manager mgr = new Manager("Dana", "A", "d", "p",
                "dana@uni.edu", "p", ManagerType.DEPARTMENT);
        check("ManagerType.DEPARTMENT field set", mgr.getType() == ManagerType.DEPARTMENT);
        check("managedCourses starts empty",      mgr.getManagedCourses().isEmpty());

        Course c = new Course("Calculus", 3, "MATH101");
        mgr.addCourse(c);
        check("addCourse() adds to managedCourses", mgr.getManagedCourses().contains(c));

        Teacher t = new Teacher("Prof", "X", "d", "p", "prof@uni.edu",
                "p", TeacherTitle.ASSOCIATE_PROFESSOR, "8y");
        mgr.assignTeacher(t, c);
        check("assignTeacher() adds Teacher to course.getInstructors()",
                c.getInstructors().contains(t));

        Student s = new Student("Eve", "X", "d", "p", "e@uni.edu",
                "p", "S99", 1, Faculty.FIT, Degree.BACHELOR);
        String report = mgr.createReport(Arrays.asList(s));
        check("createReport(List<Student>) contains student name", report.contains("Eve"));

        News pjNews = new News("1", "Test", "body");
        int before = Database.news.size();
        mgr.manageNews(pjNews);
        mgr.manageNews(pjNews);
        check("manageNews() prevents duplicates in DB",
                Database.news.size() == before + 1);

        try { mgr.displayMenu(); check("displayMenu() runs without exception", true); }
        catch (Exception e) { check("displayMenu() runs without exception", false); }

        Manager mgr2 = new Manager("X", "Y", "d", "p", "dana@uni.edu", "p", ManagerType.OR);
        check("equals() by email",    mgr.equals(mgr2));
        check("hashCode() consistent", mgr.hashCode() == mgr2.hashCode());
        check("toString() not null",   mgr.toString() != null);
    }

    static void testEnums() {
        System.out.println("\n--- Enums ---");

        TeacherTitle[] tt = TeacherTitle.values();
        check("TeacherTitle has 5 values",           tt.length == 5);
        check("TeacherTitle.TUTOR exists",           TeacherTitle.valueOf("TUTOR") != null);
        check("TeacherTitle.LECTOR exists",          TeacherTitle.valueOf("LECTOR") != null);
        check("TeacherTitle.SENIOR_LECTOR exists",   TeacherTitle.valueOf("SENIOR_LECTOR") != null);
        check("TeacherTitle.ASSOCIATE_PROFESSOR",    TeacherTitle.valueOf("ASSOCIATE_PROFESSOR") != null);
        check("TeacherTitle.PROFESSOR exists",       TeacherTitle.valueOf("PROFESSOR") != null);

        check("CourseType.MAJOR exists",        CourseType.valueOf("MAJOR") != null);
        check("CourseType.MINOR exists",        CourseType.valueOf("MINOR") != null);
        check("CourseType.FREE_ELECTIVE exists",CourseType.valueOf("FREE_ELECTIVE") != null);

        check("LessonType.LECTURE exists",      LessonType.valueOf("LECTURE") != null);
        check("LessonType.PRACTICE exists",     LessonType.valueOf("PRACTICE") != null);

        check("ManagerType.OR exists",          ManagerType.valueOf("OR") != null);
        check("ManagerType.DEPARTMENT exists",  ManagerType.valueOf("DEPARTMENT") != null);
        check("ManagerType.DEAN_OFFICE exists", ManagerType.valueOf("DEAN_OFFICE") != null);

        check("UrgencyLevel.LOW exists",        UrgencyLevel.valueOf("LOW") != null);
        check("UrgencyLevel.MEDIUM exists",     UrgencyLevel.valueOf("MEDIUM") != null);
        check("UrgencyLevel.HIGH exists",       UrgencyLevel.valueOf("HIGH") != null);
    }

    static void sep(String title) {
        System.out.println("\n====================================================");
        System.out.println("  " + title);
        System.out.println("====================================================");
    }

    static void check(String desc, boolean ok) {
        System.out.printf("  %s  %s%n", ok ? "PASS" : "FAIL", desc);
        if (ok) passed++; else failed++;
    }
}

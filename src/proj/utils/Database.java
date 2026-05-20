package proj.utils;

import proj.academic.Book;
import proj.academic.Course;
import proj.academic.Lesson;
import proj.academic.Mark;
import proj.communication.Complaint;
import proj.communication.Message;
import proj.communication.News;
import proj.communication.SupportRequest;
import proj.models.User;
import proj.research.ResearchJournal;
import proj.research.ResearchPaper;
import proj.research.ResearchProject;

import java.io.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Database implements Serializable {

    private static final long serialVersionUID = 1L;

    // ═══ SINGLETON ═══
    private static final Database instance = new Database();

    public static Database getInstance() { return instance; }

    private Database() {}

    // ═══ FIELDS (private) ═══
    private static List<Course> courses = new Vector<>();
    private static List<User> users = new Vector<>();
    private static Map<String, Course> studentRegistration = new HashMap<>();
    private static Map<String, Integer> teacherRatings = new HashMap<>();
    private static List<Mark> marks = new Vector<>();
    private static Map<String, Book> orders = new HashMap<>();
    private static List<Message> messages = new Vector<>();
    private static List<File> files = new Vector<>();
    private static Map<String, String> logFiles = new HashMap<>();
    private static Set<Book> books = new HashSet<>();
    private static List<News> news = new Vector<>();
    private static List<Lesson> lessons = new Vector<>();
    private static List<Complaint> complaints = new ArrayList<>();
    private static List<SupportRequest> supportRequests = new ArrayList<>();
    private static List<ResearchPaper> researchPapers = new ArrayList<>();
    private static List<ResearchProject> researchProjects = new ArrayList<>();
    private static List<ResearchJournal> researchJournals = new ArrayList<>();

    // ═══ GETTERS / SETTERS ═══
    public static List<Course> getCourses() { return courses; }
    public static void setCourses(List<Course> c) { courses = c; }

    public static List<User> getUsers() { return users; }
    public static void setUsers(List<User> u) { users = u; }

    public static Map<String, Course> getStudentRegistration() { return studentRegistration; }
    public static void setStudentRegistration(Map<String, Course> sr) { studentRegistration = sr; }

    public static Map<String, Integer> getTeacherRatings() { return teacherRatings; }
    public static void setTeacherRatings(Map<String, Integer> tr) { teacherRatings = tr; }

    public static List<Mark> getMarks() { return marks; }
    public static void setMarks(List<Mark> m) { marks = m; }

    public static Map<String, Book> getOrders() { return orders; }
    public static void setOrders(Map<String, Book> o) { orders = o; }

    public static List<Message> getMessages() { return messages; }
    public static void setMessages(List<Message> m) { messages = m; }

    public static List<File> getFiles() { return files; }
    public static void setFiles(List<File> f) { files = f; }

    public static Map<String, String> getLogFiles() { return logFiles; }
    public static void setLogFiles(Map<String, String> lf) { logFiles = lf; }

    public static Set<Book> getBooks() { return books; }
    public static void setBooks(Set<Book> b) { books = b; }

    public static List<News> getNews() { return news; }
    public static void setNews(List<News> n) { news = n; }

    public static List<Lesson> getLessons() { return lessons; }
    public static void setLessons(List<Lesson> l) { lessons = l; }

    public static List<Complaint> getComplaints() { return complaints; }
    public static void setComplaints(List<Complaint> c) { complaints = c; }

    public static List<SupportRequest> getSupportRequests() { return supportRequests; }
    public static void setSupportRequests(List<SupportRequest> sr) { supportRequests = sr; }

    public static List<ResearchPaper> getResearchPapers() { return researchPapers; }
    public static void setResearchPapers(List<ResearchPaper> rp) { researchPapers = rp; }

    public static List<ResearchProject> getResearchProjects() { return researchProjects; }
    public static void setResearchProjects(List<ResearchProject> rp) { researchProjects = rp; }

    public static List<ResearchJournal> getResearchJournals() { return researchJournals; }
    public static void setResearchJournals(List<ResearchJournal> rj) { researchJournals = rj; }

    // ═══ SAVE / LOAD ═══
    public static void save() {
        saveObject("users.txt", users);
        saveObject("courses.txt", courses);
        saveObject("marks.txt", marks);
        saveObject("messages.txt", messages);
        saveObject("news.txt", news);
        saveObject("files.txt", files);
        saveObject("books.txt", books);
        saveObject("orders.txt", orders);
        saveObject("studentReg.txt", studentRegistration);
        saveObject("complaints.txt", complaints);
        saveObject("supportRequests.txt", supportRequests);
        saveObject("researchPapers.txt", researchPapers);
        saveObject("researchProjects.txt", researchProjects);
        saveObject("researchJournals.txt", researchJournals);
    }

    public static void load() {
        users = (List<User>) loadObject("users.txt", new Vector<>());
        courses = (List<Course>) loadObject("courses.txt", new Vector<>());
        marks = (List<Mark>) loadObject("marks.txt", new Vector<>());
        messages = (List<Message>) loadObject("messages.txt", new Vector<>());
        news = (List<News>) loadObject("news.txt", new Vector<>());
        files = (List<File>) loadObject("files.txt", new Vector<>());
        books = (Set<Book>) loadObject("books.txt", new HashSet<>());
        orders = (Map<String, Book>) loadObject("orders.txt", new HashMap<>());
        studentRegistration = (Map<String, Course>) loadObject("studentReg.txt", new HashMap<>());
        complaints = (List<Complaint>) loadObject("complaints.txt", new ArrayList<>());
        supportRequests = (List<SupportRequest>) loadObject("supportRequests.txt", new ArrayList<>());
        researchPapers = (List<ResearchPaper>) loadObject("researchPapers.txt", new ArrayList<>());
        researchProjects = (List<ResearchProject>) loadObject("researchProjects.txt", new ArrayList<>());
        researchJournals = (List<ResearchJournal>) loadObject("researchJournals.txt", new ArrayList<>());
    }

    private static void saveObject(String filename, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println(filename + ": save failed — " + e.getMessage());
        }
    }

    private static Object loadObject(String filename, Object defaultValue) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(filename + ": load failed — " + e.getMessage());
            return defaultValue;
        }
    }
}
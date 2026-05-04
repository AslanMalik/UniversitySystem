package proj;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

@SuppressWarnings("unchecked")

public class Database implements Serializable {
    protected static Vector<Course> courses = new Vector<>();
    protected static Vector<User> users = new Vector<>();
    protected static HashMap<String, Course> studentRegistration = new HashMap<>();
    protected static HashMap<String, Integer> teacherRatings = new HashMap<>();
    protected static Vector<Mark> marks = new Vector<>();
    protected static HashMap<String, Book> orders = new HashMap<>();
    protected static Vector<Message> messages = new Vector<>();
    protected static Vector<File> files = new Vector<>();
    protected static HashMap<String, String> logFiles = new HashMap<>();
    protected static HashSet<Book> books = new HashSet<>();
    protected static Vector<News> news = new Vector<>();
    protected static Vector<Lesson> lessons = new Vector<>();
    protected static List<Complaint> complaints = new ArrayList<>();
    protected static List<SupportRequest> supportRequests = new ArrayList<>();
    protected static List<ResearchPaper> researchPapers = new ArrayList<>();
    protected static List<ResearchProject> researchProjects = new ArrayList<>();
    protected static List<ResearchJournal> researchJournals = new ArrayList<>();

    public static Vector<Course> getCourses() {
        return courses;
    }

    public static void setCourses(Vector<Course> courses) {
        Database.courses = courses;
    }

    public static Vector<User> getUsers() {
        return users;
    }

    public static void setUsers(Vector<User> users) {
        Database.users = users;
    }

    public static HashMap<String, Course> getStudentRegistration() {
        return studentRegistration;
    }

    public static void setStudentRegistration(HashMap<String, Course> studentRegistration) {
        Database.studentRegistration = studentRegistration;
    }

    public static HashMap<String, Integer> getTeacherRatings() {
        return teacherRatings;
    }

    public static void setTeacherRatings(HashMap<String, Integer> teacherRatings) {
        Database.teacherRatings = teacherRatings;
    }

    public static Vector<Mark> getMarks() {
        return marks;
    }

    public static void setMarks(Vector<Mark> marks) {
        Database.marks = marks;
    }

    public static HashMap<String, Book> getOrders() {
        return orders;
    }

    public static void setOrders(HashMap<String, Book> orders) {
        Database.orders = orders;
    }

    public static Vector<Message> getMessages() {
        return messages;
    }

    public static void setMessages(Vector<Message> messages) {
        Database.messages = messages;
    }

    public static Vector<File> getFiles() {
        return files;
    }

    public static void setFiles(Vector<File> files) {
        Database.files = files;
    }

    public static HashMap<String, String> getLogFiles() {
        return logFiles;
    }

    public static void setLogFiles(HashMap<String, String> logFiles) {
        Database.logFiles = logFiles;
    }

    public static HashSet<Book> getBooks() {
        return books;
    }

    public static void setBooks(HashSet<Book> books) {
        Database.books = books;
    }

    public static Vector<News> getNews() {
        return news;
    }

    public static void setNews(Vector<News> news) {
        Database.news = news;
    }

    public static Vector<Lesson> getLessons() {
        return lessons;
    }

    public static void setLessons(Vector<Lesson> lessons) {
        Database.lessons = lessons;
    }
    
    // Getters and Setters для новых полей
    public static List<Complaint> getComplaints() { 
        return complaints; 
    }
    
    public static void setComplaints(List<Complaint> complaints) { 
        Database.complaints = complaints; 
    }
    
    public static List<SupportRequest> getSupportRequests() { 
        return supportRequests; 
    }
    
    public static void setSupportRequests(List<SupportRequest> supportRequests) { 
        Database.supportRequests = supportRequests; 
    }
    
    public static List<ResearchPaper> getResearchPapers() { 
        return researchPapers; 
    }
    
    public static void setResearchPapers(List<ResearchPaper> researchPapers) { 
        Database.researchPapers = researchPapers; 
    }
    
    public static List<ResearchProject> getResearchProjects() { 
        return researchProjects; 
    }
    
    public static void setResearchProjects(List<ResearchProject> researchProjects) { 
        Database.researchProjects = researchProjects; 
    }
    
    public static List<ResearchJournal> getResearchJournals() { 
        return researchJournals; 
    }
    
    public static void setResearchJournals(List<ResearchJournal> researchJournals) { 
        Database.researchJournals = researchJournals; 
    }

    public static final Database instance; 
   
    static {
        instance = new Database(); 
    }

    private Database() {
        this.teacherRatings = new HashMap<String, Integer>();
    }
    
    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static ObjectOutputStream oos;
    public static ObjectInputStream oin;
                                   
    
    public static void save() {
        saveCourses();
        saveFiles();
        saveMarks();
        saveMessages();
        saveNews();
        saveUsers();
        saveBooks();
        saveOrders();
        saveStudentReg();
        saveComplaints();
        saveSupportRequests();
        saveResearchPapers();
        saveResearchProjects();
        saveResearchJournals();
    }
    
    public static void load() {
        loadCourses();
        loadFiles();
        loadMarks();
        loadMessages();
        loadNews();
        loadUsers();
        loadBooks();
        loadOrders();
        loadStudentReg();
        loadComplaints();
        loadSupportRequests();
        loadResearchPapers();
        loadResearchProjects();
        loadResearchJournals();
    }
    
    private static void saveUsers() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("users.txt"))) {
            oot.writeObject(users);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("users.txt: IOException");
        }
    }
    
    private static void saveMarks() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("marks.txt"))) {
            oot.writeObject(marks);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("marks.txt: IOException");
        }
    }
    
    private static void saveCourses() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("courses.txt"))) {
            oot.writeObject(courses);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("courses.txt: IOException");
        }
    }

    private static void saveNews() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("news.txt"))) {
            oot.writeObject(news);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("news.txt: IOException");
        }
    }
    
    private static void saveMessages() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("messages.txt"))) {
            oot.writeObject(messages);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("messages.txt: IOException");
        }
    }
    
    private static void saveFiles() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("files.txt"))) {
            oot.writeObject(files);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("files.txt: IOException");
        }
    }

    private static void saveBooks() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("books.txt"))) {
            oot.writeObject(books);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("books.txt: IOException");
        }
    }

    private static void saveStudentReg() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("studentReg.txt"))) {
            oot.writeObject(studentRegistration);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("studentReg.txt: IOException");
        }
    }
    
    private static void saveOrders() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("orders.txt"))) {
            oot.writeObject(orders);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("orders.txt: IOException");
        }
    }
    
    // НОВЫЕ МЕТОДЫ СОХРАНЕНИЯ
    private static void saveComplaints() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("complaints.txt"))) {
            oot.writeObject(complaints);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("complaints.txt: IOException");
        }
    }
    
    private static void saveSupportRequests() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("supportRequests.txt"))) {
            oot.writeObject(supportRequests);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("supportRequests.txt: IOException");
        }
    }
    
    private static void saveResearchPapers() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("researchPapers.txt"))) {
            oot.writeObject(researchPapers);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("researchPapers.txt: IOException");
        }
    }
    
    private static void saveResearchProjects() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("researchProjects.txt"))) {
            oot.writeObject(researchProjects);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("researchProjects.txt: IOException");
        }
    }
    
    private static void saveResearchJournals() {
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("researchJournals.txt"))) {
            oot.writeObject(researchJournals);
            oot.flush();
        }
        catch (IOException e) {
            System.err.println("researchJournals.txt: IOException");
        }
    }
    
    private static void loadUsers() {
        try {
            fis = new FileInputStream("users.txt");
            oin = new ObjectInputStream(fis); 
            users = (Vector<User>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            users = new Vector<>();
            System.err.println("users.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            users = new Vector<>();
            System.err.println("users.txt: ClassNotFoundException");
        }   
    }
    
    private static void loadMarks() {
        try {
            fis = new FileInputStream("marks.txt");
            oin = new ObjectInputStream(fis); 
            marks = (Vector<Mark>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            marks = new Vector<>();
            System.err.println("marks.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            marks = new Vector<>();
            System.err.println("marks.txt: ClassNotFoundException");
        }
    }
    
    private static void loadCourses() {
        try {
            fis = new FileInputStream("courses.txt");
            oin = new ObjectInputStream(fis); 
            courses = (Vector<Course>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (ClassNotFoundException e) {
            courses = new Vector<>();
            System.err.println("courses.txt: ClassNotFoundException");
        }
        catch (IOException e) {
            courses = new Vector<>();
            System.err.println("courses.txt: IOException");
        }
    }
    
    private static void loadNews() {
        try {
            fis = new FileInputStream("news.txt");
            oin = new ObjectInputStream(fis); 
            news = (Vector<News>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (ClassNotFoundException e) {
            news = new Vector<>();
            System.err.println("news.txt: ClassNotFoundException");
        }
        catch (IOException e) {
            news = new Vector<>();
            System.err.println("news.txt: IOException");
        }
    }
    
    private static void loadMessages() {
        try {
            fis = new FileInputStream("messages.txt");
            oin = new ObjectInputStream(fis); 
            messages = (Vector<Message>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            messages = new Vector<>();
            System.err.println("messages.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            messages = new Vector<>();
            System.err.println("messages.txt: ClassNotFoundException");
        }
    }
    
    private static void loadFiles() {
        try {
            fis = new FileInputStream("files.txt");
            oin = new ObjectInputStream(fis); 
            files = (Vector<File>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            files = new Vector<>();
            System.err.println("files.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            files = new Vector<>();
            System.err.println("files.txt: ClassNotFoundException");
        }
    }

    private static void loadBooks() {
        try {
            fis = new FileInputStream("books.txt");
            oin = new ObjectInputStream(fis); 
            books = (HashSet<Book>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            books = new HashSet<>();
            System.err.println("books.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            books = new HashSet<>();
            System.err.println("books.txt: ClassNotFoundException");
        }
    }

    private static void loadStudentReg() {
        try {
            fis = new FileInputStream("studentReg.txt");
            oin = new ObjectInputStream(fis); 
            studentRegistration = (HashMap<String, Course>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            studentRegistration = new HashMap<>();
            System.err.println("studentReg.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            studentRegistration = new HashMap<>();
            System.err.println("studentReg.txt: ClassNotFoundException");
        }
    }
    
    private static void loadOrders() {
        try {
            fis = new FileInputStream("orders.txt");
            oin = new ObjectInputStream(fis); 
            orders = (HashMap<String, Book>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            orders = new HashMap<>();
            System.err.println("orders.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            orders = new HashMap<>();
            System.err.println("orders.txt: ClassNotFoundException");
        }
    }
    
    private static void loadComplaints() {
        try {
            fis = new FileInputStream("complaints.txt");
            oin = new ObjectInputStream(fis); 
            complaints = (List<Complaint>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            complaints = new ArrayList<>();
            System.err.println("complaints.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            complaints = new ArrayList<>();
            System.err.println("complaints.txt: ClassNotFoundException");
        }
    }
    
    private static void loadSupportRequests() {
        try {
            fis = new FileInputStream("supportRequests.txt");
            oin = new ObjectInputStream(fis); 
            supportRequests = (List<SupportRequest>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            supportRequests = new ArrayList<>();
            System.err.println("supportRequests.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            supportRequests = new ArrayList<>();
            System.err.println("supportRequests.txt: ClassNotFoundException");
        }
    }
    
    private static void loadResearchPapers() {
        try {
            fis = new FileInputStream("researchPapers.txt");
            oin = new ObjectInputStream(fis); 
            researchPapers = (List<ResearchPaper>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            researchPapers = new ArrayList<>();
            System.err.println("researchPapers.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            researchPapers = new ArrayList<>();
            System.err.println("researchPapers.txt: ClassNotFoundException");
        }
    }
    
    private static void loadResearchProjects() {
        try {
            fis = new FileInputStream("researchProjects.txt");
            oin = new ObjectInputStream(fis); 
            researchProjects = (List<ResearchProject>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            researchProjects = new ArrayList<>();
            System.err.println("researchProjects.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            researchProjects = new ArrayList<>();
            System.err.println("researchProjects.txt: ClassNotFoundException");
        }
    }
    
    private static void loadResearchJournals() {
        try {
            fis = new FileInputStream("researchJournals.txt");
            oin = new ObjectInputStream(fis); 
            researchJournals = (List<ResearchJournal>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch (IOException e) {
            researchJournals = new ArrayList<>();
            System.err.println("researchJournals.txt: IOException");
        }
        catch (ClassNotFoundException e) {
            researchJournals = new ArrayList<>();
            System.err.println("researchJournals.txt: ClassNotFoundException");
        }
    }
}
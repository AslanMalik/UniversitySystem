package university.models;

import university.enums.Language;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter LOG_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private List<String> logFiles = new ArrayList<>();

    public Admin(String login, String password, Language language,
                 String firstName, String lastName, double salary, String department) {
        super(login, password, language, firstName, lastName, salary, department);
    }

    public void addUser(User user) {
        log("User added: " + user.getLogin());
    }

    public void removeUser(String userId) {
        log("User removed: " + userId);
    }

    public void updateUser(User user) {
        log("User updated: " + user.getLogin());
    }

    public List<String> viewLogs() {
        return List.copyOf(logFiles);
    }

    public void log(String action) {
        String entry = "[" + LocalDateTime.now().format(LOG_FORMAT) + "] " + action;
        logFiles.add(entry);
        System.out.println(entry);
    }

    @Override
    public void displayMenu() {
        System.out.println("=== Admin Menu ===");
        System.out.println("1. Add user");
        System.out.println("2. Remove user");
        System.out.println("3. Update user");
        System.out.println("4. View audit logs");
        System.out.println("5. Send message");
        System.out.println("0. Logout");
    }

    public List<String> getLogFiles() {
        return logFiles;
    }

    public void setLogFiles(List<String> logFiles) {
        this.logFiles = logFiles;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "login='" + getLogin() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", logCount=" + logFiles.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(logFiles, admin.logFiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), logFiles);
    }
}

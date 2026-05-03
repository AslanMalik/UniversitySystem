package university.models;

import university.enums.Language;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a system administrator who can manage university users
 * and maintain an audit log of administrative actions.
 */
public class Admin extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter LOG_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private List<String> logFiles = new ArrayList<>();

    /**
     * Constructs an Admin with full credential and employment information.
     *
     * @param login      unique login name
     * @param password   password
     * @param language   preferred language
     * @param firstName  first name
     * @param lastName   last name
     * @param salary     monthly salary
     * @param department department name
     */
    public Admin(String login, String password, Language language,
                 String firstName, String lastName, double salary, String department) {
        super(login, password, language, firstName, lastName, salary, department);
    }

    /**
     * Adds a new user to the system and logs the action.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        log("User added: " + user.getLogin());
    }

    /**
     * Removes a user identified by their UUID string and logs the action.
     *
     * @param userId the UUID string of the user to remove
     */
    public void removeUser(String userId) {
        log("User removed: " + userId);
    }

    /**
     * Updates an existing user's information and logs the action.
     *
     * @param user the user whose data has been updated
     */
    public void updateUser(User user) {
        log("User updated: " + user.getLogin());
    }

    /**
     * Returns the complete list of audit log entries.
     *
     * @return list of timestamped log strings
     */
    public List<String> viewLogs() {
        return List.copyOf(logFiles);
    }

    /**
     * Records a timestamped audit entry.
     *
     * @param action description of the administrative action
     */
    public void log(String action) {
        String entry = "[" + LocalDateTime.now().format(LOG_FORMAT) + "] " + action;
        logFiles.add(entry);
        System.out.println(entry);
    }

    /**
     * Displays the administrator menu options.
     */
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

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

    /**
     * Returns the list of log file entries.
     *
     * @return log entries
     */
    public List<String> getLogFiles() {
        return logFiles;
    }

    /**
     * Replaces the log file list.
     *
     * @param logFiles new log list
     */
    public void setLogFiles(List<String> logFiles) {
        this.logFiles = logFiles;
    }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

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

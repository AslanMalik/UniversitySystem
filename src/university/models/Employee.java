package university.models;

import university.enums.Language;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract class representing a university employee.
 * Extends {@link User} and adds employment-specific information such as
 * name, salary, and department.
 */
public abstract class Employee extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private double salary;
    private String department;

    /**
     * Constructs an Employee with full credentials and employment details.
     *
     * @param login      unique login name
     * @param password   password
     * @param language   preferred language
     * @param firstName  employee's first name
     * @param lastName   employee's last name
     * @param salary     monthly salary
     * @param department department name
     */
    public Employee(String login, String password, Language language,
                    String firstName, String lastName, double salary, String department) {
        super(login, password, language);
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    /**
     * Sends a text message to another user.
     * Prints a confirmation line to standard output.
     *
     * @param receiver the user who receives the message
     * @param content  the text content of the message
     */
    public void sendMessage(User receiver, String content) {
        System.out.println("Message sent to: " + receiver.getLogin());
    }

    /**
     * Returns a formatted summary of this employee's information.
     *
     * @return string containing name, department, and salary
     */
    public String getInfo() {
        return "Name: " + firstName + " " + lastName +
                " | Department: " + department +
                " | Salary: " + salary;
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

    /**
     * Returns the first name.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name.
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the monthly salary.
     *
     * @return salary amount
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the monthly salary.
     *
     * @param salary new salary amount
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Returns the department name.
     *
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department name.
     *
     * @param department new department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Employee{" +
                "login='" + getLogin() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, salary, department);
    }
}

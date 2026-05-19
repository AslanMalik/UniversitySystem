package university.models;

import university.enums.Language;

import java.io.Serializable;
import java.util.Objects;

public abstract class Employee extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private double salary;
    private String department;

    public Employee(String login, String password, Language language,
                    String firstName, String lastName, double salary, String department) {
        super(login, password, language);
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public void sendMessage(User receiver, String content) {
        System.out.println("Message sent to: " + receiver.getLogin());
    }

    public String getInfo() {
        return "Name: " + firstName + " " + lastName +
                " | Department: " + department +
                " | Salary: " + salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

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

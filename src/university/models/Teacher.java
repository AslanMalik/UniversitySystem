package university.models;

import university.enums.Language;

import java.io.Serializable;

public class Teacher extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private double averageRating = 0.0;
    private int ratingCount = 0;

    public Teacher(String login, String password, Language language,
                   String firstName, String lastName, double salary, String department) {
        super(login, password, language, firstName, lastName, salary, department);
    }

    public void addRating(double rating) {
        averageRating = (averageRating * ratingCount + rating) / (++ratingCount);
    }

    public double getAverageRating() { return averageRating; }

    @Override
    public void displayMenu() {
        System.out.println("=== Teacher Menu ===");
        System.out.println("0. Logout");
    }

    @Override
    public String toString() {
        return "Teacher{login='" + getLogin() + "', name='" + getFirstName() + " " + getLastName() + "'}";
    }
}

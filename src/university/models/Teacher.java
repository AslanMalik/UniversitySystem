package university.models;

import university.enums.Language;

import java.io.Serializable;

/**
 * Placeholder — implemented by Participant 2.
 * Represents a university teacher/lecturer.
 */
public class Teacher extends Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private double averageRating = 0.0;
    private int ratingCount = 0;

    /** @param login      login name
     *  @param password   password
     *  @param language   preferred language
     *  @param firstName  first name
     *  @param lastName   last name
     *  @param salary     salary
     *  @param department department */
    public Teacher(String login, String password, Language language,
                   String firstName, String lastName, double salary, String department) {
        super(login, password, language, firstName, lastName, salary, department);
    }

    /**
     * Records a student rating and updates the running average.
     *
     * @param rating rating value submitted by a student
     */
    public void addRating(double rating) {
        averageRating = (averageRating * ratingCount + rating) / (++ratingCount);
    }

    /** @return current average rating */
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

package university.models;

import java.io.Serializable;

/**
 * Placeholder — implemented by Participant 2.
 * Represents an academic course offered by the university.
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int credits;

    /** @param name    course name
     *  @param credits credit hours */
    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    /** @return course name */
    public String getName() { return name; }

    /** @return credit hours */
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return "Course{name='" + name + "', credits=" + credits + '}';
    }
}

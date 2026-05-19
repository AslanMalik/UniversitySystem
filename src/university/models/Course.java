package university.models;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int credits;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() { return name; }

    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return "Course{name='" + name + "', credits=" + credits + '}';
    }
}

package university.models;

import java.io.Serializable;

/**
 * Placeholder — implemented by Participant 2.
 * Represents a grade/mark received by a student for a course.
 */
public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseName;
    private double score;

    /** @param courseName name of the graded course
     *  @param score      numeric score */
    public Mark(String courseName, double score) {
        this.courseName = courseName;
        this.score = score;
    }

    /** @return name of the graded course */
    public String getCourseName() { return courseName; }

    /** @return numeric score */
    public double getScore() { return score; }

    /**
     * A mark is considered passed if the score is 50 or above.
     *
     * @return {@code true} if score >= 50
     */
    public boolean isPassed() { return score >= 50.0; }

    @Override
    public String toString() {
        return "Mark{course='" + courseName + "', score=" + score + ", passed=" + isPassed() + '}';
    }
}

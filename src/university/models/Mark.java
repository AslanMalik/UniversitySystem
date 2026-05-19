package university.models;

import java.io.Serializable;

public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseName;
    private double score;

    public Mark(String courseName, double score) {
        this.courseName = courseName;
        this.score = score;
    }

    public String getCourseName() { return courseName; }

    public double getScore() { return score; }

    public boolean isPassed() { return score >= 50.0; }

    @Override
    public String toString() {
        return "Mark{course='" + courseName + "', score=" + score + ", passed=" + isPassed() + '}';
    }
}

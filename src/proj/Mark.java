package proj;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a student's mark for a course, composed of two attestation scores
 * and a final exam score combined into a weighted total.
 */
public class Mark implements Serializable, Comparable<Mark> {

    private static final long serialVersionUID = 1L;

    protected String courseName;
    private String studentId;
    private Double firstAtt;
    private Double secondAtt;
    private Double finalGrade;

    /**
     * Constructs a Mark with the given score components.
     *
     * @param courseName  the name of the course
     * @param studentId   the identifier of the student
     * @param firstAtt    first attestation score (0–100, weighted 20%)
     * @param secondAtt   second attestation score (0–100, weighted 20%)
     * @param finalGrade  final exam score (0–100, weighted 60%)
     */
    public Mark(String courseName, String studentId,
                Double firstAtt, Double secondAtt, Double finalGrade) {
        this.courseName = courseName;
        this.studentId = studentId;
        this.firstAtt = firstAtt;
        this.secondAtt = secondAtt;
        this.finalGrade = finalGrade;
    }

    // ==================== Getters / Setters ====================

    /** @return the name of the course this mark belongs to */
    public String getCourseName() { return courseName; }

    /** @param courseName the course name to set */
    public void setCourseName(String courseName) { this.courseName = courseName; }

    /** @return the student's identifier */
    public String getStudentId() { return studentId; }

    /** @param studentId the student identifier to set */
    public void setStudentId(String studentId) { this.studentId = studentId; }

    /** @return the first attestation score */
    public Double getFirstAtt() { return firstAtt; }

    /** @param firstAtt the first attestation score to set */
    public void setFirstAtt(Double firstAtt) { this.firstAtt = firstAtt; }

    /** @return the second attestation score */
    public Double getSecondAtt() { return secondAtt; }

    /** @param secondAtt the second attestation score to set */
    public void setSecondAtt(Double secondAtt) { this.secondAtt = secondAtt; }

    /** @return the final exam score */
    public Double getFinalGrade() { return finalGrade; }

    /** @param finalGrade the final exam score to set */
    public void setFinalGrade(Double finalGrade) { this.finalGrade = finalGrade; }

    // ==================== Core Methods ====================

    /**
     * Computes the weighted total grade:
     * {@code firstAtt * 0.2 + secondAtt * 0.2 + finalGrade * 0.6}.
     *
     * @return the weighted total (0–100)
     */
    public double getTotal() {
        return firstAtt * 0.2 + secondAtt * 0.2 + finalGrade * 0.6;
    }

    /**
     * Backward-compatible alias for {@link #getTotal()}.
     *
     * @return the weighted total grade
     */
    public double getTotalGrade() {
        return getTotal();
    }

    /**
     * Returns {@code true} if the student passed (total grade >= 50).
     *
     * @return {@code true} if passed, {@code false} otherwise
     */
    public boolean isPassed() {
        return getTotal() >= 50;
    }

    /**
     * Compares this mark to another by total grade ascending.
     *
     * @param other the other Mark to compare against
     * @return negative if this is less, zero if equal, positive if greater
     */
    @Override
    public int compareTo(Mark other) {
        return Double.compare(this.getTotal(), other.getTotal());
    }

    // ==================== Conversion Helpers ====================

    /**
     * Converts the weighted total to a letter grade (A through F).
     *
     * @return the letter grade string
     */
    public String convertToLetter() {
        double total = getTotal();
        if (total >= 95) return "A";
        if (total >= 90) return "A-";
        if (total >= 85) return "B+";
        if (total >= 80) return "B";
        if (total >= 75) return "B-";
        if (total >= 70) return "C+";
        if (total >= 65) return "C";
        if (total >= 60) return "C-";
        if (total >= 55) return "D+";
        if (total >= 50) return "D";
        return "F";
    }

    /**
     * Converts the weighted total to a GPA value string (0.0–4.0).
     *
     * @return the GPA string corresponding to the total grade
     */
    public String convertToGPA() {
        double total = getTotal();
        if (total >= 95) return "4.0";
        if (total >= 90) return "3.67";
        if (total >= 85) return "3.33";
        if (total >= 80) return "3.0";
        if (total >= 75) return "2.67";
        if (total >= 70) return "2.33";
        if (total >= 65) return "2.0";
        if (total >= 60) return "1.67";
        if (total >= 55) return "1.33";
        if (total >= 50) return "1.00";
        return "0.0";
    }

    // ==================== Object overrides ====================

    @Override
    public String toString() {
        return "Mark{" +
            "courseName='" + courseName + '\'' +
            ", studentId='" + studentId + '\'' +
            ", total=" + getTotal() +
            ", passed=" + isPassed() +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;
        Mark mark = (Mark) o;
        return Objects.equals(courseName, mark.courseName)
            && Objects.equals(studentId, mark.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, studentId);
    }
}

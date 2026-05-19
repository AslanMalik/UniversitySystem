package proj.models.academic;

import java.io.Serializable;
import java.util.Objects;

public class Mark implements Serializable, Comparable<Mark> {

    private static final long serialVersionUID = 1L;

    protected String courseName;
    private String studentId;
    private Double firstAtt;
    private Double secondAtt;
    private Double finalGrade;

    public Mark(String courseName, String studentId,
                Double firstAtt, Double secondAtt, Double finalGrade) {
        this.courseName = courseName;
        this.studentId = studentId;
        this.firstAtt = firstAtt;
        this.secondAtt = secondAtt;
        this.finalGrade = finalGrade;
    }

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }

    public Double getFirstAtt() { return firstAtt; }

    public void setFirstAtt(Double firstAtt) { this.firstAtt = firstAtt; }

    public Double getSecondAtt() { return secondAtt; }

    public void setSecondAtt(Double secondAtt) { this.secondAtt = secondAtt; }

    public Double getFinalGrade() { return finalGrade; }

    public void setFinalGrade(Double finalGrade) { this.finalGrade = finalGrade; }

    public double getTotal() {
        return firstAtt * 0.2 + secondAtt * 0.2 + finalGrade * 0.6;
    }

    public double getTotalGrade() {
        return getTotal();
    }

    public boolean isPassed() {
        return getTotal() >= 50;
    }

    @Override
    public int compareTo(Mark other) {
        return Double.compare(this.getTotal(), other.getTotal());
    }

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

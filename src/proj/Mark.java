package proj;

import java.io.Serializable;

/**
* @generated
*/
public class Mark implements Serializable {
    
    protected String courseName;
    private String studentId;
    private Double totalGrade;
    private Double firstAtt;
    private Double secondAtt;
    private Double finalGrade;

    public Mark(String courseName, String studentId, Double firstAtt, Double secondAtt, Double finalGrade) {
        this.courseName = courseName;
        this.studentId = studentId;
        this.totalGrade = firstAtt + secondAtt + finalGrade;
        this.firstAtt = firstAtt;
        this.secondAtt = secondAtt;
        this.finalGrade = finalGrade;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    public Double getTotalGrade() {
        return this.totalGrade;
    }

    public void setTotalGrade(Double totalGrade) {
        this.totalGrade = totalGrade;
    }

    public Double getFirstAtt() {
        return this.firstAtt;
    }

    public void setFirstAtt(Double firstAtt) {
        this.firstAtt = firstAtt;
    }

    public Double getSecondAtt() {
        return this.secondAtt;
    }

    public void setSecondAtt(Double secondAtt) {
        this.secondAtt = secondAtt;
    }

    public Double getFinalGrade() {
        return this.finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }
    

    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public String convertToLetter() {
        if (totalGrade >= 95 && totalGrade <= 100) 
            return "A"; 
            if (totalGrade >= 90) 
                return "A-"; 
            if (totalGrade >= 85) 
                return "B+"; 
            if (totalGrade >= 80) 
                return "B"; 
            if (totalGrade >= 75) 
                return "B-"; 
            if (totalGrade >= 70) 
                return "C+"; 
            if (totalGrade >= 65) 
                return "C"; 
            if (totalGrade >= 60) 
                return "C-"; 
            if (totalGrade >= 55) 
                return "D+"; 
            if (totalGrade >= 50) 
                return "D"; 
            else  
                return "F";
    }
    /**
    * @generated
    */
    public String convertToGPA() {
        if (totalGrade >= 95 && totalGrade <= 100) 
            return "4.0"; 
            if (totalGrade >= 90) 
                return "3.67"; 
            if (totalGrade >= 85) 
                return "3.33"; 
            if (totalGrade >= 80) 
                return "3.0"; 
            if (totalGrade >= 75) 
                return "2.67"; 
            if (totalGrade >= 70) 
                return "2.33"; 
            if (totalGrade >= 65) 
                return "2.0"; 
            if (totalGrade >= 60) 
                return "1.67"; 
            if (totalGrade >= 55) 
                return "1.33"; 
            if (totalGrade >= 50) 
                return "1.00"; 
            else  
                return "0.0";
        
    }
    
}

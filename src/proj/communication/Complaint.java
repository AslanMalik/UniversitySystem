package proj.communication;

import proj.enums.UrgencyLevel;

import java.io.Serializable;
import java.util.Date;

public class Complaint implements Serializable {
    private String teacherName;
    private String studentName;
    private String reason;
    private UrgencyLevel urgency;
    private Date complaintDate;
    private boolean resolved;
    
    public Complaint(String teacherName, String studentName, String reason, UrgencyLevel urgency) {
        this.teacherName = teacherName;
        this.studentName = studentName;
        this.reason = reason;
        this.urgency = urgency;
        this.complaintDate = new Date();
        this.resolved = false;
    }
    
    public String getTeacherName() { return teacherName; }
    public String getStudentName() { return studentName; }
    public String getReason() { return reason; }
    public UrgencyLevel getUrgency() { return urgency; }
    public Date getComplaintDate() { return complaintDate; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    
    @Override
    public String toString() {
        return "[" + urgency + "] " + teacherName + " complained about " + studentName + ": " + reason;
    }
}
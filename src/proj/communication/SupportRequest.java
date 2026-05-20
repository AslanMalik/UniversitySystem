package proj.communication;

import proj.enums.RequestStatus;

import java.io.Serializable;
import java.util.Date;

public class SupportRequest implements Serializable {
    private String id;
    private String description;
    private RequestStatus status;
    private Date createdDate;
    private String createdBy;
    
    public SupportRequest(String id, String description, String createdBy) {
        this.id = id;
        this.description = description;
        this.createdBy = createdBy;
        this.status = RequestStatus.NEW;
        this.createdDate = new Date();
    }
    
    public String getId() { return id; }
    public String getDescription() { return description; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
    public Date getCreatedDate() { return createdDate; }
    public String getCreatedBy() { return createdBy; }
    
    @Override
    public String toString() {
        return "Request #" + id + " from " + createdBy + ": " + description + " [Status: " + status + "]";
    }
}
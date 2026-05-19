package proj;

import java.util.ArrayList;
import java.util.List;

public class TechSupportSpecialist extends Employee {
    private List<SupportRequest> requests;
    
    public TechSupportSpecialist(String name, String surname, String birthDate,
                                  String phoneNumber, String email, String password) {
        super(name, surname, birthDate, phoneNumber, email, password);
        this.requests = new ArrayList<>();
    }
    
    public void addRequest(SupportRequest request) {
        requests.add(request);
    }
    
    public List<SupportRequest> viewNewRequests() {
        List<SupportRequest> newRequests = new ArrayList<>();
        for (SupportRequest r : requests) {
            if (r.getStatus() == RequestStatus.NEW) {
                newRequests.add(r);
            }
        }
        return newRequests;
    }
    
    public void updateRequestStatus(SupportRequest request, RequestStatus status) {
        request.setStatus(status);
    }
    
    public String getAllRequests() {
        StringBuilder sb = new StringBuilder();
        for (SupportRequest r : requests) {
            sb.append(r).append("\n");
        }
        return sb.toString();
    }
}
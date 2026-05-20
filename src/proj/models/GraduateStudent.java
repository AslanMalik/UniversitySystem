package proj.models;

import proj.communication.News;
import proj.enums.Degree;
import proj.enums.Faculty;
import proj.interfaces.JournalSubscriber;
import proj.interfaces.Researcher;
import proj.research.ResearchPaper;
import proj.research.ResearchProject;
import proj.utils.Database;

import java.util.ArrayList;
import java.util.List;

public class GraduateStudent extends Student implements Researcher, JournalSubscriber {
    private Researcher supervisor;
    private List<ResearchPaper> researchPapers;
    private List<ResearchProject> researchProjects;
    
    public GraduateStudent(String name, String surname, String birthDate,
                           String phoneNumber, String email, String password,
                           String id, Integer yearOfStudy, Faculty faculty,
                           Degree degree, Researcher supervisor) {
        super(name, surname, birthDate, phoneNumber, email, password, 
              id, yearOfStudy, faculty, degree);
        this.supervisor = supervisor;
        this.researchPapers = new ArrayList<>();
        this.researchProjects = new ArrayList<>();
        
        if (supervisor.calculateHIndex() < 3) {
            System.err.println("WARNING: Supervisor " + ((User)supervisor).getName() + 
                               " has low h-index (" + supervisor.calculateHIndex() + ")!");
        }
    }
    
    @Override
    public int calculateHIndex() {
        researchPapers.sort((a, b) -> Integer.compare(b.getCitations(), a.getCitations()));
        int h = 0;
        for (int i = 0; i < researchPapers.size(); i++) {
            if (researchPapers.get(i).getCitations() >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }
    
    @Override
    public void printPapers(java.util.Comparator<ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (ResearchPaper paper : researchPapers) {
            System.out.println(paper);
        }
    }
    
    @Override
    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
        News news = new News("paper_" + System.currentTimeMillis(),
                             "New Research Paper Published", 
                             getName() + " published: " + paper.getTitle());
        Database.getNews().add(news);
    }
    
    @Override
    public void addResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }
    
    @Override
    public List<ResearchPaper> getResearchPapers() { return researchPapers; }
    
    @Override
    public List<ResearchProject> getResearchProjects() { return researchProjects; }
    
    public Researcher getSupervisor() { return supervisor; }
    
    @Override
    public String getAllInfo() {
        return super.getAllInfo() + "\n    Degree: Graduate Student\n    H-Index: " + calculateHIndex();
    }
    
    @Override
    public void update(String journalName, ResearchPaper newPaper) {
        System.out.println("NOTIFICATION: New paper published in " + journalName + 
                           ": " + newPaper.getTitle() + " by " + 
                           String.join(", ", newPaper.getAuthors()));
    }
}
package university.models;

import university.exceptions.NotResearcherException;
import university.interfaces.Researcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<Researcher> members = new ArrayList<>();

    public ResearchProject(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addMember(Researcher researcher) throws NotResearcherException {
        members.add(researcher);
    }

    @Override
    public String toString() {
        return "ResearchProject{name='" + name + "'}";
    }
}

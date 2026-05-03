package university.models;

import university.exceptions.NotResearcherException;
import university.interfaces.Researcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Placeholder — implemented by Participant 3.
 * Represents a university research project.
 */
public class ResearchProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<Researcher> members = new ArrayList<>();

    /** @param name project name */
    public ResearchProject(String name) {
        this.name = name;
    }

    /** @return project name */
    public String getName() { return name; }

    /**
     * Adds a researcher to this project.
     *
     * @param researcher the researcher to add
     * @throws NotResearcherException if the researcher is ineligible
     */
    public void addMember(Researcher researcher) throws NotResearcherException {
        members.add(researcher);
    }

    @Override
    public String toString() {
        return "ResearchProject{name='" + name + "'}";
    }
}

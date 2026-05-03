package university.models;

import java.io.Serializable;

/**
 * Placeholder — implemented by Participant 3.
 * Represents a graduate student's diploma (thesis) project.
 */
public class DiplomaProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    /** @param title diploma project title */
    public DiplomaProject(String title) {
        this.title = title;
    }

    /** @return diploma project title */
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "DiplomaProject{title='" + title + "'}";
    }
}

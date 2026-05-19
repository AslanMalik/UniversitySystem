package university.models;

import java.io.Serializable;

public class DiplomaProject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    public DiplomaProject(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

    @Override
    public String toString() {
        return "DiplomaProject{title='" + title + "'}";
    }
}

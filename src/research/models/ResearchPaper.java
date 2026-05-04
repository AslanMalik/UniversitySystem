package models;

import enums.CitationFormat;

import java.util.*;

public class ResearchPaper {
    private String title;
    private List<String> authors;
    private String journal;
    private int citations;
    private String doi;
    private int pages;
    private Date datePublished;

    public ResearchPaper(String title, List<String> authors) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.datePublished = new Date();
    }

    public String getCitation(CitationFormat format) {
        if (format == CitationFormat.PLAIN_TEXT) {
            return String.join(", ", authors) + ". " + title;
        } else {
            return "@article{" + title + "}";
        }
    }

    public String getTitle() { return title; }
    public int getCitations() { return citations; }
    public void setCitations(int citations) { this.citations = citations; }
    public Date getDatePublished() { return datePublished; }
    public int getPages() { return pages; }

    @Override
    public String toString() {
        return "Paper{" + title + ", citations=" + citations + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResearchPaper)) return false;
        ResearchPaper that = (ResearchPaper) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
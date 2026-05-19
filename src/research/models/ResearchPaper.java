package research.models;

import research.enums.CitationFormat;

import java.io.Serializable;
import java.util.*;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {

    private static final long serialVersionUID = 1L;

    private String title;
    private List<String> authors;
    private String journal;
    private int citations;
    private String doi;
    private int pages;
    private Date datePublished;

    public ResearchPaper(String title, List<String> authors, String journal,
                         String doi, int pages, Date datePublished) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.journal = journal;
        this.doi = doi;
        this.pages = pages;
        this.datePublished = datePublished;
        this.citations = 0;
    }

    public ResearchPaper(String title, List<String> authors) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.datePublished = new Date();
        this.citations = 0;
    }

    public String getTitle() { return title; }

    public List<String> getAuthors() { return authors; }

    public String getJournal() { return journal; }

    public String getDoi() { return doi; }

    public int getPages() { return pages; }

    public Date getDatePublished() { return datePublished; }

    public int getCitations() { return citations; }

    public void setCitations(int citations) { this.citations = citations; }

    public void addCitation() {
        this.citations++;
    }

    public String getCitation(CitationFormat format) {
        String authorsStr = String.join(", ", authors);
        if (format == CitationFormat.PLAIN_TEXT) {
            return authorsStr + ". " + title + ". " + journal + ". "
                + datePublished + ". DOI: " + doi + ". Pages: " + pages + ".";
        } else {
            return "@article{" + doi + ",\n"
                + "  title={" + title + "},\n"
                + "  author={" + authorsStr + "},\n"
                + "  journal={" + journal + "},\n"
                + "  year={" + datePublished + "},\n"
                + "  pages={" + pages + "}\n"
                + "}";
        }
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

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

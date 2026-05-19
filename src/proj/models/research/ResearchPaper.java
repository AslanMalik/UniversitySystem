package proj.models.research;

import proj.enums.CitationFormat;

import java.io.Serializable;
import java.util.Date;

public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {
    private String title;
    private String[] authors;
    private String journal;
    private int pages;
    private int citations;
    private Date publicationDate;
    private String doi;

    public ResearchPaper(String title, String[] authors, String journal,
                         int pages, Date publicationDate, String doi) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.pages = pages;
        this.citations = 0;
        this.publicationDate = publicationDate;
        this.doi = doi;
    }

    public String getTitle() { return title; }
    public String[] getAuthors() { return authors; }
    public String getJournal() { return journal; }
    public int getPages() { return pages; }
    public int getCitations() { return citations; }
    public Date getPublicationDate() { return publicationDate; }
    public String getDoi() { return doi; }

    public void addCitation() { this.citations++; }

    public String getCitation(CitationFormat format) {
        String authorsStr = String.join(", ", authors);
        if (format == CitationFormat.PLAIN_TEXT) {
            return authorsStr + ". \"" + title + "\" " + journal +
                   " (" + publicationDate.toString() + "): " + pages + " p.";
        } else {
            return "@article{" + doi + ",\n" +
                   "  author = {" + authorsStr + "},\n" +
                   "  title = {" + title + "},\n" +
                   "  journal = {" + journal + "},\n" +
                   "  year = {" + publicationDate + "},\n" +
                   "  pages = {" + pages + "}\n" +
                   "}";
        }
    }

    @Override
    public int compareTo(ResearchPaper o) {
        return Integer.compare(o.citations, this.citations);
    }

    @Override
    public String toString() {
        return title + " (" + citations + " citations)";
    }
}

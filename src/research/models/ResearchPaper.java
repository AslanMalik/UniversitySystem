package models;

import enums.CitationFormat;

import java.io.Serializable;
import java.util.*;

/**
 * Represents an academic research paper with metadata and citation tracking.
 * Implements {@link Comparable} to support sorting by citation count descending.
 */
public class ResearchPaper implements Serializable, Comparable<ResearchPaper> {

    private static final long serialVersionUID = 1L;

    private String title;
    private List<String> authors;
    private String journal;
    private int citations;
    private String doi;
    private int pages;
    private Date datePublished;

    /**
     * Constructs a ResearchPaper with all required fields. Citations start at zero.
     *
     * @param title         the paper title
     * @param authors       list of author names
     * @param journal       the journal in which the paper was published
     * @param doi           the digital object identifier
     * @param pages         the number of pages
     * @param datePublished the publication date
     */
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

    /**
     * Convenience constructor that sets the publication date to now.
     *
     * @param title   the paper title
     * @param authors list of author names
     */
    public ResearchPaper(String title, List<String> authors) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.datePublished = new Date();
        this.citations = 0;
    }

    // ==================== Getters ====================

    /** @return the paper title */
    public String getTitle() { return title; }

    /** @return the list of author names */
    public List<String> getAuthors() { return authors; }

    /** @return the journal name */
    public String getJournal() { return journal; }

    /** @return the DOI string */
    public String getDoi() { return doi; }

    /** @return the number of pages */
    public int getPages() { return pages; }

    /** @return the publication date */
    public Date getDatePublished() { return datePublished; }

    /** @return the current citation count */
    public int getCitations() { return citations; }

    /**
     * Sets the citation count directly (use {@link #addCitation()} for increments).
     *
     * @param citations the citation count to set
     */
    public void setCitations(int citations) { this.citations = citations; }

    // ==================== Core Methods ====================

    /**
     * Increments the citation count by one.
     */
    public void addCitation() {
        this.citations++;
    }

    /**
     * Returns a formatted citation string in the requested format.
     *
     * <ul>
     *   <li>{@link CitationFormat#PLAIN_TEXT}: {@code Authors. Title. Journal. Date. DOI: doi. Pages: pages.}</li>
     *   <li>{@link CitationFormat#BIBTEX}: a standard BibTeX {@code @article} entry.</li>
     * </ul>
     *
     * @param format the desired citation format
     * @return the formatted citation string
     */
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

    /**
     * Compares papers by citation count descending (most-cited first).
     *
     * @param other the other paper to compare against
     * @return positive if this has fewer citations, negative if more, zero if equal
     */
    @Override
    public int compareTo(ResearchPaper other) {
        return Integer.compare(other.citations, this.citations);
    }

    // ==================== Object overrides ====================

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

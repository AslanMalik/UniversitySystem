package models;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a news item in the university system.
 * Pinned news items sort before unpinned ones via the natural ordering.
 */
public class News implements Serializable, Comparable<News> {

    private static final long serialVersionUID = 1L;

    private String title;
    private String topic;
    private boolean isPinned;
    private List<String> comments = new ArrayList<>();

    /**
     * Constructs a News item with the given title and topic.
     * By default the item is unpinned.
     *
     * @param title the headline of the news item
     * @param topic the category or subject area
     */
    public News(String title, String topic) {
        this.title = title;
        this.topic = topic;
    }

    // ==================== Getters / Setters ====================

    /** @return the news headline */
    public String getTitle() { return title; }

    /** @param title the headline to set */
    public void setTitle(String title) { this.title = title; }

    /** @return the topic or category */
    public String getTopic() { return topic; }

    /** @param topic the topic to set */
    public void setTopic(String topic) { this.topic = topic; }

    /** @return {@code true} if the news item is pinned */
    public boolean isPinned() { return isPinned; }

    /** @return the list of comments on this news item */
    public List<String> getComments() { return comments; }

    // ==================== Core Methods ====================

    /**
     * Pins this news item so it appears at the top of sorted lists.
     */
    public void pin() {
        isPinned = true;
    }

    /**
     * Unpins this news item so it returns to normal sort order.
     */
    public void unpin() {
        isPinned = false;
    }

    /**
     * Appends a comment to this news item.
     *
     * @param comment the comment text to add
     */
    public void addComment(String comment) {
        comments.add(comment);
    }

    /**
     * Compares news items so that pinned items come first.
     * Two pinned or two unpinned items are treated as equal in ordering.
     *
     * @param other the other news item to compare against
     * @return negative if this is pinned and other is not, positive if the reverse, zero if equal
     */
    @Override
    public int compareTo(News other) {
        return Boolean.compare(other.isPinned, this.isPinned);
    }

    /**
     * Creates a pinned research announcement. Topic is set to "Research".
     *
     * @return a new pinned News item for research publications
     */
    public static News createResearchNews() {
        News news = new News("New Research Published", "Research");
        news.pin();
        return news;
    }

    /**
     * Creates a pinned research announcement referencing a specific paper.
     * Topic is set to "Research".
     *
     * @param paper the paper to feature in the announcement
     * @return a new pinned News item referencing the given paper
     */
    public static News createResearchNews(ResearchPaper paper) {
        News news = new News("New Research Published: " + paper.getTitle(), "Research");
        news.addComment("Paper: " + paper.getTitle());
        news.pin();
        return news;
    }

    /**
     * Creates a pinned top-cited papers announcement. Topic is set to "Research".
     *
     * @return a new pinned News item highlighting top-cited papers
     */
    public static News createTopCitedNews() {
        News news = new News("Top Cited Papers", "Research");
        news.pin();
        return news;
    }

    // ==================== Object overrides ====================

    @Override
    public String toString() {
        return "News{'" + title + "', topic='" + topic
            + "', pinned=" + isPinned + ", comments=" + comments.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(title, news.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

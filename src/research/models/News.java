package research.models;

import java.io.Serializable;
import java.util.*;

public class News implements Serializable, Comparable<News> {

    private static final long serialVersionUID = 1L;

    private String title;
    private String topic;
    private boolean isPinned;
    private List<String> comments = new ArrayList<>();

    public News(String title, String topic) {
        this.title = title;
        this.topic = topic;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getTopic() { return topic; }

    public void setTopic(String topic) { this.topic = topic; }

    public boolean isPinned() { return isPinned; }

    public List<String> getComments() { return comments; }

    public void pin() {
        isPinned = true;
    }

    public void unpin() {
        isPinned = false;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public int compareTo(News other) {
        return Boolean.compare(other.isPinned, this.isPinned);
    }

    public static News createResearchNews() {
        News news = new News("New Research Published", "Research");
        news.pin();
        return news;
    }

    public static News createResearchNews(ResearchPaper paper) {
        News news = new News("New Research Published: " + paper.getTitle(), "Research");
        news.addComment("Paper: " + paper.getTitle());
        news.pin();
        return news;
    }

    public static News createTopCitedNews() {
        News news = new News("Top Cited Papers", "Research");
        news.pin();
        return news;
    }

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

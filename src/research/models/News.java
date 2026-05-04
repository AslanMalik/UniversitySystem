package models;

import java.util.*;

public class News {
    private String title;
    private String topic;
    private boolean isPinned;
    private List<String> comments = new ArrayList<>();

    public News(String title, String topic) {
        this.title = title;
        this.topic = topic;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void pin() {
        isPinned = true;
    }

    public static News createResearchNews(ResearchPaper paper) {
        News news = new News("New Research Published", "Research");
        news.addComment("Paper: " + paper.getTitle());
        news.pin();
        return news;
    }

    @Override
    public String toString() {
        return "News{" + title + "}";
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
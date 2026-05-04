package models;

import interfaces.Subscriber;

import java.io.Serializable;
import java.util.*;

/**
 * Represents an academic journal that subscribers can follow.
 * Implements the Observer pattern: adding a paper automatically notifies all subscribers.
 */
public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    /** Subscribers registered to receive notifications; no duplicates are allowed. */
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<ResearchPaper> papers = new ArrayList<>();

    /**
     * Constructs a Journal with the given name.
     *
     * @param name the name of the journal
     */
    public Journal(String name) {
        this.name = name;
    }

    // ==================== Getters ====================

    /** @return the journal name */
    public String getName() { return name; }

    /** @return the list of subscribers */
    public List<Subscriber> getSubscribers() { return subscribers; }

    /** @return the list of published papers */
    public List<ResearchPaper> getPapers() { return papers; }

    // ==================== Core Methods ====================

    /**
     * Subscribes a user to this journal. Duplicate subscriptions are silently ignored.
     *
     * @param subscriber the subscriber to add
     */
    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    /**
     * Unsubscribes a user from this journal.
     *
     * @param subscriber the subscriber to remove
     */
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Adds a paper to this journal and automatically notifies all subscribers.
     *
     * @param paper the research paper to add
     */
    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
        notifySubscribers();
    }

    /**
     * Backward-compatible alias for {@link #addPaper(ResearchPaper)}.
     *
     * @param paper the paper to publish
     */
    public void publishPaper(ResearchPaper paper) {
        addPaper(paper);
    }

    /**
     * Notifies every subscriber that a new paper has been added to this journal
     * by printing a notification message to standard output.
     */
    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            System.out.println("Notification: New paper published in journal '" + name + "'.");
            s.update(papers.get(papers.size() - 1));
        }
    }

    // ==================== Object overrides ====================

    @Override
    public String toString() {
        return "Journal{" + name + ", papers=" + papers.size()
            + ", subscribers=" + subscribers.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journal)) return false;
        Journal journal = (Journal) o;
        return Objects.equals(name, journal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

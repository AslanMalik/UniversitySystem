package models;

import interfaces.Subscriber;

import java.io.Serializable;
import java.util.*;

public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<ResearchPaper> papers = new ArrayList<>();

    public Journal(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public List<Subscriber> getSubscribers() { return subscribers; }

    public List<ResearchPaper> getPapers() { return papers; }

    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
        notifySubscribers();
    }

    public void publishPaper(ResearchPaper paper) {
        addPaper(paper);
    }

    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            System.out.println("Notification: New paper published in journal '" + name + "'.");
            s.update(papers.get(papers.size() - 1));
        }
    }

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

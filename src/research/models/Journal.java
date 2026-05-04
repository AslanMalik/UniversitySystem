package models;

import interfaces.Subscriber;

import java.util.*;

public class Journal {
    private String name;
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<ResearchPaper> papers = new ArrayList<>();

    public Journal(String name) {
        this.name = name;
    }

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    public void publishPaper(ResearchPaper paper) {
        papers.add(paper);
        notifySubscribers(paper);
    }

    private void notifySubscribers(ResearchPaper paper) {
        for (Subscriber s : subscribers) {
            s.update(paper);
        }
    }

    @Override
    public String toString() {
        return "Journal{" + name + "}";
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
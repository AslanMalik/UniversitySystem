package models;

import enums.CitationFormat;
import exceptions.LowHIndexException;
import exceptions.NotResearcherException;
import interfaces.Researcher;
import interfaces.Subscriber;

import java.util.*;

public class TestResearch {

    static int passed = 0;
    static int failed = 0;

    static class MockResearcher implements Researcher, Subscriber {
        private final String name;
        private final List<ResearchPaper> papers = new ArrayList<>();
        ResearchPaper lastUpdated = null;

        MockResearcher(String name) { this.name = name; }

        @Override public List<ResearchPaper> getResearchPapers() { return papers; }

        @Override public void publishPaper(ResearchPaper paper) { papers.add(paper); }

        @Override public void joinProject(ResearchProject project) throws NotResearcherException {
            project.addParticipant(this);
        }

        @Override public void update(ResearchPaper paper) { lastUpdated = paper; }

        @Override public String toString() { return "Mock(" + name + ")"; }
    }

    public static void main(String[] args) {
        sep("PARTICIPANT 3  —  research/models/*");
        testResearchPaper();
        testResearchProject();
        testDiplomaProject();
        testJournal();
        testMessage();
        testNews();
        System.out.printf("%n=== research/models/* RESULT: %d passed, %d failed ===%n",
                passed, failed);
    }

    static void testResearchPaper() {
        System.out.println("\n--- ResearchPaper ---");

        List<String> authors = Arrays.asList("Alice", "Bob");
        ResearchPaper p = new ResearchPaper("AI Paper", authors, "Nature", "10.1/ai", 12, new Date());

        check("getCitations() starts at 0", p.getCitations() == 0);

        p.addCitation();
        p.addCitation();
        check("addCitation() increments twice to 2", p.getCitations() == 2);

        String plain = p.getCitation(CitationFormat.PLAIN_TEXT);
        check("getCitation(PLAIN_TEXT) contains title",   plain.contains("AI Paper"));
        check("getCitation(PLAIN_TEXT) contains authors", plain.contains("Alice"));
        check("getCitation(PLAIN_TEXT) contains DOI",     plain.contains("10.1/ai"));
        check("getCitation(PLAIN_TEXT) contains journal", plain.contains("Nature"));

        String bibtex = p.getCitation(CitationFormat.BIBTEX);
        check("getCitation(BIBTEX) starts with @article",  bibtex.startsWith("@article{"));
        check("getCitation(BIBTEX) contains title field",  bibtex.contains("title={AI Paper}"));
        check("getCitation(BIBTEX) contains author field", bibtex.contains("author={Alice, Bob}"));
        check("getCitation(BIBTEX) contains journal field",bibtex.contains("journal={Nature}"));

        ResearchPaper p2 = new ResearchPaper("Old Paper", Arrays.asList("Carol"));
        p2.addCitation();
        check("compareTo() p(2) < p2(1) in descending order — p comes first",
                p.compareTo(p2) < 0);

        List<ResearchPaper> list = new ArrayList<>(Arrays.asList(p2, p));
        Collections.sort(list);
        check("sort descending — p(2 citations) comes first", list.get(0) == p);

        ResearchPaper pSame = new ResearchPaper("AI Paper", Arrays.asList("X"));
        check("equals() by title",     p.equals(pSame));
        check("hashCode() consistent", p.hashCode() == pSame.hashCode());
        check("toString() not null",   p.toString() != null);
    }

    static void testResearchProject() {
        System.out.println("\n--- ResearchProject ---");

        ResearchProject proj = new ResearchProject("Machine Learning");
        check("getParticipants() starts empty", proj.getParticipants().isEmpty());
        check("getPapers() starts empty",       proj.getPapers().isEmpty());

        MockResearcher r1 = new MockResearcher("Alice");
        try {
            proj.addParticipant(r1);
            check("addParticipant(Researcher) succeeds", true);
        } catch (NotResearcherException e) {
            check("addParticipant(Researcher) succeeds", false);
        }
        check("participant added", proj.getParticipants().size() == 1);

        boolean threw = false;
        try {
            proj.addParticipant("not a researcher");
        } catch (NotResearcherException e) {
            threw = true;
        }
        check("addParticipant(non-Researcher) throws NotResearcherException", threw);

        proj.removeParticipant(r1);
        check("removeParticipant() removes the researcher", proj.getParticipants().isEmpty());

        ResearchPaper paper = new ResearchPaper("Deep Learning", Arrays.asList("Alice"));
        proj.publishPaper(paper);
        check("publishPaper() adds to papers list", proj.getPapers().contains(paper));

        check("toString() not null", proj.toString() != null);

        ResearchProject proj2 = new ResearchProject("Machine Learning");
        check("equals() by topic",     proj.equals(proj2));
        check("hashCode() consistent", proj.hashCode() == proj2.hashCode());
    }

    static void testDiplomaProject() {
        System.out.println("\n--- DiplomaProject ---");

        MockResearcher student    = new MockResearcher("Student");
        MockResearcher supervisor = new MockResearcher("Supervisor");

        DiplomaProject dp = new DiplomaProject("NLP Thesis", student, supervisor);
        check("getTitle() correct",           "NLP Thesis".equals(dp.getTitle()));
        check("getStudent() is set",          dp.getStudent() == student);
        check("getSupervisor() is set",       dp.getSupervisor() == supervisor);
        check("isComplete() false with no papers", !dp.isComplete());

        ResearchPaper paper = new ResearchPaper("Attention", Arrays.asList("Student"));
        dp.addPaper(paper);
        check("isComplete() true after addPaper()", dp.isComplete());
        check("getPapers() contains added paper",   dp.getPapers().contains(paper));

        check("toString() not null", dp.toString() != null);

        DiplomaProject dp2 = new DiplomaProject("NLP Thesis");
        check("equals() by title",     dp.equals(dp2));
        check("hashCode() consistent", dp.hashCode() == dp2.hashCode());
    }

    static void testJournal() {
        System.out.println("\n--- Journal ---");

        Journal journal = new Journal("Science");
        check("getSubscribers() starts empty", journal.getSubscribers().isEmpty());
        check("getPapers() starts empty",      journal.getPapers().isEmpty());

        MockResearcher sub1 = new MockResearcher("Sub1");
        MockResearcher sub2 = new MockResearcher("Sub2");

        journal.subscribe(sub1);
        journal.subscribe(sub2);
        journal.subscribe(sub1);
        check("subscribe() supports 2 subscribers, ignores duplicate",
                journal.getSubscribers().size() == 2);

        journal.unsubscribe(sub2);
        check("unsubscribe() removes subscriber", journal.getSubscribers().size() == 1);

        ResearchPaper paper = new ResearchPaper("Quantum", Arrays.asList("Q"));
        journal.addPaper(paper);
        check("addPaper() adds paper to list",                  journal.getPapers().contains(paper));
        check("addPaper() notifies subscriber via update()",    sub1.lastUpdated == paper);

        ResearchPaper paper2 = new ResearchPaper("Quantum 2", Arrays.asList("Q2"));
        sub1.lastUpdated = null;
        journal.publishPaper(paper2);
        check("publishPaper() alias also adds paper",           journal.getPapers().contains(paper2));
        check("publishPaper() alias also notifies subscriber",  sub1.lastUpdated == paper2);

        check("toString() not null", journal.toString() != null);

        Journal j2 = new Journal("Science");
        check("equals() by name",      journal.equals(j2));
        check("hashCode() consistent", journal.hashCode() == j2.hashCode());
    }

    static void testMessage() {
        System.out.println("\n--- Message ---");

        Message msg = new Message("Alice", "Bob", "Hello!");
        check("getSender() correct",   "Alice".equals(msg.getSender()));
        check("getReceiver() correct", "Bob".equals(msg.getReceiver()));
        check("getContent() correct",  "Hello!".equals(msg.getContent()));
        check("getTimestamp() not null", msg.getTimestamp() != null);
        check("isRead() starts false",   !msg.isRead());

        msg.markAsRead();
        check("isRead() true after markAsRead()", msg.isRead());

        try { msg.send(); check("send() runs without exception", true); }
        catch (Exception e) { check("send() runs without exception", false); }

        check("toString() not null", msg.toString() != null);

        Message msg2 = new Message("Alice", "Bob", "Hello!");
        check("equals() by content+sender+receiver", msg.equals(msg2));
        check("hashCode() consistent",               msg.hashCode() == msg2.hashCode());
    }

    static void testNews() {
        System.out.println("\n--- News ---");

        News n1 = new News("Breaking", "Tech");
        check("getTitle() correct",  "Breaking".equals(n1.getTitle()));
        check("getTopic() correct",  "Tech".equals(n1.getTopic()));
        check("isPinned() starts false", !n1.isPinned());

        n1.pin();
        check("isPinned() true after pin()", n1.isPinned());

        n1.unpin();
        check("isPinned() false after unpin()", !n1.isPinned());

        n1.addComment("Great news!");
        check("addComment() adds to comments list", n1.getComments().size() == 1);

        News pinned   = new News("Pinned",   "X");
        News unpinned = new News("Unpinned", "Y");
        pinned.pin();
        check("compareTo() pinned < unpinned (pinned sorts first)",
                pinned.compareTo(unpinned) < 0);

        List<News> newsList = new ArrayList<>(Arrays.asList(unpinned, pinned));
        Collections.sort(newsList);
        check("sort — pinned comes first", newsList.get(0) == pinned);

        News rn = News.createResearchNews();
        check("createResearchNews() is pinned",           rn.isPinned());
        check("createResearchNews() topic is 'Research'", "Research".equals(rn.getTopic()));

        ResearchPaper paper = new ResearchPaper("The Paper", Arrays.asList("X"));
        News rn2 = News.createResearchNews(paper);
        check("createResearchNews(paper) is pinned",               rn2.isPinned());
        check("createResearchNews(paper) title contains paper title",
                rn2.getTitle().contains("The Paper"));

        News top = News.createTopCitedNews();
        check("createTopCitedNews() is pinned",           top.isPinned());
        check("createTopCitedNews() topic is 'Research'", "Research".equals(top.getTopic()));

        check("toString() not null", n1.toString() != null);

        News n2 = new News("Breaking", "Other");
        check("equals() by title",     n1.equals(n2));
        check("hashCode() consistent", n1.hashCode() == n2.hashCode());
    }

    static void sep(String title) {
        System.out.println("\n====================================================");
        System.out.println("  " + title);
        System.out.println("====================================================");
    }

    static void check(String desc, boolean ok) {
        System.out.printf("  %s  %s%n", ok ? "PASS" : "FAIL", desc);
        if (ok) passed++; else failed++;
    }
}

package models;

import java.util.*;

public class Message {
    private String sender;
    private String receiver;
    private String content;
    private Date timestamp;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date();
    }

    public void send() {
        System.out.println("Message sent: " + content);
    }

    @Override
    public String toString() {
        return "Message{" + content + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message m = (Message) o;
        return Objects.equals(content, m.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
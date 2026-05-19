package models;

import java.io.Serializable;
import java.util.*;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sender;
    private String receiver;
    private String content;
    private Date timestamp;
    private boolean isRead = false;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date();
    }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }

    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Date getTimestamp() { return timestamp; }

    public boolean isRead() { return isRead; }

    public void send() {
        System.out.println("Message sent from " + sender + " to " + receiver + ": " + content);
    }

    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        return "Message{from='" + sender + "', to='" + receiver
            + "', content='" + content + "', read=" + isRead + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message m = (Message) o;
        return Objects.equals(content, m.content)
            && Objects.equals(sender, m.sender)
            && Objects.equals(receiver, m.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, sender, receiver);
    }
}

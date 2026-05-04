package models;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a message sent between two users in the system.
 * The timestamp is set automatically at construction. The read status starts as unread.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sender;
    private String receiver;
    private String content;
    /** Timestamp set automatically when the message is created. */
    private Date timestamp;
    /** Tracks whether the recipient has read the message; defaults to {@code false}. */
    private boolean isRead = false;

    /**
     * Constructs a Message. The timestamp is set to the current time automatically.
     *
     * @param sender   the sender's identifier or name
     * @param receiver the receiver's identifier or name
     * @param content  the body text of the message
     */
    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date();
    }

    // ==================== Getters / Setters ====================

    /** @return the sender's identifier */
    public String getSender() { return sender; }

    /** @param sender the sender to set */
    public void setSender(String sender) { this.sender = sender; }

    /** @return the receiver's identifier */
    public String getReceiver() { return receiver; }

    /** @param receiver the receiver to set */
    public void setReceiver(String receiver) { this.receiver = receiver; }

    /** @return the message body text */
    public String getContent() { return content; }

    /** @param content the new message content */
    public void setContent(String content) { this.content = content; }

    /** @return the timestamp when the message was created */
    public Date getTimestamp() { return timestamp; }

    /** @return {@code true} if the message has been read */
    public boolean isRead() { return isRead; }

    // ==================== Core Methods ====================

    /**
     * Simulates sending the message by printing a confirmation to standard output.
     */
    public void send() {
        System.out.println("Message sent from " + sender + " to " + receiver + ": " + content);
    }

    /**
     * Marks this message as read.
     */
    public void markAsRead() {
        this.isRead = true;
    }

    // ==================== Object overrides ====================

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

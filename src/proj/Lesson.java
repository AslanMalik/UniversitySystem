package proj;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a single scheduled lesson (lecture or practice) for a university course.
 */
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    /** The type of the lesson (LECTURE or PRACTICE). */
    private LessonType type;
    /** The date and time when the lesson takes place. */
    private Date date;
    /** The room or location where the lesson is held. */
    private String room;

    Lesson() {}

    /**
     * Constructs a Lesson with a {@link LessonType} enum value.
     *
     * @param type the lesson type (LECTURE or PRACTICE)
     * @param date the scheduled date and time
     * @param room the room or location identifier
     */
    public Lesson(LessonType type, Date date, String room) {
        this.type = type;
        this.date = date;
        this.room = room;
    }

    /**
     * Constructs a Lesson from a plain-text type string (for backward compatibility).
     * Unrecognized strings default to {@link LessonType#LECTURE}.
     *
     * @param lessonType the lesson type as a string ("LECTURE" or "PRACTICE")
     * @param date       the scheduled date and time
     * @param room       the room or location identifier
     */
    public Lesson(String lessonType, Date date, String room) {
        try {
            this.type = LessonType.valueOf(lessonType.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.type = LessonType.LECTURE;
        }
        this.date = date;
        this.room = room;
    }

    // ==================== Getters / Setters ====================

    /** @return the lesson type */
    public LessonType getType() { return type; }

    /** @param type the lesson type to set */
    public void setType(LessonType type) { this.type = type; }

    /**
     * Backward-compatible getter that returns the type as a String.
     *
     * @return string representation of the lesson type
     */
    public String getLessonType() { return type != null ? type.name() : null; }

    /**
     * Backward-compatible setter that accepts a String lesson type.
     *
     * @param lessonType the lesson type string to parse
     */
    public void setLessonType(String lessonType) {
        try {
            this.type = LessonType.valueOf(lessonType.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.type = LessonType.LECTURE;
        }
    }

    /** @return the date and time of the lesson */
    public Date getDate() { return date; }

    /** @param date the new date and time */
    public void setDate(Date date) { this.date = date; }

    /**
     * Backward-compatible getter for the date field (previously named {@code time}).
     *
     * @return the lesson date
     */
    public Date getTime() { return date; }

    /** @param time the lesson date (backward-compat setter) */
    public void setTime(Date time) { this.date = time; }

    /** @return the room or location of the lesson */
    public String getRoom() { return room; }

    /** @param room the room to set */
    public void setRoom(String room) { this.room = room; }

    // ==================== Core Methods ====================

    /**
     * Returns a human-readable description of this lesson.
     *
     * @return formatted lesson info string
     */
    public String getInfo() {
        return "Lesson{type=" + type + ", date=" + date + ", room='" + room + "'}";
    }

    // ==================== Object overrides ====================

    @Override
    public String toString() {
        return getInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(type, lesson.type)
            && Objects.equals(date, lesson.date)
            && Objects.equals(room, lesson.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, room);
    }
}

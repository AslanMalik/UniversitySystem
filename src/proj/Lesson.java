package proj;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    private LessonType type;
    private Date date;
    private String room;

    Lesson() {}

    public Lesson(LessonType type, Date date, String room) {
        this.type = type;
        this.date = date;
        this.room = room;
    }

    public Lesson(String lessonType, Date date, String room) {
        try {
            this.type = LessonType.valueOf(lessonType.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.type = LessonType.LECTURE;
        }
        this.date = date;
        this.room = room;
    }

    public LessonType getType() { return type; }

    public void setType(LessonType type) { this.type = type; }

    public String getLessonType() { return type != null ? type.name() : null; }

    public void setLessonType(String lessonType) {
        try {
            this.type = LessonType.valueOf(lessonType.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.type = LessonType.LECTURE;
        }
    }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Date getTime() { return date; }

    public void setTime(Date time) { this.date = time; }

    public String getRoom() { return room; }

    public void setRoom(String room) { this.room = room; }

    public String getInfo() {
        return "Lesson{type=" + type + ", date=" + date + ", room='" + room + "'}";
    }

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

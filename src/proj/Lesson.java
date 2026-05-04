package proj;

import java.io.Serializable;
import java.util.Date;

/**
* @generated
*/
public class Lesson implements Serializable {

    private String lessonType;
    private Date time;
    private String room;

    Lesson() {

    }

    public Lesson(String lessonType, Date time, String room) {
        this.lessonType = lessonType;
        this.time = time;
        this.room = room;
    }


    public String getLessonType() {
        return this.lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    //                          Operations                                  
    /**
    * @generated
    */
    @Override
    public String toString() {
        return "{" +
            " lessonType='" + getLessonType() + "'" +
            ", time='" + getTime() + "'" +
            ", room='" + getRoom() + "'" +
            "}";
    }

}

package proj;

import java.io.Serializable;
import java.util.Date;

/**
* @generated
*/
public class Message implements Serializable {
    
    private String messageFrom;
    private String messageTo;
    private String title;
    private String text;
    private Date msgDate;

    public Message(String messageFrom, String messageTo, String title, String text) {
        this.messageFrom = messageFrom;
        this.messageTo = messageTo;
        this.title = title;
        this.text = text;
        this.msgDate = new Date();
    }

    public String getMessageFrom() {
        return this.messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageTo() {
        return this.messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getMsgDate() {
        return this.msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }
    
    

    //                          Operations                                  


    @Override
    public String toString() {
        return "{" +
            " messageFrom='" + getMessageFrom() + "'" +
            ", messageTo='" + getMessageTo() + "'" +
            ", title='" + getTitle() + "'" +
            ", text='" + getText() + "'" +
            ", msgDate='" + getMsgDate() + "'" +
            "}";
    }

}

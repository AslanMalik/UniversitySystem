package proj;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

/**
* @generated
*/
public class News implements Serializable {
    
    private String id;
    private String title;
    private String text;
    private Vector<String> comments;
    private Date postDate;

    public News(String id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.comments = new Vector<String>();
        this.postDate = new Date();
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Vector<String> getComments() {
        return this.comments;
    }

    public void setComments(Vector<String> comments) {
        this.comments = comments;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public String getNewsInfo() {
        String s = "";
        for (News i: Database.news){
            s += i;
        }
        return s;
    }
    
}

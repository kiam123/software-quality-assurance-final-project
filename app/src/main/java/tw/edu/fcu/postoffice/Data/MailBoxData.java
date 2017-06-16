package tw.edu.fcu.postoffice.Data;

/**
 * Created by kiam on 3/19/2017.
 */

public class MailBoxData {
    String title = "";
    String content = "";

    public MailBoxData(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

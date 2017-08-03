package gbook;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

public class Message {
    private int messageId;
    private String autorName;
    private String messageDesc;
    private Date publicationDate;


    public int getId() {
        return messageId;
    }

    public void setId(int messageId) {
        this.messageId = messageId;
    }

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }

    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publication_date) {
        this.publicationDate = publication_date;
    }

}


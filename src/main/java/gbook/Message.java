package gbook;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "message_table", schema = "public", catalog = "guest_book")
@NamedQuery(name = "Message.getMessages", query = "SELECT m from Message m order by messageId desc")
@Component
public class Message {
    private String autorName;
    private java.sql.Timestamp publicationDate;
    private int messageId;
    private String textMessage;
    @Id
    @Column(name = "message_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId =messageId;
    }

    @Basic
    @Column(name = "autor_name", nullable = true, length = 100)
    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }

    @Basic
    @Column(name = "publication_date", nullable = true)
    public java.sql.Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(java.sql.Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }


    @Basic
    @Column(name = "text_message", nullable = true, length = 10000)
    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message that = (Message) o;
        if (messageId != that.messageId) return false;
        if (autorName != null ? !autorName.equals(that.autorName) : that.autorName != null) return false;
        if (publicationDate != null ? !publicationDate.equals(that.publicationDate) : that.publicationDate != null)
            return false;
        if (textMessage != null ? !textMessage.equals(that.textMessage) : that.textMessage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autorName != null ? autorName.hashCode() : 0;
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + messageId;
        result = 31 * result + (textMessage != null ? textMessage.hashCode() : 0);
        return result;
    }

}


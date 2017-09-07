package gbook;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;

public class DbConnect {
    public EntityManager em = Persistence.createEntityManagerFactory("BOOK").createEntityManager();

    public List<Message> getMessages() {
        TypedQuery<Message> namedQuery = em.createNamedQuery("Message.getMessages", Message.class);
        return namedQuery.getResultList();
    }
    public Message insertMessage(Message message) {
        em.getTransaction().begin();
        Message mes = em.merge(message);
        em.getTransaction().commit();
        return mes;
    }

    public java.sql.Timestamp getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

}




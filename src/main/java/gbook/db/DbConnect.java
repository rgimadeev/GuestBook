package gbook.db;

import gbook.model.Message;

import java.sql.Connection;
import java.util.List;

public interface DbConnect {
    Connection getConnection();

    List<Message> getMessages();

    void insertMessage(Message message);
}

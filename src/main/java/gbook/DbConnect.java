package gbook;

import java.sql.Connection;
import java.util.List;

interface DbConnect {
    Connection getDbConnection();

    List<Message> getMessages();

    void insertMessage(Message message);
}

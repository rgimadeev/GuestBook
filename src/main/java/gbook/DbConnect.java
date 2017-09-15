package gbook;

import java.sql.Connection;
import java.util.List;

public interface DbConnect {
    public Connection getDbConnection();

    public List<Message> getMessages();

    public void insertMessage(Message message);
}

package gbook;

import java.util.List;

public interface MessageService {
    public SaveResult saveMessage(Message mes);

    public List<Message> selectMessage();
}

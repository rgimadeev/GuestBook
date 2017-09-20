package gbook;

import java.util.List;

public interface MessageService {

    public SaveResult saveMessage(Message message);

    public List<Message> selectMessage();
}

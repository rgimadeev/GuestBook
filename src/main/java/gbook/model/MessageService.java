package gbook.model;

import java.util.List;

public interface MessageService {
     SaveResult saveMessage(Message mes);
     List<Message> selectMessage();
}

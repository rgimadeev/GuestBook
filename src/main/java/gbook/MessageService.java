package gbook;

import java.util.List;

interface MessageService {

    SaveResult saveMessage(Message message);

    List<Message> selectMessage();
}

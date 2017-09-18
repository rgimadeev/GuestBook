package gbook;

import java.util.List;

interface MessageService {
    SaveResult saveMessage(Message mes);

    List<Message> selectMessage();
}

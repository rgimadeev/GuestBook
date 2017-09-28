package gbook.model;

import gbook.model.Message;

import java.util.Map;

public interface MessageValidation {
    Map<String, String> validate(Message message);

    void validateAuthor(Map<String, String> errors, Message message);

    void validateMessageText(Map<String, String> errors, Message message);

}

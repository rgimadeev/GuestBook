package gbook.model;

import gbook.model.Message;

import java.util.Map;

public interface MessageValidation {
    Map<String, String> validate(Message mes);

    void validateAuthor(Map<String, String> error, Message mes);

    void validateMessageText(Map<String, String> error, Message mes);
}

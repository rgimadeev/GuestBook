package gbook;

import java.util.Map;

interface MessageValidation {
    Map<String, String> validate(Message message);

    void validateAuthor(Map<String, String> errors, Message message);

    void validateMessageText(Map<String, String> errors, Message message);

    void validateAuthorAndMessageText(Map<String, String> errors, Message message);
}

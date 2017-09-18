package gbook;

import java.util.Map;

interface MessageValidation {
    Map<String, String> validate(Message mes);

    void validateAuthor(Map<String, String> error, Message mes);

    void validateMessageDesc(Map<String, String> error, Message mes);
}

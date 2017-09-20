package gbook;

import java.util.Map;

public interface MessageValidation {
    public Map<String, String> validate(Message message);

    public void validateAuthor(Map<String, String> errors, Message message);

    public void validateMessageText(Map<String, String> errors, Message message);

    public void validateAuthorAndMessageText(Map<String, String> errors, Message message);
}

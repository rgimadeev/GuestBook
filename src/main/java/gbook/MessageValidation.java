package gbook;

import java.util.Map;

public interface MessageValidation {
    public Map<String, String> validate(Message mes);
    public void validateAuthor(Map<String, String> error, Message mes);
    public void validateMessageDesc(Map<String, String> error, Message mes);
}

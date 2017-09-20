package gbook;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessageValidationImpl implements MessageValidation {
    String author_text;
    String message_text;
    String max_kol_message_text;
    static final Logger userLogger = LogManager.getLogger(MessageValidationImpl.class);

    public MessageValidationImpl() {
        File file = new File(this.getClass().getClassLoader().getResource("errors.properties").getFile());

        try (InputStream stream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        ) {
            Properties property = new Properties();
            property.load(reader);
            author_text = property.getProperty("author_text_error");
            message_text = property.getProperty("message_text_error");
            max_kol_message_text = property.getProperty("max_kol_message_text_error");

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            userLogger.error("error in class MessageValidationImpl(FileNotFoundException,UnsupportedEncodingException) :  " + e.getMessage());
        } catch (IOException e) {
            userLogger.error("error in class MessageValidationImpl(IOException) : " + e.getMessage());
        }
    }

    public Map<String, String> validate(Message message) {

        Map<String, String> errors = new HashMap<String, String>();
        validateAuthor(errors, message);
        validateMessageText(errors, message);
        validateAuthorAndMessageText(errors, message);
        return errors;

    }

    public void validateAuthor(Map<String, String> errors, Message message) {
        String authorName = message.getAuthorName();
        if (authorName.equals("")) {
            errors.put("author_text_error", author_text);
            errors.put("message_text_error", "");
            errors.put("max_kol_message_text_error", "");
        }
    }

    public void validateMessageText(Map<String, String> errors, Message message) {
        String messageText = message.getMessageText();
        if (messageText.equals("")) {
            errors.put("author_text_error", "");
            errors.put("message_text_error", message_text);

        }
        if (messageText != null && messageText.length() > 4000) {
            errors.put("author_text_error", "");
            errors.put("message_text_error", "");
            errors.put("max_kol_message_text_error", max_kol_message_text);
        }

    }

    public void validateAuthorAndMessageText(Map<String, String> errors, Message message) {
        String messageText = message.getMessageText();
        String authorName = message.getAuthorName();
        if (authorName.equals("") && messageText.equals("")) {
            errors.put("author_text_error", author_text);
            errors.put("message_text_error", message_text);

        }

    }
}

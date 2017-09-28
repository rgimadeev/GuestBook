package gbook.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessageValidationImpl implements MessageValidation {
    String authorMessage;
    String textMessage;
    String maxKolMessageText;
    static final Logger userLogger = LogManager.getLogger(MessageValidationImpl.class);

    public MessageValidationImpl() {
        File file = new File(this.getClass().getClassLoader().getResource("errors.properties").getFile());
        try (InputStream stream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        ) {
            Properties errorMessages = new Properties();
            errorMessages.load(reader);
            authorMessage = errorMessages.getProperty("author_text_error");
            textMessage = errorMessages.getProperty("message_text_error");
            maxKolMessageText = errorMessages.getProperty("max_kol_message_text_error");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            userLogger.error("error in class MessageValidationImpl (FileNotFoundException,UnsupportedEncodingException) : " + e.getMessage());
        } catch (IOException e) {
            userLogger.error("error in class MessageValidationImpl (IOException) : " + e.getMessage());
        }
    }

    public Map<String, String> validate(Message mes) {
        Map<String, String> errors = new HashMap<String, String>();
        validateAuthor(errors, mes);
        validateMessageText(errors, mes);
        return errors;
    }

    public void validateAuthor(Map<String, String> error, Message mes) {
        String authorName = mes.getAuthorName();
        if (authorName.equals("")) {
            error.put("author_text_error", authorMessage);
        }

    }

    public void validateMessageText(Map<String, String> error, Message mes) {
        String messageText = mes.getMessageText();

        if (messageText.equals("")) {
            error.put("message_text_error", textMessage);
        }
        if (messageText != null && messageText.length() > 4000) {
            error.put("max_kol_message_text_error", maxKolMessageText);

        }
    }

}


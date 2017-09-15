package gbook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessageValidationImpl implements MessageValidation {
    String authorMessage;
    String textMessage;
    String maxLengtMessageText;
    static final Logger userLogger = LogManager.getLogger(MessageValidationImpl.class);

    public MessageValidationImpl() {
        File file = new File(this.getClass().getClassLoader().getResource("errors.properties").getFile());
        try (InputStream stream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        ) {
            Properties errorMessages = new Properties();
            errorMessages.load(reader);
            authorMessage = errorMessages.getProperty("author_error");
            textMessage = errorMessages.getProperty("messagetext_error");
            maxLengtMessageText = errorMessages.getProperty("max_kol_messagetext_error");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            userLogger.error("error message: " + e.getMessage());
        } catch (IOException e) {
            userLogger.error("error IOException message: " + e.getMessage());
        }
    }

    public Map<String, String> validate(Message mes) {
        Map<String, String> errors = new HashMap<String, String>();
        validateAuthor(errors, mes);
        validateMessageDesc(errors, mes);
        return errors;
    }

    public void validateAuthor(Map<String, String> error, Message mes) {
        String autorName = mes.getAuthorName();
        if (autorName.equals("")) {
            error.put("show_author_error", authorMessage);
        }

    }

    public void validateMessageDesc(Map<String, String> error, Message mes) {
        String messageDesc = mes.getMessageDesc();

        if (messageDesc.equals("")) {
            error.put("show_messagetext_error", textMessage);
        }
        if (messageDesc != null && messageDesc.length() > 4000) {
            error.put("show_max_kol_messagetext_error", maxLengtMessageText);

        }
    }

}


package gbook.model;

import gbook.model.MessageValidation;
import gbook.db.DbConnect;
import gbook.db.DbConnectImpl;

import java.util.List;
import java.util.Map;


public class MessageServiceImpl implements MessageService {
    private MessageValidation validator = new MessageValidationImpl();
    private DbConnect dbConnect = new DbConnectImpl();

    public SaveResult saveMessage(Message mes) {
        Map<String, String> errors = validator.validate(mes);

        if (errors.isEmpty()) {
            dbConnect.insertMessage(mes);
            return new SaveResult();
        } else {
            SaveResult saveResult = new SaveResult();
            saveResult.setErrors(errors);
            return saveResult;
        }
    }

    public List<Message> selectMessage() {
        return dbConnect.getMessages();

    }

}

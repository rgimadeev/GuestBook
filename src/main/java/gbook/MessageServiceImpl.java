package gbook;


import java.util.List;
import java.util.Map;


public class MessageServiceImpl implements MessageService {
    MessageValidation validator = new MessageValidationImpl();
    DbConnect dbConnect = new DbConnectImpl();
    Map<String, String> errors;

    public SaveResult saveMessage(Message message) {
        errors = validator.validate(message);
        if (errors.isEmpty()) {
            dbConnect.insertMessage(message);
            return new SaveResult();
        } else {
            SaveResult res = new SaveResult();
            res.setErrors(errors);
            return res;
        }
    }

    public List<Message> selectMessage() {
        return dbConnect.getMessages();

    }


}

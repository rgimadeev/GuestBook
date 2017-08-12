package gbook;

import java.util.HashMap;

public class MessageService {
    private MessageValidation validator=new MessageValidation();
    private DbConnect dbConnect=new DbConnect();
    public SaveResult saveMessage(Message mes) {
        HashMap<String, String> errors = validator.validate(mes);
        if (errors == null) {
            dbConnect.insertMessage(mes);
            return new SaveResult();
        } else {
            SaveResult saveResult = new SaveResult();
             saveResult.setErrors(errors);
             return saveResult;
        }
    }
}

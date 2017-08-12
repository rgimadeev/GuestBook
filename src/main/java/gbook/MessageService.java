package gbook;

import java.util.HashMap;

public class MessageService {
    private MessageValidation validator=new MessageValidation();
    private DbConnect dbConnect=new DbConnect();
    public SaveResult SaveMessage(Message mes) {
        HashMap<String,String> errors = validator.Validate(mes);
        if (errors == null) {
            dbConnect.insertMessage(mes);
            return new SaveResult();
        } else {
            SaveResult saveResult = new SaveResult();
            saveResult.setErrors(errors);

        }

        return new SaveResult();
    }
}

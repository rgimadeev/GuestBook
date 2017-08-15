package gbook;



import java.util.HashMap;


public class MessageService {
    MessageValidation validator = new MessageValidation();
    DbConnect dbConnect = new DbConnect();
    HashMap<String, String> errors;
    public SaveResult saveMessage(Message message) {
        errors = validator.validate(message);
        if(errors== null){
            dbConnect.insertMessage(message);
            SaveResult res=new SaveResult();
            res.setHashMap(errors);
            return res;
        }
        else {
            SaveResult res=new SaveResult();
            res.setHashMap(errors);
            return res;
        }
    }



}

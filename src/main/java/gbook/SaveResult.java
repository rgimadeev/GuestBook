package gbook;

import java.util.HashMap;

public class SaveResult {
  private HashMap<String,String> errors=new HashMap<String,String>();

    public HashMap<String, String> getErrors() {
        return errors;
    }
    public void setErrors(HashMap<String, String> errors) {

        this.errors = errors;
    }
}

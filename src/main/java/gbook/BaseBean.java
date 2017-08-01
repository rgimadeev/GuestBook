package gbook;

import java.util.ArrayList;

public class BaseBean {
    ArrayList<Message> mes=new ArrayList<Message>();
    public ArrayList<Message>getMessage() {
        return mes;
    }
    public void setMessage(ArrayList<Message> messages) {
        this.mes = messages;
    }
}
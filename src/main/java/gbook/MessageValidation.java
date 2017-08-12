package gbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;

import static jdk.nashorn.internal.objects.NativeMath.log;

public class MessageValidation {
    String autorMessage=null;
    String textMessage=null;
    String maxLengtText=null;
   public void reader(String autorMess,String textMess,String maxLenghtText){
       InputStream stream = null;
       try {
           stream = new FileInputStream(new File("C:/Users/rgimadeev/IdeaProjects/GuestBook/src/main/resources/text.properties"));
           InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
           Properties property = new Properties();
           property.load(reader);
           autorMessage = property.getProperty("aut_err");
           textMessage=property.getProperty("text_err");
           maxLengtText=property.getProperty("max_text_err");
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       catch (UnsupportedEncodingException e) {
           log("Error", e);
       } catch (IOException e) {
           log("Error", e);
       }
   }
    public HashMap validate (Message mes) {
        reader(autorMessage, textMessage, maxLengtText);
        HashMap<String,String> hashMap=new HashMap<String,String>();
        String autorName = mes.getAutorName();
        String messageDesc = mes.getMessageDesc();
        if (!autorName.equals("") && !messageDesc.equals("")  && messageDesc.length() <= 100) {
            return null;
        } else if (autorName.equals("") && !messageDesc.equals("")) {
           hashMap.put("aut_err", autorMessage);
           return hashMap;

        } else if (!autorName.equals("") && messageDesc.equals("")) {
            hashMap.put("text_err", textMessage);
            return hashMap;
        } else if (!autorName.equals("") && !messageDesc.equals("") && messageDesc.length() > 100) {
            hashMap.put("max_lt", textMessage);
            return hashMap;
        } else {
            hashMap.put("aut_err", autorMessage);
            hashMap.put("text_err", textMessage);
            return hashMap;
        }
    }
}

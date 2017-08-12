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
    private HashMap<String,String> hashMap = new HashMap<String,String>();
   public void Reader(String autorMess,String textMess,String maxLenghtText){
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
    public HashMap Validate (Message mes) {
        Reader(autorMessage, textMessage, maxLengtText);
        String autorName = mes.getAutorName();
        String messageDesc = mes.getMessageDesc();
        if (autorName != "" && messageDesc != "" && messageDesc.length() <= 100) {
            return hashMap=null;
        } else if (autorName == "" && messageDesc != "") {
           hashMap.put("aut_err", autorMessage);
           return hashMap;

        } else if (autorName != "" && messageDesc == "") {
            hashMap.put("text_err", textMessage);
            return hashMap;
        } else if (autorName != "" && messageDesc != "" && messageDesc.length() > 100) {
            hashMap.put("max_lt", textMessage);
            return hashMap;
        } else {
            hashMap.put("aut_err", autorMessage);
            hashMap.put("text_err", textMessage);
            return hashMap;
        }
    }
}

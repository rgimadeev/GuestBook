package gbook;



import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class MessageValidation {
    String s1 = null;
    String s2 = null;
    String s3 = null;
    String s4 = null;
    String s5 = null;

    public void reader(String s1, String s2, String s3, String s4, String s5) {
        try (InputStream stream = new FileInputStream(new File("C:/Users/rgimadeev/IdeaProjects/GuestBook/src/main/resources/text.properties"));
             InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        ) {
            Properties property = new Properties();
            property.load(reader);
            this.s1 = property.getProperty("s1");
            this.s2 = property.getProperty("s2");
            this.s3 = property.getProperty("s3");
            this.s4 = property.getProperty("s4");
            this.s5 = property.getProperty("s5");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public HashMap<String, String> validate(Message message) {
        reader(s1, s2, s3, s4, s5);
       HashMap<String,String> hashMap=new HashMap<String,String>();
        String mesAut = message.getAutorName();
        String mesMesc = message.getMessageDesc();

        if (!mesAut.equals("") && !mesMesc.equals("") && mesMesc.length() <= 4000 && mesAut.length() <= 35) {
            return null;
        } else if (mesAut.equals("") && !mesMesc.equals("")) {
            hashMap.put("s1",s1);
            return hashMap;
        } else if (!mesAut.equals("") && mesMesc.equals("")) {
            hashMap.put("s2",s2);
            return hashMap;
        } else if (!mesAut.equals("") && !mesMesc.equals("") && mesMesc.length()>4000 ) {
            hashMap.put("s3",s3);
            return hashMap;
        } else if (!mesAut.equals("") && !mesMesc.equals("") && mesAut.length()>35) {
            hashMap.put("s4",s4);
            return hashMap;
        } else  {
            hashMap.put("s5",s5);
            return hashMap;
        }
    }
}

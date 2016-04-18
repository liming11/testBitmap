package com.example.testbitmap.parser;

import java.io.InputStream;
import java.io.StringWriter;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.xmlpull.v1.XmlPullParser;  
import org.xmlpull.v1.XmlSerializer;  
  
import android.util.Xml;

import com.example.testbitmap.model.chargeBean;

  
public class PullChargeBeanParser implements chargeBeanParser {

    @Override
    public List<chargeBean> parse(InputStream is) throws Exception {
        List<chargeBean> books = null;
        chargeBean book = null;

          
        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例  
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式  
  
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
            case XmlPullParser.START_DOCUMENT:
                books = new ArrayList<chargeBean>();
                break;
            case XmlPullParser.START_TAG:
                if (parser.getName().equals("charge")) {
                    book = new chargeBean();
                } else if (parser.getName().equals("id")) {
                    eventType = parser.next();
                    book.setId(Integer.parseInt(parser.getText()));
                } else if (parser.getName().equals("name")) {
                    eventType = parser.next();
                    book.setName(parser.getText());
                } else if (parser.getName().equals("price")) {
                    eventType = parser.next();
                    book.setPrice(Float.parseFloat(parser.getText()));
                }  
                break;  
            case XmlPullParser.END_TAG:
                if (parser.getName().equals("charge")) {
                    books.add(book);
                    book = null;
                }
                break;
            }
            eventType = parser.next();
        }
        return books;
    }
      
    @Override
    public String serialize(List<chargeBean> chargebeans) throws Exception {

        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例  
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);   //设置输出方向为writer
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "books");
        for (chargeBean book : chargebeans) {
            serializer.startTag("", "book");
            serializer.attribute("", "id", book.getId() + "");

            serializer.startTag("", "name");
            serializer.text(book.getName());
            serializer.endTag("", "name");

            serializer.startTag("", "price");
            serializer.text(book.getPrice() + "");
            serializer.endTag("", "price");
              
            serializer.endTag("", "book");
        }
        serializer.endTag("", "books");  
        serializer.endDocument();  
          
        return writer.toString();  
    }  
}  

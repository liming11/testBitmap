package com.example.testbitmap.parser;

import java.io.InputStream;  
import java.io.StringWriter;  
import java.util.ArrayList;  
import java.util.List;  
  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.transform.OutputKeys;  
import javax.xml.transform.Result;  
import javax.xml.transform.Source;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;

import com.example.testbitmap.model.chargeBean;  
  
  
public class DomChargeBeanParser implements chargeBeanParser {  
  
    @Override  
    public List<chargeBean> parse(InputStream is) throws Exception {  
        List<chargeBean> books = new ArrayList<chargeBean>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  //ȡ��DocumentBuilderFactoryʵ��  
        DocumentBuilder builder = factory.newDocumentBuilder(); //��factory��ȡDocumentBuilderʵ��
        Document doc = builder.parse(is);   //���������� �õ�Documentʵ��
        Element rootElement = doc.getDocumentElement();
        NodeList items = rootElement.getElementsByTagName("book");

        for (int i = 0; i < items.getLength(); i++) {
        	chargeBean book = new chargeBean();
            Node item = items.item(i);
            NodeList properties = item.getChildNodes();
            for (int j = 0; j < properties.getLength(); j++) {
                Node property = properties.item(j);
                String nodeName = property.getNodeName();
                if (nodeName.equals("id")) {
                    book.setId(Integer.parseInt(property.getFirstChild().getNodeValue()));  
                } else if (nodeName.equals("name")) {
                    book.setName(property.getFirstChild().getNodeValue());
                } else if (nodeName.equals("price")) {
                    book.setPrice(Float.parseFloat(property.getFirstChild().getNodeValue()));  
                }
            }
            books.add(book);
        }  
        return books;  
    }  
  
    @Override  
    public String serialize(List<chargeBean> chargebeans) throws Exception {  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder = factory.newDocumentBuilder();  
        Document doc = builder.newDocument();   //��builder�������ĵ�  
          
        Element rootElement = doc.createElement("charges");  
  
        for (chargeBean book : chargebeans) {  
            Element bookElement = doc.createElement("charge");  
            bookElement.setAttribute("id", book.getId() + "");  
              
            Element nameElement = doc.createElement("name");  
            nameElement.setTextContent(book.getName());  
            bookElement.appendChild(nameElement);  
              
            Element priceElement = doc.createElement("price");  
            priceElement.setTextContent(book.getPrice() + "");  
            bookElement.appendChild(priceElement);  
              
            rootElement.appendChild(bookElement);  
        }  
          
        doc.appendChild(rootElement);  
          
        TransformerFactory transFactory = TransformerFactory.newInstance();//ȡ��TransformerFactoryʵ��  
        Transformer transformer = transFactory.newTransformer();    //��transFactory��ȡTransformerʵ��  
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");            // ����������õı��뷽ʽ  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");                // �Ƿ��Զ���Ӷ���Ŀհ�  
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");   // �Ƿ����XML����  

        StringWriter writer = new StringWriter();

        Source source = new DOMSource(doc); //�����ĵ���Դ��doc
        Result result = new StreamResult(writer);//����Ŀ����Ϊwriter
        transformer.transform(source, result);  //��ʼת�� 

        return writer.toString();
    }  
  
}  
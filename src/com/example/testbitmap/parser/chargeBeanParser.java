package com.example.testbitmap.parser;

import java.io.InputStream;  
import java.util.List;

import com.example.testbitmap.model.chargeBean;  
  
public interface chargeBeanParser {  
    /** 
     * ���������� �õ�chargeBeanParser���󼯺� 
     * @param is 
     * @return 
     * @throws Exception 
     */  
    public List<chargeBean> parse(InputStream is) throws Exception;  
      
    /** 
     * ���л�chargeBeanParser���󼯺� �õ�XML��ʽ���ַ��� 
     * @param chargebeans 
     * @return
     * @throws Exception
     */  
    public String serialize(List<chargeBean> chargebeans) throws Exception;
}  
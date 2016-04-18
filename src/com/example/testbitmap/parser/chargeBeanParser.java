package com.example.testbitmap.parser;

import java.io.InputStream;  
import java.util.List;

import com.example.testbitmap.model.chargeBean;  
  
public interface chargeBeanParser {  
    /** 
     * 解析输入流 得到chargeBeanParser对象集合 
     * @param is 
     * @return 
     * @throws Exception 
     */  
    public List<chargeBean> parse(InputStream is) throws Exception;  
      
    /** 
     * 序列化chargeBeanParser对象集合 得到XML形式的字符串 
     * @param chargebeans 
     * @return
     * @throws Exception
     */  
    public String serialize(List<chargeBean> chargebeans) throws Exception;
}  
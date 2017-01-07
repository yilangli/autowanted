package com.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 



import javax.enterprise.inject.New;

import com.bean.Admin;
import com.bean.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
 

public class JsonUtils {

 
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
         
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
         
        return bean;
         
    }
 
    
    public static String beanToJson(Object bean) {
 
        JSONObject json = JSONObject.fromObject(bean);
         
        return json.toString();
 
    }
      
  
    @SuppressWarnings("unchecked")
    public static String beanListToJson(List beans) {
        
    	if(beans==null||beans.size()==0){
    		return "\"\"";
    	}
    	
        StringBuffer rest = new StringBuffer();
        
         
        rest.append("[");
         
        int size = beans.size();
         
        for (int i = 0; i < size; i++) {
             
            rest.append(beanToJson(beans.get(i))+((i<size-1)?",":""));
 
        }
         
        rest.append("]");
         
        return rest.toString();
 
    }
     
   
  
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {
 
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        T bean;
        int size = jsonArray.size();
        List<T> list = new ArrayList<T>(size);
 
        for (int i = 0; i < size; i++) {
 
            jsonObject = jsonArray.getJSONObject(i);
            bean = (T) JSONObject.toBean(jsonObject, beanClass);
            list.add(bean);
 
        }
         
        return list;
 
    }
    
    public static <T> T MapToBean(Map<String, ?> map, Class<T> beanClass) {
        for (int i = 0; i < map.size(); i++) {
			
		}
    	JSONObject jsonObject=new JSONObject().fromObject(map);
    	 T bean = (T) JSONObject.toBean(jsonObject, beanClass);
    	 
    	 return bean;
        
    }
    

 


 

 

}

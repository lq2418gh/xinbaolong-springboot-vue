package com.bit.sc.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 自己封装的转换
 * @author zhanglx
 *
 */
public class JsonConvertUtil {

	/*
		QuoteFieldNames———-输出key时是否使用双引号,默认为true 
		WriteMapNullValue——–是否输出值为null的字段,默认为false 
		WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
		WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
		WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
		WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null 
	 */
	
	private static final SerializerFeature[] features = {
			SerializerFeature.WriteMapNullValue, 
			SerializerFeature.WriteNullListAsEmpty,
	        SerializerFeature.WriteNullNumberAsZero,
	        SerializerFeature.WriteNullBooleanAsFalse,
	        SerializerFeature.WriteNullStringAsEmpty,
	        SerializerFeature.WriteDateUseDateFormat,
	        SerializerFeature.DisableCircularReferenceDetect
	       };
	
	  private static ValueFilter filter = new ValueFilter() {
	        @Override
	        public Object process(Object obj, String s, Object v) {
	        	Class<?> type = null ;
	        	try {
	        		type = obj.getClass().getDeclaredField(s).getType();
				} catch (Exception e) {
				} 
	        	
	            if (v == null && type == Date.class){
	            	return "";
	            }
	            return v;
	        }
	    };
	    
	public static String toJSONString(Object obj) {
		return JSONObject.toJSONString(obj,filter,features);  
	}
	
	/**
	 * 把object对象转换成map 按照指定的属性
	 * 如果不指定属性 就按照全部的属性转换
	 * @param obj
	 * @param fileds
	 * @return
	 */
   public static Map<String, Object> convertObjectByFiled(Object obj,String... fileds){
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(obj == null){
			return map;
		}
    	
    	if(fileds.length ==0){
    		//得到类对象  
    	    Class<?> clazz = obj.getClass();  
    	    /* 
    	     * 得到类中的所有属性集合 
    	     */  
    	    Field[] fs = clazz.getDeclaredFields(); 
    	    if(fs.length>0){
    	    	for(int i = 0 ; i < fs.length; i++){  
    	        	String filed = fs[i].getName();
    	    		try {  
    	    	           PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);  
    	    	           Method getMethod = pd.getReadMethod();//获得get方法
    	    	           Object o =getMethod.invoke(obj);
    	    	           
    	    	           Class<?> type =  obj.getClass().getDeclaredField(filed).getType();
    	    	           
    	    	           //时间的处理
    		  	           if(o!=null && type == Date.class){
    		  	        	   	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		  	        		Date date = (Date)o;
    		  	        		o = sdf.format(date);
    		  	           }
    		  	           
    	    	           if(o==null && type == String.class){
    	    	        	   o = "";
    	    	           }
    	    	           if(o==null && type == Date.class){
    	    	        	   o = "";
    	    	           }
    	    	           if(o==null && type == Integer.class){
    	    	        	   o = 0;
    	    	           }
    	    	           if(o==null && type == List.class){
    	    	        	   o = new ArrayList<>();
    	    	           }
    	    	          
    	    	           map.put(filed,o);
    	    	       }catch (Exception e) {  
    	    	           e.printStackTrace();  
    	    	           return null;  
    	    	       }  
    	        }
    	    }
    		return map;
    	}else{
    		for (String filed : fileds) {
    			try {  
    				 Class<?> clazz = obj.getClass();  
    				 PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);  
    				 Method getMethod = pd.getReadMethod();//获得get方法
      	           	 Object o =getMethod.invoke(obj);
    		           
    		           Class<?> type =  obj.getClass().getDeclaredField(filed).getType();
    		           //时间的处理
    	  	           if(o!=null && type == Date.class){
    	  	        	   	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  	        		Date date = (Date)o;
    	  	        		o = sdf.format(date);
    	  	           }
    	  	           
        	           if(o==null && type == String.class){
        	        	   o = "";
        	           }
        	           if(o==null && type == Date.class){
        	        	   o = "";
        	           }
        	           if(o==null && type == Integer.class){
        	        	   o = 0;
        	           }
        	           if(o==null && type == List.class){
        	        	   o = new ArrayList<>();
        	           }
        	          
    	  	         map.put(filed,o);
    	  	         
    		       }catch (Exception e) {  
    		           e.printStackTrace();  
    		           return null;  
    		       }  
			}
    	}
    	return map;
     }

/**
 * List对象转换为map对象   按照指定的属性
 * 如果不指定属性 就按照全部的属性转换
 * @return
 */
public static List convertListByFiled(List list,String ...fieds){
List<Object> fanhuiList = new ArrayList<>();
	if(list == null || list.size() ==0){
		return fanhuiList;
	}
	
	if(fieds.length ==0){//全部的转换
 		for (int j = 0; j < list.size(); j++) {
 			Map<String, Object> map = new HashMap<String, Object>();
 			Object obj = list.get(j);
 	 		//得到类对象  
 	 		  Class<?> clazz = obj.getClass();  
 	 		  /* 
 	 		   * 得到类中的所有属性集合 
 	 		   */  
 	 		  Field[] fs = clazz.getDeclaredFields(); 
 	 		  if(fs.length>0){
 	 		  	for(int i = 0 ; i < fs.length; i++){  
 	 		      	String filed = fs[i].getName();
 	 		  		try {  
 	 		  	           PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);  
 	 		  	           Method getMethod = pd.getReadMethod();//获得get方法
 	 		  	           Object o =getMethod.invoke(obj);
 	 		  	           
 	 		  	           Class<?> type =  obj.getClass().getDeclaredField(filed).getType();
 	 		  	           
 	 		  	           //时间的处理
	 		  	           if(o!=null && type == Date.class){
	 		  	        	   	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 		  	        		Date date = (Date)o;
	 		  	        		o = sdf.format(date);
	 		  	           }
	 		  	           
 	 		  	           if(o==null && type == String.class){
 	 		  	        	   o = "";
 	 		  	           }
 	 		  	           if(o==null && type == Date.class){
 	 		  	        	   o = "";
 	 		  	           }
 	 		  	           if(o==null && type == Integer.class){
 	 		  	        	   o = 0;
 	 		  	           }
 	 		  	           if(o==null && type == List.class){
 	 		  	        	   o = new ArrayList<>();
 	 		  	           }
 	 		  	       
 	 		  	           map.put(filed,o);
 	 		  	       }catch (Exception e) {  
 	 		  	           e.printStackTrace();  
 	 		  	           return null;  
 	 		  	       }  
 	 		      }
 	 		  }
 	 		  fanhuiList.add(map);
 			}
 		 return fanhuiList;
	}else{
		for (int j = 0; j < list.size(); j++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object obj = list.get(j);
	 		//得到类对象  
	 		  Class<?> clazz = obj.getClass();  
	 		  /* 
	 		   * 得到类中的所有属性集合 
	 		   */  
	 		  if(fieds.length>0){
	 		  	for(int i = 0 ; i < fieds.length; i++){  
	 		      	String filed = fieds[i];
	 		  		try {  
	 		  	           PropertyDescriptor pd = new PropertyDescriptor(filed,clazz);  
	 		  	           Method getMethod = pd.getReadMethod();//获得get方法
	 		  	           Object o =getMethod.invoke(obj);
	 		  	           
	 		  	           Class<?> type =  obj.getClass().getDeclaredField(filed).getType();
	 		  	           
	 		  	           //时间的处理
	 		  	           if(o!=null && type == Date.class){
	 		  	        	   	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 		  	        		Date date = (Date)o;
	 		  	        		o = sdf.format(date);
	 		  	           }
	 		  	           
	 		  	           if(o==null && type == String.class){
	 		  	        	   o = "";
	 		  	           }
	 		  	           if(o==null && type == Date.class){
	 		  	        	   o = "";
	 		  	           }
	 		  	           if(o==null && type == Integer.class){
	 		  	        	   o = 0;
	 		  	           }
	 		  	           if(o==null && type == List.class){
	 		  	        	   o = new ArrayList<>();
	 		  	           }
	 		  	        
	 		  	           map.put(filed,o);
	 		  	       }catch (Exception e) {  
	 		  	           e.printStackTrace();  
	 		  	           return null;  
	 		  	       }  
	 		      }
	 		  }
	 		  fanhuiList.add(map);
			}
		 		return fanhuiList;
		}
	}
}
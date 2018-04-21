package com.chinadovey.parking.webapps.controller.baseInfo;


import java.util.List;

import com.chinadovey.parking.webapps.pojo.Person;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTools {

    /**
     * 第一种
     * @param key
     * @param jsonString
     * @return
     */
    public static Person0 getPerson(String key, String jsonString) {
    	System.out.println(jsonString);
        Person0 person = new Person0();
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonString);
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String ad = jsonObject.getString("address");
            person.setId(id);
            person.setName(name);
            person.setAddress(ad);
        } catch (Exception e) {
        }
        return person;
    }
    /**
     * 第二种
     * @param key
     * @param jsonString
     * @return
     */
    public static Person0 getPerson2(String jsonString) {
    	System.out.println(jsonString);
        Person0 person = new Person0();
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonString);
            int result = (Integer)jsonObject.get("result");
            String date = jsonObject.getString("data");
            person = (Person0) JSONObject.toBean(JSONObject.fromObject(date),Person0.class);
            
            
            /*JSONObject json = JSONObject.fromObject(date);
            int id = json.getInt("id");
            String name = json.getString("name");
            String ad = json.getString("address");
            person.setId(id);
            person.setName(name);
            person.setAddress(ad);*/
        } catch (Exception e) {
        }
        return person;
    }
    /**
     * json数组
     * @param jsonString
     */
    public static void getPersons(String jsonString) {
    	System.out.println(jsonString);
        try {
        	JSONArray array = JSONArray.fromObject(jsonString);
            List<JSONObject> names = 
            		JSONArray.toList(array,JSONObject.class);
            for (JSONObject json : names) {
            	String name = (String) json.get("name");
				System.err.println(name);
			}
        } catch (Exception e) {
        	
        }
    }
    
    public static void main(String[] args) {
       JSONObject outjson = new JSONObject();
	   JSONObject json = new JSONObject();
	   json.put("id", 1);
	   json.put("name", "123");
	   json.put("address", "adresss");
	   outjson.put("result", 200);
	   outjson.put("data", json.toString());
	   //getPerson2( outjson.toString());
	   
	   
	   String array = "[{\"name\": \"Michael\"},{\"name\": \"Jerry\"}]";
	   getPersons(array);
	   
	}
    
}


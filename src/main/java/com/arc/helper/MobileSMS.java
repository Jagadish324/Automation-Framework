package com.arc.helper;

import io.restassured.RestAssured;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.StringTokenizer;

abstract public class MobileSMS {
//    Sample link
//    https://receive-smss.com/sms/19175175434/
    public static String getWorkingNumber(String baseURL, String [] numbers)
    {
        String i = "";

        for (String number : numbers) {
            RestAssured.baseURI = baseURL;
            String htmData = RestAssured.given().get(number).getBody().asString();
            Document doc = Jsoup.parse(htmData);
            Elements elements1 = doc.getElementsMatchingText("seconds");
            JSONArray json = parseElementsToJson(elements1);
            JSONObject obj = new JSONObject();
            obj.put("Data", json);
            JSONArray searchResults = searchJSON(obj, "seconds ago");
            if(searchResults.length()>0) {
                i=number;
                break;
            }
            else {
//                System.out.println("Nothing available");
            }
        }
        return i;
    }

    public  static HashMap<Integer, Integer> getSms(String baseURL, String number, String searchText , String contains) throws InterruptedException {
        RestAssured.baseURI = baseURL;
        String htmData  = RestAssured.given().get(number).getBody().asString();
        Document doc = Jsoup.parse(htmData);
        Elements elements1 = doc.getElementsMatchingText(searchText);
        JSONArray json = parseElementsToJson(elements1);
        JSONObject obj = new JSONObject();
        obj.put("Data",json);
        JSONArray searchResults = searchJSON(obj, searchText);
        int code = checknum(searchResults.getString(2), 0);
        if((checknum(searchResults.getString(2),1)<=45
                && searchResults.getString(2).contains("seconds ago"))
                ||searchResults.getString(2).contains("just now")) {
            int time = checknum(rev(searchResults.getString(2)),0);
            System.out.println(code+" seconds:"+rev(Integer.toString(time)));
        }
        else if (searchResults.getString(2).contains("minutes ago")) {
            System.out.println("over one-minute\n"+code);
        }
        boolean check = Boolean.parseBoolean(contains);
        HashMap<Integer,Integer> al = new HashMap<>();
        if(check)
        {
            for (int i = 0; i < searchResults.length(); i++) {
                al.put(checknum(searchResults.getString(i),0),i);
            }
            System.out.println(al);
        }
        return al;
    }
    public static JSONArray parseElementsToJson(Elements elements) {
        JSONArray jsonArray = new JSONArray();

        for (Element element : elements) {
            JSONObject json = new JSONObject();
            json.put("tagName", element.tagName());
            json.put("text", element.text());
            json.put("children", parseElementsToJson(element.children()));
            jsonArray.put(json);
        }
        return jsonArray;
    }
    public static JSONArray searchJSON(JSONObject json, String searchText) {
        JSONArray results = new JSONArray();

        for (String key : json.keySet()) {
            Object value = json.get(key);

            if (value instanceof String) {
                String stringValue = (String) value;
                if (stringValue.contains(searchText)) {
                    results.put(stringValue);
                }
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Object arrayValue = jsonArray.get(i);
                    if (arrayValue instanceof JSONObject) {
                        JSONArray subResults = searchJSON((JSONObject) arrayValue, searchText);
                        for (int j = 0; j < subResults.length(); j++) {
                            results.put(subResults.getString(j));
                        }
                    }
                }
            } else if (value instanceof JSONObject) {
                JSONArray subResults = searchJSON((JSONObject) value, searchText);
                for (int i = 0; i < subResults.length(); i++) {
                    results.put(subResults.getString(i));
                }
            }
        }

        return results;
    }
    public static int checknum(String n, int i)
    {
        StringTokenizer st = new StringTokenizer(n," :+");
        int a = 0,n1=0;
        while(st.hasMoreTokens()) {
            try {
                a = Integer.parseInt(st.nextToken());
//                    System.out.println("---------------------------------------------------------------\n"+a);
                if(i==n1){
                    break;
                }
                ++n1;
            } catch (NumberFormatException e) {

            }
        }
        return a;
    }
    public static String rev(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static void main(String[] args) {
        String[] num = {"19175175434"};
        getWorkingNumber("https://receive-smss.com/sms/",num);
        try {
            HashMap value =getSms("https://receive-smss.com/sms/","19175175434","code is","code is");
       System.out.println(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

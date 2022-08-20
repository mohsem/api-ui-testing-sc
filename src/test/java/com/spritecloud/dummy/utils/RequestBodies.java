package com.spritecloud.dummy.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class RequestBodies {

    public JSONObject userRequestBody() {
        JSONObject user = new JSONObject();
        user.put("id", 1000);
        user.put("username", "angaraliCilgin");
        user.put("firstName", "Angarali");
        user.put("lastName", "Muhsin");
        user.put("email", "angaralicilgin06@melih.com");
        user.put("userStatus", 59874962);
        user.put("password", "Ankaragucu06");
        user.put("phone", "+901234567890");
//        String userName = user.get("username").toString();
        return user;
    }

    public JSONObject petRequestBody() {
        JSONObject pet = new JSONObject();
        pet.put("name", "dogo");
        pet.put("photoUrls", new JSONArray(Arrays.asList("url1", "url2")));
        pet.put("id", 1);
        pet.put("category", new JSONObject().put("name", "catagory name").put("id", 1));
        pet.put("tags", new JSONArray(Arrays.asList(
            new JSONObject().put("name", "fluffy").put("id", 1),
            new JSONObject().put("name", "longTail").put("id", 1))));
        pet.put("status", "available");
        return pet;
    }

    public JSONObject storePostRequest() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//        Date date = new Date();
        JSONObject store = new JSONObject();
        store.put("id", 55054076);
        store.put("petId", -72445332);
        store.put("quantity", 76930299);
        store.put("shipDate", "1968-04-19T12:51:27.367Z");
        store.put("status", "placed");
        store.put("complete", true);
        return store;
    }
}

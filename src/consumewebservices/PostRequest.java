/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumewebservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author pc
 */
public class PostRequest {
    public static void main(String[] args) {
	try {
		PostRequest.call_me();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	 public static void call_me() throws Exception {
	    URL url = new URL("https://bookme.pk/REST/API/rest_api.php?eventBooking");
	    Map params = new LinkedHashMap<>();
	    params.put("api_key", "0724d9f9941803425ebac3e6659fd1af");
	    params.put("event_id", "105");
            params.put("date", "2018-09-08");
            params.put("tickets", "1");
            params.put("name", "test");
	    params.put("email", "qaiser@macropaks.com");
	    params.put("phone", "03210000000");
            params.put("cnic", "cnic");
            params.put("city", "test");
            params.put("address", "test");
            params.put("amount", "3000");
            params.put("payment_type", "ep");
	    StringBuilder postData = new StringBuilder();
        for (Iterator it = params.entrySet().iterator(); it.hasNext();) {
            Map.Entry param = (Map.Entry) it.next();
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode((String) param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
	    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setRequestProperty("Authorization", "b1ea54317dc0663164ca6ca897254bd6f497bb392b0565a0116f87829a81d029");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	    conn.setDoOutput(true);
	    conn.getOutputStream().write(postDataBytes);
	    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    StringBuilder sb = new StringBuilder();
	    for (int c; (c = in.read()) >= 0;)
	        sb.append((char)c);
	    String response = sb.toString();
	            System.out.println("Response Code:"+conn.getResponseCode());
                    System.out.println("Response Code:"+conn.getResponseMessage());
                    System.out.println("Response Code:"+conn.getContentType());
            System.out.println(response);
	/*    JSONObject myResponse = new JSONObject(response.toString());
	    System.out.println("result after Reading JSON Response");
	    System.out.println("origin- "+myResponse.getString("origin"));
	    System.out.println("url- "+myResponse.getString("url"));
	    JSONObject form_data = myResponse.getJSONObject("form");
	    System.out.println("CODE- "+form_data.getString("CODE"));
	    System.out.println("email- "+form_data.getString("email"));
	    System.out.println("message- "+form_data.getString("message"));
	    System.out.println("name"+form_data.getString("name"));  */
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumewebservices;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author pc
 */
public class ConsumeWebServices extends Thread{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    /*    try {
            Client client=ClientBuilder.newClient();
            WebTarget target=client.target("http://localhost:7804/Calculator/Test,val1=556,val2=87,label=product");
            System.out.println(""+target.request(MediaType.APPLICATION_JSON).get(String.class));
            
            JSONObject obj = new JSONObject(target.request(MediaType.APPLICATION_JSON).get(String.class));
            String pageName = obj.getJSONObject("result").getString("add");
            System.out.println("Result of Add:: "+pageName);  */
            
            ConsumeWebServices t1=new ConsumeWebServices();
            ConsumeWebServices t2=new ConsumeWebServices();
            ConsumeWebServices t3=new ConsumeWebServices();
            ConsumeWebServices t4=new ConsumeWebServices();
            ConsumeWebServices t5=new ConsumeWebServices();
            ConsumeWebServices t6=new ConsumeWebServices();
            ConsumeWebServices t7=new ConsumeWebServices();
            ConsumeWebServices t8=new ConsumeWebServices();
            ConsumeWebServices t9=new ConsumeWebServices();
            ConsumeWebServices t10=new ConsumeWebServices();  
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();
            t8.start();
            t9.start();
            t10.start();  
            /*   JSONObject obj = new JSONObject(json);
            String pageName = obj.getJSONObject("pageInfo").getString("pageName");
            
            System.out.println(pageName);
            
            JSONArray arr = obj.getJSONArray("posts");
            for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
            System.out.println(post_id);
            } */
      /*  } catch (JSONException ex) {
            Logger.getLogger(ConsumeWebServices.class.getName()).log(Level.SEVERE, null, ex);
        }  */
    }
    
    @Override
    public void run()
    {
         try {
            Client client=ClientBuilder.newClient();
            WebTarget target=client.target("http://localhost:7804/Calculator/Test,val1=432,val2=876,label=product");
            System.out.println(""+target.request(MediaType.APPLICATION_JSON).get(String.class));
            
            JSONObject obj = new JSONObject(target.request(MediaType.APPLICATION_JSON).get(String.class));
            String pageName = obj.getJSONObject("result").getString("add");
            System.out.println("Result of Add:: "+pageName);
         }
     catch (JSONException ex) {
            Logger.getLogger(ConsumeWebServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

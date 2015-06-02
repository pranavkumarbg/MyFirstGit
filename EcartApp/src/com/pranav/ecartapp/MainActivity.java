package com.pranav.ecartapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.image.SmartImageView;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	TextView pname,price,ak,bk,ck;
	Bitmap bitmap;
	ImageView img;
	String imgurl;
	String productname;
    String price1;
    String aa;
    String ba;
    String ca;
    SmartImageView myImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        
     
        pname=(TextView)findViewById(R.id.textView1);
		price=(TextView)findViewById(R.id.textView2);
		ak=(TextView)findViewById(R.id.textView3);
		bk=(TextView)findViewById(R.id.textView4);
		ck=(TextView)findViewById(R.id.textView5);
		
		new RequestTask().execute("http://env-6425390.jelasticlw.com.br/json");
		
		//myImage.setImageUrl("http://www.chitramala.in/wp-content/uploads/2015/02/Sonakshi-Sinha-Anal-Arasu.jpg");
		
		//img.setImageBitmap(bitmap);
		
		//bitmap = getBitmapFromURL(imgurl);
        //img.setImageBitmap(bitmap);
        
        //pname.setText(productname);
        //price.setText(price1);

    }


    
    private class RequestTask extends AsyncTask<String, String, String>
    {
        // make a request to the specified url
    	@Override
        protected String doInBackground(String... uri)
        {
    		
    		//Toast.makeText(getApplicationContext(), uri, Toast.LENGTH_LONG).show();
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try
            {
            	           	
                    response = httpclient.execute(new HttpGet(uri[0]));
              
                    HttpEntity httpEntity = response.getEntity();
                    InputStream is = httpEntity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                          sb.append(line + "n");
                        }
                        is.close();
                        responseString = sb.toString();
                    
                 
               
            }
            catch (Exception e)
            {
                Log.d("Test", "Couldn't make a successful request!");
            }
            return responseString;
        }

    	
    	
        // if the request above completed successfully, this method will 
        // automatically run so you can do something with the response
        @Override
        protected void onPostExecute(String response)
        {
            super.onPostExecute(response);

            if (response != null)
            {
                try
                {
                	
                	   JSONObject reader = new JSONObject(response);
                	   JSONArray android = reader.getJSONArray("Messages");
                       for(int i = 0; i < android.length(); i++){
                       JSONObject jo = android.getJSONObject(i);
                   	
                       // Storing  JSON item in a Variable
                       String q_id = jo.getString("q_id");
                       String question = jo.getString("quetion");
                       String a = jo.getString("a");
                       String b = jo.getString("b");
                       String c = jo.getString("c");
                       String d = jo.getString("d");
            
                      // Toast.makeText(getApplicationContext(), question, Toast.LENGTH_LONG).show();
                	  
                	 
                      
                     
                       
                     pname.setText(question);
                     price.setText(a);
                     ak.setText(b);
                     bk.setText(c);
                     ck.setText(d);
              
                  }
                      
                }
                catch (JSONException e)
                {
                    Log.d("Test", "Failed to parse the JSON response!");
                   
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            else
            {
            	Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_LONG).show();
            }
        }

    }
    
   


    
}

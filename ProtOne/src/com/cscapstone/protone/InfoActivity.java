package com.cscapstone.protone;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import com.google.gson.*;

public class InfoActivity extends ListActivity {

	ArrayAdapter mInfoAdapter;
	
    @TargetApi(11)
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*
        // Create a progress bar to display while the list loads
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);
        
        // Must add the progress bar to the root of the layout
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);
        */
        
        DownloadTask downloadTask = new DownloadTask();
        
        
        
        setContentView(R.layout.activity_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_info, menu);
        return true;
    }
    
    
    private class DownloadTask extends AsyncTask<String, Integer, ArrayList>{

    	
    	
    	private HttpURLConnection sendPOIData(String poi) throws IOException{
            
            InputStream iStream = null;
            Gson gson = new Gson();
            ArrayList jList = new ArrayList();
            try{
                URL url = new URL(getString(R.string.poi_url));
                /** Creating an http connection to communicate with url */
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                /** Allow inputs */
                urlConnection.setDoInput(true);
                /** Allow outputs */
                urlConnection.setDoOutput(true);
                /** Use a cached copy */
                urlConnection.setDefaultUseCaches(true);
                
                /** Connecting to url */
                urlConnection.connect();
                
                /** Sending data to the server */
                urlConnection.setRequestMethod("GET");
                
                DataOutputStream dos = new DataOutputStream( urlConnection.getOutputStream() );
                
                dos.writeBytes("point of interest:" + poi + "\r\n");
                
                dos.flush();
                
                return urlConnection;
     
            }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
                return null;
            }
            
        }
    	
    	
    	@Override
    	protected void onPreExecute(){
    		
    	}
    	
		@Override
		protected ArrayList doInBackground(String... poi) {
			try{
				
			}
			catch(Exception e){
				
			}
			
			return null;
		}
    	
    }
    
}

package com.cscapstone.protone;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class LocationActivity extends Activity implements LocationListener {

	private LocationManager locationManager;
	private String provider;
	private TextView latitudeView;
	private TextView longitudeView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitudeView = (TextView) findViewById(R.id.latitudeTextView);
        longitudeView = (TextView) findViewById(R.id.longitudeTextView);
        
        //Get Location Manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        Criteria criteria = new Criteria();
    	provider = locationManager.getBestProvider(criteria, false);
    	locationManager.requestLocationUpdates(provider, 0, 1, this);
    	updateLocation(locationManager.getLastKnownLocation(provider));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    protected void onResume(){
    	super.onResume();
    	locationManager.requestLocationUpdates(provider, 500, 1, this);
    }
    
    protected void onPause(){
    	super.onPause();
    	locationManager.removeUpdates(this);
    }
    
	public void onLocationChanged(Location location) {
		updateLocation(location);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
    private void updateLocation(Location location){
    	final double testLat = 56.0;
    	final double testLongi = 120.0;
    	if(location != null){
    		if( (int)location.getLatitude() == testLat && (int)location.getLongitude() == testLongi ){
    			new AlertDialog.Builder(this).setTitle("Location Found!").setMessage("You have found a building")
    			.setPositiveButton("Show Details",new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
							new ShowBuildingInfo().execute();
					}
    			}).setNeutralButton("Cancel", null).show();
    		}
				int log = (int) (location.getLongitude());
				int lat = (int) (location.getLatitude());
				latitudeView.setText(String.valueOf(lat));
				longitudeView.setText(String.valueOf(log));
		}
		else
		{
			latitudeView.setText("Location Not Available");
    		longitudeView.setText("Location Not Available");
		}
    	
    }
    
    class ShowBuildingInfo extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {
			String building = "";
			 
			
			return null;
		}
    	
    }
    
}

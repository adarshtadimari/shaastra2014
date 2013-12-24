package org.shaastra.activities;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.shaastra.helper.DBAdapter;
import org.shaastra.helper.DatabaseHelper;
import org.shaastra.helper.HTTPHelper;



import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class LandingActivity extends Activity {
	
	Intent i;
	ArrayList<String> cNames=new ArrayList<String>();
	ArrayList<String> cNumber=new ArrayList<String>();
	ArrayList<String> cEvents=new ArrayList<String>();
	String dpath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //To remove title
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to remove notification bar
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
        try
		{
			somePreliminaryDatabaseTests();
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
        i= new Intent(this,TilesActivity.class);
        ImageButton landingButton= (ImageButton)findViewById(R.id.landingbutton);
        /*
        landingButton.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle scaleBundle=ActivityOptions.makeCustomAnimation(LandingActivity.this,R.anim.slide_in_down,R.anim.slide_out_up).toBundle();
				
				startActivity(i,scaleBundle);
			}
		});*/
        landingButton.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

					Bundle scaleBundle=ActivityOptions.makeCustomAnimation(LandingActivity.this,R.anim.slide_in_down,R.anim.slide_out_up).toBundle();
					i.putStringArrayListExtra("cname",cNames);
					i.putStringArrayListExtra("cphone",cNumber);
					i.putStringArrayListExtra("cevent",cEvents);
					
					
					startActivity(i,scaleBundle);
				}
				
		
				return false;
			}
		});
       
    }
    private void somePreliminaryDatabaseTests() throws JSONException
	{
    	DBAdapter db = new DBAdapter(this);
    	
    	try {
            String destPath = "/data/data/" + getPackageName() +
                "/databases";
            
            File f = new File(destPath);
            if (!f.exists()) {            	
            	f.mkdirs();
                f.createNewFile();
            	
            	//---copy the db from the assets folder into 
            	// the databases folder---
                CopyDB(getBaseContext().getAssets().open("mydb"),
                    new FileOutputStream(destPath + "/MyDB"));
                
                
            }
                
            
            
            //writeToSD();
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	db.open();
    	
        Cursor c = db.getAllContacts();
        if (c.moveToFirst())
        {
            do {
            		cNames.add(c.getString(1));
            		cNumber.add(c.getString(2));
            		cEvents.add(c.getString(3));
            		//Log.d("event",c.getString(3));
                //DisplayContact(c);
            } while (c.moveToNext());
        }
        
       db.insertEvent("2","ROBOTICS" , "INTRasdasd", "FORMAT", "PRIZE", "VENUE");
       
       
        
        
        db.close();
        
        
        
        
		/*
		db.open();
		String coordInfo = HTTPHelper.getData("http://www.shaastra.org/mobops/coords/");
		JSONObject coordJson = new JSONObject(coordInfo);
		JSONArray coords = coordJson.getJSONArray("users");
		
		for(int i = 0; i < coords.length(); i++)
		{
			JSONObject j = coords.getJSONObject(i);
			
			String coordName = j.getString("name");
			cNames.add(coordName);
			
			Log.d("name", cNames.get(i));
			String coordID = j.getString("id");
			String eventName = j.getString("department");
			String phone = j.getString("phone");
			cNumber.add(phone);
			ContentValues cv = new ContentValues();
			cv.put("_id", coordID);
			cv.put("coordName", coordName);
			cv.put("phone", phone);
			cv.put("eventName", eventName);
			db.insertContact(coordID,coordName,phone,eventName);
		
		}
		
		db.close();
		*/
		/*try {
			writeToSD();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tiles, menu);
        return true;
    }
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	    	case R.id.action_credits1:
	    		startActivity(new Intent(this,CreditsActivity.class));
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    public void DisplayContact(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                "Name: " + c.getString(1) + "\n" +
                "Email:  " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }
    public void CopyDB(InputStream inputStream, 
    	    OutputStream outputStream) throws IOException {
    	        //---copy 1K bytes at a time---
    	        byte[] buffer = new byte[1024];
    	        int length;
    	        while ((length = inputStream.read(buffer)) > 0) {
    	            outputStream.write(buffer, 0, length);
    	        }
    	        inputStream.close();
    	        outputStream.close();
    	    }
    

    @SuppressWarnings({ "resource", "unused" })
	private void writeToSD() throws IOException {
        File sd = Environment.getExternalStorageDirectory();

        if (sd.canWrite()) {
            String currentDBPath = "MyDB";
            String backupDBPath = "backupname.db";
            File currentDB = new File("/data/data/" + getPackageName() +
                    "/databases", currentDBPath);
            File backupDB = new File(sd, backupDBPath);

            if (currentDB.exists()) {
                @SuppressWarnings("resource")
				FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        }
    }


    
    
}

package org.shaastra.activities;



import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
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

public class LandingActivity extends Activity {
	
	Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //To remove title
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to remove notification bar
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);
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
					startActivity(i,scaleBundle);
				}
				
		
				return false;
			}
		});
       
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
    



    
    
}

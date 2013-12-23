package org.shaastra.activities;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.HapticFeedbackConstants;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.*;
public class TilesActivity extends Activity {
	
	private Vibrator myvib;
	private int flag =1;
	private Intent i,i2,i3,i4,i5;
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        //To remove title
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to remove notification bar
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tiles);
        final ArrayList<String> cNames=(ArrayList<String>) getIntent().getStringArrayListExtra("cname");
        final ArrayList<String> cNumber=(ArrayList<String>) getIntent().getStringArrayListExtra("cphone");
        final ArrayList<String> cEvents=(ArrayList<String>) getIntent().getStringArrayListExtra("cevent");
        
        //Log.d("val",cNames.get(3));
       
        i = new Intent(this,EventList.class);
        i2= new Intent(this,CoordinatorsList.class);
        i3= new Intent(this,ShowsFlip.class);
        i4= new Intent(this,Sponsors.class);
        i5=new Intent(this,LectureActivity.class);
        
        
        myvib=(Vibrator)this.getSystemService(VIBRATOR_SERVICE);
        
        ImageButton tilem1=(ImageButton)findViewById(R.id.tilem1);
        ImageButton tiles1=(ImageButton)findViewById(R.id.tiles1);
        ImageButton tiles2=(ImageButton)findViewById(R.id.tiles2);
        ImageButton tiles3=(ImageButton)findViewById(R.id.tiles3);
        ImageButton tiles4=(ImageButton)findViewById(R.id.tiles4);
        ImageButton tileb1=(ImageButton)findViewById(R.id.tileb1);
        ImageButton tileb2=(ImageButton)findViewById(R.id.tileb2);
        ImageButton tilel1=(ImageButton)findViewById(R.id.tilel1);
        ImageButton tilel2=(ImageButton)findViewById(R.id.tilel2);
                
        //to get current animation time
        long ct= AnimationUtils.currentAnimationTimeMillis();
        
        final TranslateAnimation tA1= new TranslateAnimation(Animation.RELATIVE_TO_SELF,0 , Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, +500, Animation.RELATIVE_TO_SELF, 0);
        tA1.setDuration(600);
        tA1.setStartTime(ct+100);
        final TranslateAnimation tA2= new TranslateAnimation(Animation.RELATIVE_TO_SELF,0 , Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, +500, Animation.RELATIVE_TO_SELF, 0);
        tA2.setDuration(600);
        
        tA2.setStartTime(ct+100);  //Starts 600ms after tA1
        
        final TranslateAnimation tA3= new TranslateAnimation(Animation.RELATIVE_TO_SELF,0 , Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, +500, Animation.RELATIVE_TO_SELF, 0);
        tA3.setDuration(600);
        tA3.setStartTime(ct+100); //Starts 1200ms after tA1
        	
        tilem1.setAnimation(tA1);
        tiles1.setAnimation(tA1);
        tiles2.setAnimation(tA1);
        tiles3.setAnimation(tA1);
        tiles4.setAnimation(tA1);
        tileb1.setAnimation(tA2);
        tileb2.setAnimation(tA2);
        tilel1.setAnimation(tA2);
        tilel2.setAnimation(tA2);
        
        
		OnTouchListener touchlistener=new View.OnTouchListener() {
			
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
					
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					v.setAlpha((float)0.75);
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					startActivity(i,scaleBundle);
					
				}
				
				return false;
			}
		};
		OnTouchListener touchlistener2=new View.OnTouchListener() {
			
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					startActivity(i,scaleBundle);
					
				}
				
				return false;
			}
		};
        //tilem1.setOnTouchListener(touchlistener);
        //tiles1.setOnTouchListener(touchlistener2);
        //tiles2.setOnTouchListener(touchlistener);
        //tiles3.setOnTouchListener(touchlistener);
        //tiles4.setOnTouchListener(touchlistener);
        tilem1.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
					
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					i2.putStringArrayListExtra("cname", cNames);
					i2.putStringArrayListExtra("cphone", cNumber);
					i2.putStringArrayListExtra("cevent", cEvents);
					
					startActivity(i2,scaleBundle);
					
				}
		
				return false;
			}
		});
        tileb1.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
					
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					startActivity(i,scaleBundle);
					
				}
		
				return false;
			}
		});

		tileb2.setOnTouchListener(new View.OnTouchListener() {
		
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@SuppressLint("NewApi")
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if(event.getAction()==MotionEvent.ACTION_DOWN)
			{
	
		    	myvib.vibrate(25);
				v.setAlpha((float)0.4);
				
				
			}
			if(event.getAction()==MotionEvent.ACTION_UP)
			{
				v.setAlpha((float)0.75);
				
				
				
				startActivity(i3);
				
			}
	
			return false;
		}
	});

        tiles1.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
					
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Shaastra"));
					startActivity(browserIntent,scaleBundle);
					
				}
		
				return false;
			}
		});
        tiles2.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/109668817957263291803/about"));
					startActivity(browserIntent,scaleBundle);
					
				}
		
				return false;
			}
		});
        
        tiles3.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
					
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/user/iitmshaastra"));
					startActivity(browserIntent,scaleBundle);
					
				}
		
				return false;
			}
		});
        tiles4.setOnTouchListener(new View.OnTouchListener() {
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN)
		{

	    	myvib.vibrate(25);
			v.setAlpha((float)0.4);
			v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
		}
		if(event.getAction()==MotionEvent.ACTION_UP)
		{
			v.setAlpha((float)0.75);
			v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
			Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ShaastraIITM"));
			startActivity(browserIntent,scaleBundle);
			
		}

		return false;
	}
});
        tilel2.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					Bundle scaleBundle=ActivityOptions.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
					
					startActivity(i4,scaleBundle);
					
				}
		
				return false;
			}
		});
        tilel1.setOnTouchListener(new View.OnTouchListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_DOWN)
				{

			    	myvib.vibrate(25);
					v.setAlpha((float)0.4);
					v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
				}
				if(event.getAction()==MotionEvent.ACTION_UP)
				{
					v.setAlpha((float)0.75);
					v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
					startActivity(i5);
					
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

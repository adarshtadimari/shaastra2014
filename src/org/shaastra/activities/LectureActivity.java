package org.shaastra.activities;


import android.R.anim;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ViewFlipper;

public class LectureActivity extends FragmentActivity {
	 private static final int NUM_PAGES = 15;
	 private ViewPager mViewPager;
	 private PagerAdapter mPagerAdapter;
	 private ViewFlipper v;
	 private int cur=0;
	 String[] lecData=new String[]{"Date1 Time1 Venue1","Date2 Time2 Venue2","Date3 Time3 Venue3","Date4 Time4 Venue4","Date5 Time5 Venue5","Date6 Time6 Venue6","Date7 Time7 Venue7","Date8 Time8 Venue8","Date9 Time9 Venue9","Date10 Time10 Venue10","Date11 Time11 Venue11","Date12 Time12 Venue12","Date13 Time13 Venue13","Date14 Time14 Venue14","Date15 Time15 Venue15"};
	 String[] lecName=new String[]{"Dr.Richard Stallman","Amitabh Shah","Shravan and Sanjay Kumaran","S.Narasinga Rao","Raja Manohar","Anirudh Sharma","V.P.Agrawal","Manoj Pant","Sharmila Bhattacharya","John R Adler","Bikash Sinha","Anant Agarwal","Ajit Narayanan","Srinivasa Varadhan","Dave Lavery"};
	 @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//to remove notification bar
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_lecture);
		v =(ViewFlipper)findViewById(R.id.lectureflipper);
		v.setInAnimation(this,android.R.anim.fade_in);
		v.setOutAnimation(this,android.R.anim.fade_out);
		//getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setTitle(lecName[cur]);
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.diagonal_blue));
		getActionBar().setSubtitle(lecData[cur]);
		//getActionBar().set
		//getActionBar()
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lecture, menu);
		return true;
		
		}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_next:
	            v.showNext();
	            if(cur==14)
	            	cur=0;
	            else
	            	cur++;
	            getActionBar().setSubtitle(lecData[cur]);
	            getActionBar().setTitle(lecName[cur]);
	            
	            return true;
	        case R.id.action_previous:
	            v.showPrevious();
	            if(cur==0)
	            	cur=14;
	            else
	            	cur--;
	            getActionBar().setSubtitle(lecData[cur]);
	            getActionBar().setTitle(lecName[cur]);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
	

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
	 String[] lecData=new String[]{"7th Jan 12pm IC&SR","4th Jan 12 pm IC&SR","5th Jan 12 pm CLT","6th Jan 10 am IC&SR","7th Jan 10 am IC&SR","4th Jan 6pm IC&SR","","5th Jan 10 am IC&SR","4th Jan 4 pm IC&SR","4th Jan 2 pm IC&SR","6th Jan 2 pm IC&SR","7th Jan 2 pm IC&SR","6th Jan 4 pm IC&SR","5th Jan 12 pm IC&SR",""};
	
	 String[] lecName=new String[]{"Dr.Richard Stallman","Amitabh Shah","Shravan and Sanjay Kumaran","S.Narasinga Rao","Raja Manohar","Anirudh Sharma","V.P.Agrawal","Manoj Pant","Sharmila Bhattacharya","John R Adler","Bikash Sinha","Anant Agarwal","Ajit Narayanan","Srinivasa Varadhan","Dave Lavery"};
	 @Override
		public void finish() {
			// TODO Auto-generated method stub
			super.finish();
			overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);
		}
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
	

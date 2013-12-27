package org.shaastra.activities;

import java.util.ArrayList;

import org.shaastra.helper.QuickContactFragment;
import org.shaastra.helper.SuperAwesomeCardFragment2;

import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class CoordinatorsList extends FragmentActivity {

	private final Handler handler = new Handler();
	
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	ArrayList<String> cn=new ArrayList<String>();
	ArrayList<String> pn=new ArrayList<String>();
	ArrayList<String> en=new ArrayList<String>();
	ArrayList<String> esn=new ArrayList<String>();
	private Drawable oldBackground = null;
	private int currentColor = 0xFF25495F;
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		overridePendingTransition(R.anim.slid_in_left,R.anim.slid_out_right);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//To remove title
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to remove notification bar
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_coordinators_list);
		ArrayList<String>cNames= (ArrayList<String>)getIntent().getStringArrayListExtra("cname");
		ArrayList<String>cNumber= (ArrayList<String>)getIntent().getStringArrayListExtra("cphone");
		ArrayList<String>cEvents= (ArrayList<String>)getIntent().getStringArrayListExtra("cevent");
		ArrayList<String>cEventsSub= (ArrayList<String>)getIntent().getStringArrayListExtra("ceventsub");
		
		cn.addAll(cNames);
		pn.addAll(cNumber);
		en.addAll(cEvents);
		esn.addAll(cEventsSub);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs2);
		pager = (ViewPager) findViewById(R.id.pager2);
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		//changeColor(currentColor);
		tabs.setIndicatorColor(currentColor);
		//tabs.setTextColorResource(Color.WHITE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.action_settings:
			QuickContactFragment dialog = new QuickContactFragment();
			dialog.show(getSupportFragmentManager(), "QuickContactFragment");
			return true;

		}

		return super.onOptionsItemSelected(item);
	}



	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("currentColor", currentColor);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		currentColor = savedInstanceState.getInt("currentColor");
		
	}

	private Drawable.Callback drawableCallback = new Drawable.Callback() {
		@SuppressLint("NewApi")
		@Override
		public void invalidateDrawable(Drawable who) {
			getActionBar().setBackgroundDrawable(who);
		}

		@Override
		public void scheduleDrawable(Drawable who, Runnable what, long when) {
			handler.postAtTime(what, when);
		}

		@Override
		public void unscheduleDrawable(Drawable who, Runnable what) {
			handler.removeCallbacks(what);
		}
	};

	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "All","Events","Facilities","QMS","Student Relations","Spons & PR","Shows","Evolve","Finance","Webops","Design" };
		

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return SuperAwesomeCardFragment2.newInstance(position,cn,pn,en,esn);
		}

	}

}
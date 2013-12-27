package org.shaastra.activities;

import org.shaastra.helper.DBAdapter;
import org.shaastra.helper.QuickContactFragment;
import org.shaastra.helper.SuperAwesomeCardFragment;

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
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class EventActivity extends FragmentActivity {
	
	 String db[]={"Fire N Ice","Lunar Rover Challenge","Ultimate Engineer","Contraptions","Robowars","Junkyard Wars","Robotics"};
	 String af[]={"Aerobotics","Wright Design","Paper Planes","TopGun","AirShow","Boeing National Aeromodelling Competition"};
	 String cod1[]={"Open Programmin Contest","Reverse Coding","Triathlon","Debugging","Code Obfuscation","Automania","Hackfest Workshop"};
	 String inv[]={"ProjectX","Shaastra Cube Open","Math Modelling","Puzzle Champ"};
	 String qui[]={"Shaastra Junior Quiz","Shaastra Main Quiz","How Things Work","Auto Quiz"};
	 String on[]={"Online Puzzle Champ","Online Math Modelling","Finance and Consultancy"};
	 String df[]={"Robo Oceana","Forensics","Shaastra Circuit Design Challenge","Chemical X","Master Builder","Desmod","Onspot Desmod"};
	 String sp[]={"Sustainable CityScape","Paper and Poster Presentation","Shaastra Junior","IIT Madras Symposium","Ideas Challenge"};
	 String ws[]={"Autonomous Robotics","Chuckglider","Hovercraft","Quadrotor","3D Animation","Forensics","Streax","Rubiks Cube","Android","Manual Robotics","Paper Planes"};
	 String ex[]={"Product Launch","Tech Lounge","Magic Materials"};
	 String be[]={"Case Study","Vittaneeti","Estimus"};
	 String ase[]={"Erricson IDP","Eaton IDP"};
	 String try1[][]={db,af,cod1,inv,qui,on,df,sp,ws,ex,be,ase};
	 

	private final Handler handler = new Handler();

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	private Drawable oldBackground = null;
	private int currentColor = 0xFF25495F;
	String eventName=new String();
	String eventFormat=new String();
	String eventIntroduction=new String();
	String eventPrize=new String();
	String eventVenue=new String();
	
	
	
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
		
    	setContentView(R.layout.activity_event_try);

    	Bundle extras= getIntent().getExtras();
    	int eventCatIndex= extras.getInt("categoryindex");
    	int eventIndex= extras.getInt("eventIndex");
    	
    	Log.d("event", try1[eventCatIndex][eventIndex]);
    	DBAdapter db=new DBAdapter(this);
    	db.open();
    	Cursor c=db.getAllEvents();
    	
    	  if (c.moveToFirst())
          {
              do {
              		if(c.getString(1).equalsIgnoreCase(try1[eventCatIndex][eventIndex]))
              		{
              			eventName= c.getString(1);
              			eventIntroduction= c.getString(2);
              			eventFormat= c.getString(3);
              			eventPrize= c.getString(4);
              			eventVenue= c.getString(5);
              			
              		}
            	  Log.d("eventevent",c.getString(3));
                  //DisplayContact(c);
              } while (c.moveToNext());
          }
    	db.close();
    	tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs3);
		pager = (ViewPager) findViewById(R.id.pager3);
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		//changeColor(currentColor);
		tabs.setIndicatorColor(currentColor);
		
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

		private final String[] TITLES = { "Introduction","Map","Event Format", "Prize" };

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
			return SuperAwesomeCardFragment.newInstance(position,eventIntroduction,eventVenue,eventFormat,eventPrize);
		}

	}

}
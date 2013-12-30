/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.shaastra.helper;

import org.shaastra.activities.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;

public class SuperAwesomeCardFragment extends Fragment {
	private Vibrator myvib;

	private static final String ARG_POSITION = "position";
	Location l = null;

	private int position;
	String eintroduction=new String();
	String eformat=new String();
	String evenue=new String();
	String eprize=new String();
	String elatlong=new String();
	
	
	public static SuperAwesomeCardFragment newInstance(int position,String introduction,String venue,String format,String prize,String latlong) {
		SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
		
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		b.putString("int", introduction);
		b.putString("format", format);
		b.putString("venue", venue);
		b.putString("prize", prize);		
		b.putString("latlong", latlong);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
				
		eintroduction=getArguments().getString("int");
		eformat=getArguments().getString("format");
		eprize=getArguments().getString("prize");
		evenue=getArguments().getString("venue");
		elatlong=getArguments().getString("latlong");
			
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		
		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);

		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
				.getDisplayMetrics());

		TextView v = new TextView(getActivity());
		params.setMargins(margin, margin, margin, margin);
		v.setLayoutParams(params);
		v.setLayoutParams(params);
		v.setGravity(Gravity.CENTER);
		//v.setBackgroundResource(R.drawable.background_card);
		v.setText("CARD " + (position + 1));

		
		if(position ==0)
		{
			View v1=inflater.inflate(R.layout.event_info, container,false);
			v1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			TextView t1=(TextView)v1.findViewById(R.id.infotext);
			t1.setText(eintroduction);
			return v1;
		}
		else if(position==1)
			
		{	
			
			String Venue=new String();
			Venue=evenue;
			
			final String SAC=elatlong;
			//String start=String.valueOf(l.getLatitude())+String.valueOf(l.getLongitude());
		    /*
			View v1=inflater.inflate(R.layout.map, container,false);
			v1.setLayoutParams(params);
			WebView wv=(WebView)v1.findViewById(R.id.wv1);
			wv.setWebChromeClient(new WebChromeClient());
			//wv.loadUrl("https://maps.google.com/maps?saddr=13,80&daddr=13,80.02");
			//wv.loadUrl("http://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&sensor=false");
			wv.loadUrl("http://maps.google.com/maps?f=d&daddr=51.448,-0.972");
			wv.getSettings().getBuiltInZoomControls();
			*/
			View v1=inflater.inflate(R.layout.map, container,false);
			v1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			TextView location= (TextView)v1.findViewById(R.id.locationText);
			Button mapButton=(Button)v1.findViewById(R.id.mapButton);
			if(evenue.equalsIgnoreCase("NONE"))
			{
				mapButton.setAlpha(0);
			}
			else
			{
				mapButton.setAlpha(1);
			}
				
			location.setText(Venue);
			mapButton.setOnTouchListener(new View.OnTouchListener() {
				
				@TargetApi(Build.VERSION_CODES.HONEYCOMB)
				@SuppressLint("NewApi")
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction()==MotionEvent.ACTION_DOWN)
					{

				    	
						v.setAlpha((float)0.4);
						v.animate().setInterpolator(new DecelerateInterpolator()).scaleX(0.9f).scaleY(0.9f);
						
					}
					if(event.getAction()==MotionEvent.ACTION_UP)
					{
						v.setAlpha((float)0.75);
						v.animate().setInterpolator(new OvershootInterpolator()).scaleX(1f).scaleY(1f);
						
						Intent intent = new Intent(Intent.ACTION_VIEW, 
							    Uri.parse("http://maps.google.com/maps?f=d&daddr="+SAC));
							intent.setComponent(new ComponentName("com.google.android.apps.maps", 
							    "com.google.android.maps.MapsActivity"));
							startActivity(intent);						
					}
			
					return false;
				}
			});

			
			
			return v1;
		}
		else if(position==2)
		{
			View v1=inflater.inflate(R.layout.event_info, container,false);
			v1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			TextView t1=(TextView)v1.findViewById(R.id.infotext);
			t1.setText(eformat);
			return v1;
			
		}
		else if(position==3)
		{
			View v1=inflater.inflate(R.layout.prize, container,false);
			v1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			TextView t1=(TextView)v1.findViewById(R.id.prizeText);
			t1.setText(eprize);
			return v1;
			
		}
		else if(position==4)
		{
			v.setBackgroundColor(Color.GREEN);
			
		}else if(position==5)
		{
			
			v.setBackgroundColor(Color.MAGENTA);
			
		}else if(position==6)
		{
			v.setBackgroundColor(Color.MAGENTA);
			
		}
		fl.addView(v);
		return fl;
		
	}

}
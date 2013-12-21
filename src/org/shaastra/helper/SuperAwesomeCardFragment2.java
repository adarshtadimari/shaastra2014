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





import java.util.ArrayList;
import java.util.List;
import org.shaastra.activities.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class SuperAwesomeCardFragment2 extends Fragment {

	private static final String ARG_POSITION = "position";
	AlertDialog.Builder b1;

	private int position;

	public static SuperAwesomeCardFragment2 newInstance(int position) {
		SuperAwesomeCardFragment2 f = new SuperAwesomeCardFragment2();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
				
		
			
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		ListView lv;
		EditText inputSearch;
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
			
		}
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Adarsh Tadimari", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
		
			ArrayList<Coord> rowItems =new ArrayList<Coord>();
			for (int i = 0; i < values.length; i++) {
	            Coord item = new Coord(values[i],"9789091025");
	            rowItems.add(item);
	        }
			
			
			/*final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    R.layout.custom_list_item 
                 );
			files.addAll(values);
			*/
			final CoordAdapter files=new CoordAdapter(getActivity(),R.layout.cordlist,rowItems);
			
			
			v1.setBackgroundColor(0xbba0d9ea);
			
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	
			    	b1= new AlertDialog.Builder(getActivity());
			    	b1.setMessage("What do you want to do?");
			    	b1.setNegativeButton("Call", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String url = "tel:9789091025";
					        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
					        startActivity(intent);
						}
					});
			    	b1.setPositiveButton("Message", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String url = "sms:9789091025";
					        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
					        startActivity(intent);
						}
					});
			    	AlertDialog a1=b1.create();
			    	a1.setCanceledOnTouchOutside(true);
			    	a1.show();
			    
			  //Open the browser here
			}
			});
			
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs.toString());
					
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;

		
	}
}
/*
		else if(position==1)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackgroundColor(0xbbdee789);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					
					files.getFilter().filter(cs.toString());	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
			
		}
		else if(position==2)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackgroundColor(0xbbdee789);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
			
		}
		else if(position==3)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackgroundColor(0xbbdee789);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
			
		}
		else if(position==4)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Adarsh Tadimari", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackgroundColor(0xbbdee789);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
			
		}else if(position==5)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Adarsh Tadimari", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackgroundColor(0xbbdee789);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
			
		}
		else if(position==6)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Adarsh Tadimari", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackgroundColor(0xbbdee789);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
		}
		else if(position==7)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Adarsh Tadimari", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackgroundColor(0xbbdee789);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
		}
		else if(position==8)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Adarsh Tadimari", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			v1.setBackgroundColor(0xbba0d9ea);
			//v1.setBackgroundColor(0xbbdee789);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
		}
		else if(position==9)
		{
			View v1=inflater.inflate(R.layout.contacts_list, container,false);
			lv= (ListView)v1.findViewById(R.id.list1);
			inputSearch = (EditText)v1.findViewById(R.id.inputSearch);
			
			String[] values = new String[] { "Adarsh Tadimari", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
               "Linux", "OS/2" };
			final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                    android.R.layout.simple_list_item_1, 
                    values);
			//v1.setBackground(R.drawable.backblue);
			v1.setBackgroundColor(0xbba0d9ea);
			lv.setAdapter(files);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) { 
			    	String url = "tel:9789091025";
			        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
			        startActivity(intent);
			  //Open the browser here
			}
			});
			inputSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
					// When user changed the Text
					files.getFilter().filter(cs);	
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub							
				}
			});
	    
			//v1.setLayoutParams(params);
			return v1;
		}
		fl.addView(v);
		return fl;
		
	}

}*/
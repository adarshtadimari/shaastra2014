package org.shaastra.helper;

import org.shaastra.activities.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;

public class MyCard extends Card {
	int flag=0;
	public MyCard(String title){
		super(title);
	}
	public MyCard(String title,String desc){
		
		super(title,desc);
		flag=1;
	}
	

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_ex, null);

		((TextView) view.findViewById(R.id.title)).setText(title);
		if(flag==1)
			((TextView) view.findViewById(R.id.description)).setText(desc);

		
		return view;
	}

	
	
	
}

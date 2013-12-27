package org.shaastra.helper;

import java.util.ArrayList;

import org.shaastra.activities.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class CoordAdapter extends BaseAdapter implements Filterable {

private ArrayList<Coord> mOriginalValues; // Original Values
private ArrayList<Coord> mDisplayedValues;    // Values to be displayed
LayoutInflater inflater;
int layout;
Context context;
public CoordAdapter(Context context, int layoutid,ArrayList<Coord> mProductArrayList) {
    this.mOriginalValues = mProductArrayList;
    this.mDisplayedValues = mProductArrayList;
    this.context=context;
    layout=layoutid;
}

@Override
public int getCount() {
    return mDisplayedValues.size();
}

@Override
public Object getItem(int position) {
    return position;
}

@Override
public long getItemId(int position) {
    return position;
}

private class ViewHolder {
    TextView Name;
    TextView Phone;
    TextView subEvent;
}

@Override
public View getView(final int position, View convertView, ViewGroup parent) {

    ViewHolder holder = null;
    

    if (convertView == null) {

        holder = new ViewHolder();
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(layout, parent, false);
        
        holder.Name = (TextView)convertView.findViewById(R.id.cordname);
        holder.Phone = (TextView) convertView.findViewById(R.id.cordphone);
        holder.subEvent =(TextView)convertView.findViewById(R.id.cordsub);
         convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }
    holder.Name.setText(mDisplayedValues.get(position).name);
    holder.Phone.setText(mDisplayedValues.get(position).phone);
    holder.subEvent.setText(mDisplayedValues.get(position).subEvent);
    

   
    return convertView;
}

@Override
public Filter getFilter() {
    Filter filter = new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,FilterResults results) {

            mDisplayedValues = (ArrayList<Coord>) results.values; // has the filtered values
            notifyDataSetChanged();  // notifies the data with new filtered values
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
            ArrayList<Coord> FilteredArrList = new ArrayList<Coord>();

            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<Coord>(mDisplayedValues); // saves the original data in mOriginalValues
            }

            /********
             * 
             *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
             *  else does the Filtering and returns FilteredArrList(Filtered)  
             *
             ********/
            
            if (constraint == null || constraint.length() == 0) {

                // set the Original result to return  
                results.count = mOriginalValues.size();
                results.values = mOriginalValues;
            } else {
                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < mOriginalValues.size(); i++) {
                    String data = mOriginalValues.get(i).name;
                    String data2 = mOriginalValues.get(i).name2;
                    String data3 = mOriginalValues.get(i).event;
                    String data4 = mOriginalValues.get(i).subEvent;
                    String data5 = mOriginalValues.get(i).name3;
                    
                    
                    if (data.toLowerCase().startsWith(constraint.toString())||(data2.toLowerCase().startsWith(constraint.toString()))||(data3.toLowerCase().startsWith(constraint.toString()))||(data4.toLowerCase().startsWith(constraint.toString()))||(data5.toLowerCase().startsWith(constraint.toString()))) {
                        FilteredArrList.add(new Coord(mOriginalValues.get(i).name,mOriginalValues.get(i).phone,mOriginalValues.get(i).subEvent,mOriginalValues.get(i).event));
                    }
                }
                // set the Filtered result to return
                results.count = FilteredArrList.size();
                results.values = FilteredArrList;
            }
            return results;
        }
    };
    return filter;
}


}
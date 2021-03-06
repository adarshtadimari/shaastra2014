package org.shaastra.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/*
 * cv.put("time", time);
			cv.put("_id", Integer.parseInt(eventID));
			cv.put("eventCategoryID", val);
			cv.put("eventName", eventName);
			cv.put("introduction", introduction);
			cv.put("format", format);
			cv.put("prize", prizemoney);
			cv.put("venueID", venueid);
 */
public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_PHONE = "phone";
    static final String KEY_EVENT = "event";
    static final String KEY_EVENT_SUB = "subevent";
    static final String EVENT_ID="_id";
    static final String EVENT_NAME= "eventname";
    static final String EVENT_INTRODUCTION="introduction";
    static final String EVENT_FORMAT="format";
    static final String EVENT_PRIZE="prize";
    static final String EVENT_VENUEID="venueid";
    static final String VENUE_ID="_id";
    static final String VENUE_NAME="name";
    static final String VENUE_LATLONG="latlong";
    
    
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "shaastraDB2";
    static final String DATABASE_TABLE = "contacts";
    static final String DATABASE_TABLE_EVENT = "events";
    static final String DATABASE_TABLE_VENUE = "venue";
    static final int DATABASE_VERSION = 2;

    static final String DATABASE_CREATE =
        "create table contacts (_id integer primary key autoincrement, "
        + "name text not null, phone text not null);";

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() 
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertContact(String id,String name, String phone,String event,String subevent) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, id);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_EVENT, event);
        initialValues.put(KEY_EVENT_SUB, subevent);
        
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    public long insertEvent(String id,String eventName, String introduction, String format, String prize,String venueID)
    {
    	ContentValues initialValues = new ContentValues();
    	 initialValues.put(EVENT_ID, id);
         initialValues.put(EVENT_NAME, eventName);
         initialValues.put(EVENT_INTRODUCTION, introduction);
         initialValues.put(EVENT_FORMAT, format);
         initialValues.put(EVENT_PRIZE, prize);
         initialValues.put(EVENT_VENUEID, venueID);
         return db.insert(DATABASE_TABLE_EVENT, null, initialValues);
    }
    //---deletes a particular contact---
    public boolean deleteContact(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                KEY_PHONE,KEY_EVENT,KEY_EVENT_SUB}, null, null, null, null, null);
    }
    public Cursor getAllEvents()
    {
        return db.query(DATABASE_TABLE_EVENT, new String[] {EVENT_ID, EVENT_NAME,
                EVENT_INTRODUCTION,EVENT_FORMAT,EVENT_PRIZE,EVENT_VENUEID}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getContact(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_NAME, KEY_PHONE,KEY_EVENT,KEY_EVENT_SUB}, KEY_ROWID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getVenue(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_VENUE, new String[] {VENUE_ID,
                VENUE_NAME, VENUE_LATLONG}, VENUE_ID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getEvent(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_EVENT, new String[] {EVENT_ID, EVENT_NAME,
                        EVENT_INTRODUCTION,EVENT_FORMAT,EVENT_PRIZE,EVENT_VENUEID}, 
                        EVENT_ID + "=" + rowId, null,
                null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateContact(long rowId, String name, String phone,String event) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_PHONE, phone);
        args.put(KEY_EVENT, event);
        
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

}

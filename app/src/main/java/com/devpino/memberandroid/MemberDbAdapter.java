package com.devpino.memberandroid;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MemberDbAdapter {

    private static final String TAG = "MemberDbAdapter";
    
    public static MemberDbAdapter instance = null;
    
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
    	"create table devpino_member (_id integer primary key autoincrement, "
        + "email text not null, member_name text not null);";

    private static final String DATABASE_NAME = "memberdb";
    private static final String DATABASE_TABLE = "devpino_member";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;
    
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS memberdb");
            onCreate(db);
        }
    }    
    
    public MemberDbAdapter(Context context) {
    	this.mCtx = context;
    	
    	if (mDbHelper == null) {
            mDbHelper = new DatabaseHelper(mCtx);
		}

    }
    
    synchronized public static MemberDbAdapter getInstance(Context context) {
    	
    	if (instance == null) {
			instance = new MemberDbAdapter(context);
		}
    	
    	return instance;
    }

    synchronized public static MemberDbAdapter getInstance() {
    	
    	return instance;
    }

    public MemberDbAdapter open() throws SQLException {
    	
    	mDb = mDbHelper.getWritableDatabase();

    	return this;
    }

    public void close() {
        mDbHelper.close();
    }


    public long addMember(Member member) {
    	
        ContentValues initialValues = new ContentValues();

        initialValues.put(Member.KEY_EMAIL, member.getEmail());
        initialValues.put(Member.KEY_MEMBER_NAME, member.getMemberName());

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public Cursor queryAllMembers() {

        return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    
    public List<Member> searchAllMembers() {
    	
    	List<Member> memberList = new ArrayList<Member>();
    	
    	Cursor mCursor = queryAllMembers();

        Member Member = null;
    	
    	while (mCursor.moveToNext()) {
		
    		Member = new Member();

        	Member.setEmail(mCursor.getString(mCursor.getColumnIndexOrThrow(Member.KEY_EMAIL)));
        	Member.setMemberName(mCursor.getString(mCursor.getColumnIndexOrThrow(Member.KEY_MEMBER_NAME)));
        	Member.setNo(mCursor.getInt(mCursor.getColumnIndexOrThrow(Member.KEY_ROWID)));

        	memberList.add(Member);
		}

    	mCursor.close();
    	
    	return memberList;
    }

}

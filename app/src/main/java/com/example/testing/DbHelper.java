package com.example.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
   public static final int DATABASE_VERSION=1;
   public static final String DATABASE_NAME="myDatabase";
   public static final String TABLE_NAME="notes";
   public static final String COLUMN_ID="id";
   public static final String TITLE="title";
    public static final String DESCRIPTION="description";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATING_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+" TEXT,"+DESCRIPTION+" TEXT)";
        db.execSQL(CREATING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public long insertData(String title,String desc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( TITLE,title);
        contentValues.put( DESCRIPTION,desc);
        long a=db.insert(TABLE_NAME, null, contentValues);
        return a;
    }
    public ArrayList<Model>getdata()
    {
     ArrayList<Model>list=new ArrayList<>();
     SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=db.rawQuery("SELECT "+"* FROM "+TABLE_NAME,null);
        if (cr.moveToFirst())
        {
            do {
                int id = Integer.parseInt(cr.getString(0));
                String title = cr.getString(1);
                String desc= cr.getString(2);
                list.add(new Model(id,title,desc));
            } while (cr.moveToNext());
        }
        cr.close();
        db.close();
        return list;
    }
    public int deleteData(int id)
    {
     SQLiteDatabase db=this.getWritableDatabase();
     int a=db.delete(TABLE_NAME,
             COLUMN_ID+" = ? ",
             new String[] {String.valueOf((id))});
     db.close();
     return a;
    }
    public boolean chkDatabaseEmpty()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        boolean chk = false;
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(mCursor.moveToFirst()){
            chk=true;
        }else{
            chk = false;
        }
        mCursor.close();
        return chk;
    }
}

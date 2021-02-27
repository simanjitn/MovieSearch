package com.simanjit.moviesearch.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.simanjit.moviesearch.BookmarkList;

import java.util.ArrayList;

public class BookmarkDatabaseAdapter {

    static final String DATABASE_NAME = "bookmarkdatabase.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public  static String getPassword="";
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table BOOKMARK( ID integer primary key autoincrement,IMAGEURL  text,TITLE  text,RELEASED text,RUNTIME text,GENRE text,DIRECTOR text,ACTORS text,LANGUAGE text,BOXOFFICE text,PRODUCTION text); ";
    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;
    public  BookmarkDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Method to openthe Database
    public  BookmarkDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();        return this;
    }
    // Method to close the Database
    public void close()
    {
        db.close();
    }
    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }
    // method to insert a record in Table
    public String insertEntry(String imageurl,String title,String released,String runtime,String genre,String director,String actors,String language,String boxOffice,String production)
    {
        try {
            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("IMAGEURL", imageurl);
            newValues.put("TITLE", title);
            newValues.put("RELEASED", released);
            newValues.put("RUNTIME", runtime);
            newValues.put("GENRE", genre);
            newValues.put("DIRECTOR", director);
            newValues.put("ACTORS", actors);
            newValues.put("LANGUAGE", language);
            newValues.put("BOXOFFICE", boxOffice);
            newValues.put("PRODUCTION", production);
            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert("BOOKMARK", null, newValues);
            System.out.print(result);
            Toast.makeText(context, "Movie Info Saved", Toast.LENGTH_LONG).show();
        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
        }
        return ok;
    }

    public ArrayList<BookmarkList> listBookmark() {
        String sql = "select * from BOOKMARK";
        db=dbHelper.getReadableDatabase();
        ArrayList<BookmarkList> bookmarkLists = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String imageUrl = cursor.getString(1);
                String title = cursor.getString(2);
                String released = cursor.getString(3);
                String runtime = cursor.getString(4);
                String genre = cursor.getString(5);
                String director = cursor.getString(6);
                String actors = cursor.getString(7);
                String language = cursor.getString(8);
                String boxOffice = cursor.getString(9);
                String production = cursor.getString(10);
                bookmarkLists.add(new BookmarkList(id,imageUrl, title, released,runtime, genre,director,actors,language,boxOffice,production));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return bookmarkLists;
    }


}

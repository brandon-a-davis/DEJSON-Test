package com.bdavis.dejsontest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bdavis.dejsontest.jsontest.MainActivity;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "books";
    private static final String DATABASE_NAME = "books.db";
    private static final String COLUMN_NAME_ID = "_id";
    private static final String COLUMN_NAME_BOOK_TITLE = "title";
    private static final String COLUMN_NAME_BOOK_IMAGE_URL = "imageURL";
    private static final String COLUMN_NAME_BOOK_AUTHOR = "author";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + COLUMN_NAME_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_BOOK_TITLE
                + " TEXT,"
                + COLUMN_NAME_BOOK_AUTHOR
                + " TEXT,"
                + COLUMN_NAME_BOOK_IMAGE_URL
                + " TEXT"
                + ");";
        Log.e("TAG", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertBooks(Book[] book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i < book.length; i++) {
            values.put(COLUMN_NAME_BOOK_TITLE, book[i].getTitle());
            values.put(COLUMN_NAME_BOOK_AUTHOR, book[i].getAuthor());
            values.put(COLUMN_NAME_BOOK_IMAGE_URL, book[i].getImageUrl());
            db.insert(TABLE_NAME, null, values);
        }
    }

    public Cursor allRowsToCursor() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return c;
    }

    public String getColumnNameBookTitle() {
        return COLUMN_NAME_BOOK_TITLE;
    }

    public String getColumnNameBookAuthor() {
        return COLUMN_NAME_BOOK_AUTHOR;
    }

    public String getColumnNameBookImageUrl() {
        return COLUMN_NAME_BOOK_IMAGE_URL;
    }
}

package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tektak on 10/31/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "playlistManager";

    //table name
    private static final String TABLE_PLAYLIST = "playlist";

    // Table Columns names
    private static final String KEY_SONGID = "songID";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ABSOLUTEPATH = "absolutePath";
    private static final String KEY_ALBUMID = "albumID";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLAYLIST + "("
                + KEY_SONGID + " LONG PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_ABSOLUTEPATH + " TEXT,"  + KEY_ALBUMID + " LONG"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST);

        // Create tables again
        onCreate(db);
    }

    // Adding new Song to the list
    public void addSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SONGID, song.getSongID());
        values.put(KEY_TITLE, song.getTitle());
        values.put(KEY_ABSOLUTEPATH, song.getAbsolutePath());
        values.put(KEY_ALBUMID, song.getAlbumID());

        // Inserting Row
        db.insert(TABLE_PLAYLIST, null, values);
        db.close(); // Closing database connection
    }
    // Adding new Song to the list
    public void addSongList(List<Song> allSongs) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(Song song: allSongs) {
            ContentValues values = new ContentValues();
            values.put(KEY_SONGID, song.getSongID());
            values.put(KEY_TITLE, song.getTitle());
            values.put(KEY_ABSOLUTEPATH, song.getAbsolutePath());
            values.put(KEY_ALBUMID, song.getAlbumID());

            // Inserting Row
            db.insert(TABLE_PLAYLIST, null, values);
        }
        db.close(); // Closing database connection
    }
    // Reading single song
    public Song getSong(Long songID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYLIST, new String[]{KEY_SONGID,
                        KEY_TITLE, KEY_ABSOLUTEPATH, KEY_ALBUMID}, KEY_SONGID + "=?",
                new String[]{String.valueOf(songID)}, null, null, null, null);
        Song song;
        if (cursor != null && cursor.moveToFirst()) {

            song = new Song(Long.parseLong(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), Long.parseLong(cursor.getString(3)));
            }
        else {
            song = null;
        }
        // return song..return null if it doesnot exist
        return song;
    }

    // Reading all song
    public List<Song> getAllSong() {
        List<Song> allSongs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYLIST;

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Song song = new Song(Long.parseLong(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2),Long.parseLong(cursor.getString(3)));

                allSongs.add(song);
            } while (cursor.moveToNext());
        }
        return allSongs;
    }


    // Deleting singlesong
    public void deletSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYLIST, KEY_SONGID + " = ?",
                new String[] { String.valueOf(song.getSongID()) });
        db.close();
    }
}

package com.example.tektak.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import utils.RecycleViewAdapterParent;
import utils.Song;


import utils.DatabaseHandler;
import utils.RecycleViewAdapterParent;
import utils.Song;

public class ParentMediaSelectorActivity2 extends AppCompatActivity {

    private List<Song> musicData,filteredPlaylist ;
    private RecycleViewAdapterParent recycleViewAdapterParent;
    private RecyclerView musicListRecyclerview;
    private LinearLayoutManager layoutManager;
    private Button addList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_media_selector);
        addList = (Button) findViewById(R.id.addToList);
        filteredPlaylist = new ArrayList<Song>();

        temp();
    }

    public void temp(){
        musicData = getMusicListRecyclerview();
        if(musicData.size() >=1){
            final DatabaseHandler databaseHandler = new DatabaseHandler(this);
            //code for recycle view adapter
            musicListRecyclerview =(RecyclerView) findViewById(R.id.recyclerViewParent) ;
            recycleViewAdapterParent = new RecycleViewAdapterParent(musicData,databaseHandler,this);
            layoutManager = new LinearLayoutManager(this);
            musicListRecyclerview.setLayoutManager(layoutManager);
            musicListRecyclerview.setAdapter(recycleViewAdapterParent);

            //enable buttons if list exists
            addList.setEnabled(true);
            addList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Boolean> states = recycleViewAdapterParent.states;
                    //Iterate throught the states to add song to database
                    for(int i =0;i<states.size();i++){
                        if(states.get(i)==true){
                            filteredPlaylist.add(musicData.get(i));
                        }else{//remove from list if it exists
                            databaseHandler.deletSong(musicData.get(i));
                        }
                    }
                    //finally add the list to database
                    databaseHandler.addSongList(filteredPlaylist);
                    //return to previous acticity
                    Toast.makeText(ParentMediaSelectorActivity2.this, "Added New Playlist", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ParentDashboard.class);
                    startActivity(intent);
                }
            });


        }else{
            Toast.makeText(ParentMediaSelectorActivity2.this, "No files found", Toast.LENGTH_SHORT).show();
        }
    }


    //method to retrieve song info from device
    public List<Song> getMusicListRecyclerview(){
        //query external audio
        List<Song> songList = new ArrayList<>();
        ContentResolver musicResolver = getContentResolver();
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Uri musicUriExternal = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri musicUriInternal = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        Cursor musicCursor1 = musicResolver.query(musicUriExternal, null, selection, null, null);
        Cursor musicCursor2 = musicResolver.query(musicUriInternal, null, selection, null, null);

        //iterate over results if valid
        if(musicCursor1!=null && musicCursor1.moveToFirst()){
            //get columns
            int titleColumn = musicCursor1.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor1.getColumnIndex(MediaStore.Audio.Media._ID);
            int absolutePath = musicCursor1.getColumnIndex(MediaStore.Audio.Media.DATA);
            int albumID = musicCursor1.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);

            //add songs to list
            do {
                long thisId = musicCursor1.getLong(idColumn);
                String thisTitle = musicCursor1.getString(titleColumn);
                String absoluteName = musicCursor1.getString(absolutePath);
                long thisalbumID = musicCursor1.getLong(albumID);

                songList.add(new Song(thisId,thisTitle,absoluteName,thisalbumID));
            }
            while (musicCursor1.moveToNext());
        }

        return songList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parent_media_selector_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

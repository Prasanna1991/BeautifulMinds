package com.example.tektak.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import utils.DatabaseHandler;
import utils.RecycleViewAdapterChild;
import utils.Song;

public class MusicActivity extends AppCompatActivity {
    private List<Song> musicData;
    private RecyclerView musicListRecycleview;
    private LinearLayoutManager layoutManager;
    private RecycleViewAdapterChild recycleViewAdapterChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("color");
        RelativeLayout back = (RelativeLayout) findViewById(R.id.musicDash);
        back.setBackgroundColor(Color.parseColor(stuff));

        musicData = getSonglistFromDatabase();
        if(musicData.size() >=1){
            musicListRecycleview =(RecyclerView) findViewById(R.id.recyclerViewChild) ;
            recycleViewAdapterChild = new RecycleViewAdapterChild(musicData,this);
            layoutManager = new LinearLayoutManager(this);
            musicListRecycleview.setLayoutManager(layoutManager);
            musicListRecycleview.setAdapter(recycleViewAdapterChild);
/*            ArrayAdapter<Image> arrayAdapter = new ArrayAdapter<Image>(
                    this,
                    android.R.layout.simple_list_item_1,
                    images);

            musicListRecycleview.setAdapter(arrayAdapter);*/
        }else{
            Toast.makeText(MusicActivity.this, "No files found", Toast.LENGTH_SHORT).show();
        }




    }


    //method to retrieve song info from device
    public List<Song> getSonglistFromDatabase(){
        List<Song> allSongs;
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        allSongs =databaseHandler.getAllSong();
        return allSongs;
    }


}

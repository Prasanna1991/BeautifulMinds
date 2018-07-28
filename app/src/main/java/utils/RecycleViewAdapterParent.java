package utils;

/**
 * Created by tektak on 10/27/15.
 */
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tektak.myapplication.AndroidBuildingMusicPlayerActivity;
import com.example.tektak.myapplication.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterParent extends RecyclerView.Adapter<RecycleViewAdapterParent.ViewHolder> {
    private List<Song> itemsData;
    public Context context;
    public List<Boolean> states;
    private ViewHolder viewHolder;

    public RecycleViewAdapterParent(List<Song> itemsData,DatabaseHandler databaseHandler, Context context) {
        this.itemsData = itemsData;
        this.context = context;
        //initialize state of eachh checkbox based on database value
        this.states = new ArrayList<>();
        for(int i =0;i<itemsData.size();i++){
         if(databaseHandler.getSong(itemsData.get(i).getSongID()) == null){
             states.add(false);
         }else {
             states.add(true);
         }
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleViewAdapterParent.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        this.viewHolder = viewHolder;
        return viewHolder;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewTitle.setText(itemsData.get(position).getTitle());
        //Create image from album ID
        Uri sArtworkUri = Uri
                .parse("content://media/external/audio/albumart");
        Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, itemsData.get(position).getAlbumID());
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    context.getContentResolver(), albumArtUri);
            bitmap = Bitmap.createScaledBitmap(bitmap, 30, 30, true);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            bitmap = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.flag);
            bitmap = Bitmap.createScaledBitmap(bitmap, 30, 30, true);
        } catch (IOException e) {

            e.printStackTrace();
        }
        //Creating image ends here
        if(bitmap != null) viewHolder.imgViewIcon.setImageBitmap(bitmap);
        //Add state of checkbox based on existing playlist
        if(states.get(position)==true){//the song is already on the list
            viewHolder.checkBox.setChecked(!viewHolder.checkBox.isChecked());
        }
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("clicked", "Is  it going?");
                Intent intent = new Intent(context, AndroidBuildingMusicPlayerActivity.class);
                intent.putExtra("musicList", (Serializable) itemsData);
                intent.putExtra("currentIndex",position);
                context.startActivity(intent);

            }
        });

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the state of the button
              if(viewHolder.checkBox.isChecked())states.set(position,true);
                else states.set(position,false);
            }
        });

    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;
        public TextView txtViewTitle;
        public ImageView imgViewIcon;
        public CheckBox checkBox;

        public ViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.rowText);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.rowImage);
            relativeLayout = (RelativeLayout) itemLayoutView.findViewById(R.id.music_row);
            checkBox = (CheckBox) itemLayoutView.findViewById(R.id.playlistCheckbox);


        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.size();
    }
}


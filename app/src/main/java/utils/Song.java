package utils;

import java.io.Serializable;

/**
 * Created by tektak on 10/30/15.
 */
public class Song implements Serializable {



    private String title;
    private long songID;
    private String absolutePath;
    private long albumID;
    private String artistName;

    public Song(long songID, String title, String absolutePath,long albumID){
        this.songID =songID;
        this.absolutePath =absolutePath;
        this.title = title;
        this.albumID = albumID;

    }

    public long getSongID() {
        return songID;
    }

    public void setSongID(long songID) {
        this.songID = songID;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

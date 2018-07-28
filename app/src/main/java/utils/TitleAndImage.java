package utils;

import java.io.Serializable;

/**
 * Created by tektak on 10/27/15.
 */
public class TitleAndImage implements Serializable {
    public TitleAndImage(String title,int imageName){
     this.imageName = imageName;
        this.title = title;
    }
    public TitleAndImage(){}
    private int imageName;
    private String title;
    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

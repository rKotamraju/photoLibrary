package sample;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class PhotoDetail {
    HashMap tags;
    String caption;
    String filePathLocal;
    Time time;

    public PhotoDetail(String caption, HashMap tags, String filePath, Time time){
        this.caption = caption;
        this.tags = tags;
        this.filePathLocal = filePath;
        this.time = time;
    }


    public String toString(){
        return filePathLocal + " caption: " + caption;
    }

}

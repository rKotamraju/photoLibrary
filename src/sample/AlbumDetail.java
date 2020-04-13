package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class AlbumDetail implements Serializable {
    String name;
    int count;
    ArrayList<PhotoDetail> photos;
    static final long serialVersionUID = 1L;

    public AlbumDetail(String name){
        this.name = name;
    }

    public AlbumDetail(AlbumDetail album){
        this.name = name;
        this.count = count;
        this.photos = photos;
    }

    public String toString(){
        return name;
    }

    public void addPhoto(PhotoDetail photo){

        this.photos.add(photo);
    }
}

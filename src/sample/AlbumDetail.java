package sample;

import java.util.ArrayList;

public class AlbumDetail {
    String name;
    int count;
    ArrayList<PhotoDetail> photos;

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

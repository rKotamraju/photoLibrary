package sample;

import java.util.ArrayList;

public class AlbumDetail {
    String name;
    int count;
    ArrayList<PhotoDetail> photos;

    public AlbumDetail(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public void addPhoto(PhotoDetail photo){
        photos.add(photo);
    }
}

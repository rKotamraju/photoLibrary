package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class AlbumDetail implements Serializable {
    String name;
    int count;
    private ArrayList<PhotoDetail> photos;
    static final long serialVersionUID = 1L;

    public AlbumDetail(String name){
        this.name = name;
        this.photos = new ArrayList<PhotoDetail>();
    }

    public AlbumDetail(AlbumDetail album){
        this.name = name;
        this.photos = new ArrayList<PhotoDetail>();
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }

    public void addPhoto(PhotoDetail photo){
        photos.add(photo);
    }


    public ArrayList<PhotoDetail> getPhotos(){

        ArrayList<PhotoDetail> tempList = new ArrayList<PhotoDetail>();

        PhotoDetail temp = new PhotoDetail("Birthday", "/Image/happy.jpg/", true);
        PhotoDetail temp2 = new PhotoDetail("Cookies", "/Image/puppy.jpeg/", true);
        PhotoDetail temp3 = new PhotoDetail("Cookies and Milk", "/Image/flower.jpg/", true);

        tempList.add(temp);
        tempList.add(temp2);


        //return this.photos;

        return tempList;
    }


    public void removePhoto(PhotoDetail removedPhoto){

        photos.remove(removedPhoto);
    }
}

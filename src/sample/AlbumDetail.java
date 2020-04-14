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

    public AlbumDetail(String searchedText, ArrayList<PhotoDetail> searchedPhotos){
        this.name = searchedText;
        this.photos = searchedPhotos;
    }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String toString(){
        return name;
    }

    public void addPhoto(PhotoDetail photo){
        photos.add(photo);
    }


    public ArrayList<PhotoDetail> getPhotos(){

        //ArrayList<PhotoDetail> tempList = new ArrayList<PhotoDetail>();

//        PhotoDetail temp = new PhotoDetail("Birthday", "/Image/happy.jpg/", true);
//        PhotoDetail temp2 = new PhotoDetail("Cookies", "/Image/puppy.jpeg/", true);
//        PhotoDetail temp3 = new PhotoDetail("Cookies and Milk", "/Image/flower.jpg/", true);
//
//        photos.add(temp);
//        photos.add(temp2);
//        photos.add(temp3);


        return this.photos;
    }


    public void removePhoto(PhotoDetail removedPhoto){

        photos.remove(removedPhoto);
    }
}

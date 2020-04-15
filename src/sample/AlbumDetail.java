package sample;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        if(photos.size() == 0){
            return name+" • "+photos.size();
        }

        String startDate = null;
        try {
            startDate = getFirstPhoto();
        } catch (ParseException e) {
            startDate = "";
        }
        String endDate = null;
        try {
            endDate = getLastPhoto();
        } catch (ParseException e) {
            endDate = "";
        }

        return name+" • "+photos.size()+ " • "+startDate+" to "+endDate;
    }

    private String getFirstPhoto() throws ParseException {

        Date min = new SimpleDateFormat("MM/dd/yyyy").parse(photos.get(0).getDate());
        Date temp;
        for(PhotoDetail p : photos){
            temp = new SimpleDateFormat("MM/dd/yyyy").parse(p.getDate());
            if(temp.before(min)){
                min = temp;
            }
        }

        String date = new SimpleDateFormat("MM/dd/yyyy").format(min);

        return date;
    }

    private String getLastPhoto() throws ParseException {
        Date max = new SimpleDateFormat("MM/dd/yyyy").parse(photos.get(0).getDate());
        Date temp;
        for(PhotoDetail p : photos){
            temp = new SimpleDateFormat("MM/dd/yyyy").parse(p.getDate());
            if(temp.after(max)){
                max = temp;
            }
        }

        String date = new SimpleDateFormat("MM/dd/yyyy").format(max);

        return date;
    }

    public void addPhoto(PhotoDetail photo){
        photos.add(photo);
    }


    public ArrayList<PhotoDetail> getPhotos(){

        return this.photos;
    }


    public void removePhoto(PhotoDetail removedPhoto){

        photos.remove(removedPhoto);
    }
}

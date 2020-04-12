package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDetail implements Serializable{
    private String username;
    private int numAlbums;
    //ArrayList<AlbumDetail> albums;


    public UserDetail(String name){
        this.username = name;
    }

    public String toString(){
        return username;
    }

    public String getUsername(){
        return username;
    }

    /*public void addAlbum(AlbumDetail album){
        albums.add(album);
        System.out.println("Added Album");
    }*/
}

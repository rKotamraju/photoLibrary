package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDetail implements Serializable{
    private String username;
    private int numAlbums;
    static final long serialVersionUID = 1L;

    private ArrayList<AlbumDetail> albums;


    public UserDetail(String name){
        this.username = name;
        this.albums = new ArrayList<AlbumDetail>();
    }

    public String toString(){
        return username;
    }

    public String getUsername(){
        return username;
    }

    public void addAlbum(AlbumDetail newAlbum){
        albums.add(newAlbum);
    }

    public void removeAlbum(AlbumDetail removedAlbum){
        albums.remove(removedAlbum);
    }

    public ArrayList<AlbumDetail> getAlbums(){
        return this.albums;
    }

    public AlbumDetail getAlbum(String name){
        for(AlbumDetail a : albums){
            if(a.name.equals(name)){
                return a;
            }
        }
        return null;
    }
    /*public void addAlbum(AlbumDetail album){
        albums.add(album);
        System.out.println("Added Album");
    }*/
}

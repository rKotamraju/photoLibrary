package sample;

import java.io.Serializable;

public class UserDetail implements Serializable{
    private String username;
    private int numAlbums;

    public UserDetail(String name){
        this.username = name;
    }

    public String toString(){
        return username;
    }

    public String getUsername(){
        return username;
    }
}

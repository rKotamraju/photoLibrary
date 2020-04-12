package sample;

import java.io.Serializable;

public class UserDetail implements Serializable {
    String username;
    int numAlbums;

    public UserDetail(String name){
        this.username = name;
    }

    public String toString(){
        return username;
    }
}

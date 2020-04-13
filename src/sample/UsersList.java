package sample;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class UsersList implements Serializable {

    static UsersList usersList = new UsersList();

    private ArrayList<UserDetail> users = new ArrayList<UserDetail>();
    public static final String storeDir = "dat";
    public static final String storeFile = "users.dat";
    static final long serialVersionUID = 1L;

    private UsersList(){

    }

    public void addUser(UserDetail newUser){
        users.add(newUser);
    }

    public void removeUser(UserDetail removedUser){
        users.remove(removedUser);
    }

    public void listUsers(){
        for(UserDetail u : users){
            System.out.println(u);
        }
    }

    public void writeApp() throws IOException {

        //setting up the stream
        ObjectOutputStream oos = new
                ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));

        //writing object
        oos.writeObject(usersList);
    }

    public static UsersList readApp() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new
                ObjectInputStream( new FileInputStream(storeDir + File.separator + storeFile));

        UsersList usersList = (UsersList) ois.readObject();

        return usersList;
    }

    public boolean checkName(String name){

        for( UserDetail u : users ){
            if (u.getUsername().equals(name)){
                return true;
            }
        }
        return false;
    }

    public UserDetail getUser(String name){

        for( UserDetail u : users ){
            if (u.getUsername().equals(name)){
                return u;
            }
        }

        //will never return this because at this point we have checked if the user has existed
        return null;
    }

    public ArrayList<UserDetail> getUsers(){
        return users;
    }

    public static UsersList getInstance(){
        return usersList;
    }



}

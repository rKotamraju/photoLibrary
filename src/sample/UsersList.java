package sample;

import java.io.*;
import java.util.ArrayList;

public class UsersList implements Serializable {

    private ArrayList<UserDetail> users;
    public static final String storeDir = "dat";
    public static final String storeFile = "users.dat";
    static final long serialVersionUID = 1L;


    public void addUser(UserDetail newUser){
        users.add(newUser);
    }

    public void listUsers(){
        for(UserDetail u : users){
            System.out.println(u);
        }
    }

    public static void writeApp(UsersList userList) throws IOException {

        //setting up the stream
        ObjectOutputStream oos = new
                ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));

        //writing object
        oos.writeObject(userList);
    }

    public static UsersList readApp() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new
                ObjectInputStream( new FileInputStream(storeDir + File.separator + storeFile));

        UsersList usersList = (UsersList) ois.readObject();

        return usersList;
    }



}

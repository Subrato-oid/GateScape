package com.example.gatescape.daos;

import com.example.gatescape.models.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDao {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference userCollection = db.collection("users");

    public void addUser(UserData user){
        if(user != null){
            userCollection.document(user.getRoll_no()).set(user);
        }
    }
}

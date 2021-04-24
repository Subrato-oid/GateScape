package com.example.gatescape.daos;

import com.example.gatescape.models.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RequestDao {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference gpReqCollection = db.collection("gpRequests");
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser fireuser = auth.getCurrentUser();

    public void addRequest(String text){

        UserDao userDao = new UserDao();
        String reason;
        UserData user ;

//        if(fireuser != null) {
//            currentUserId = fireuser.getUid();
//            user = udao.getUserById(currentUserId);
//            Long currentTime = System.currentTimeMillis();
//
//            gpRequest gp = new gpRequest(text, user, currentTime);
//            gpReqCollection.document().set(gp);
//        }
    }
}

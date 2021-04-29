package com.example.gatescape.daos;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gatescape.models.RequestInfo;
import com.example.gatescape.models.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class UserDao {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    CollectionReference userCollection = db.collection("users");
    UserData user;
    FirebaseUser fireUser = mAuth.getCurrentUser();
    private String TAG = "UserDao";

    public void addUser(UserData user , String uid){
        if(user != null){
            userCollection.document(uid).set(user);
        }
    }

    public void getUserById(String text) {

        DocumentReference documentReference = userCollection.document(fireUser.getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(UserData.class);
                if(user != null){
                    Log.i(TAG , "User is not NULL !");
                    Log.i(TAG , "Operation Successful");

                    Long currentTime = System.currentTimeMillis();
                    Boolean approve = false;
                    RequestInfo gp = new RequestInfo(text, user , currentTime , false);
                    CollectionReference ReqCollection = db.collection("Requests");
                    ReqCollection.document().set(gp);
                }
            }

        });
    }

//    public UserData getUserBy(){
//        userCollection.whereEqualTo("email", "subratochowdhury111@gmail.com")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                user = document.toObject(UserData.class);
////                                Log.d(TAG, document.getId() + " => " + user.getEmail());
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
//        return user;
//    }
}
package com.example.gatescape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gatescape.models.RequestInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TeacherActivity3 extends AppCompatActivity {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference ReqCollection = db.collection("Requests");
    private String TAG = "TeacherActivity3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher3);

        TextView T_creator_name = findViewById(R.id.T_creator_name);
        TextView T_creator_branch = findViewById(R.id.T_creator_branch);
        TextView T_creator_sem = findViewById(R.id.T_creator_Sem);
        TextView T_creator_roll_no = findViewById(R.id.T_creator_roll_no);
        TextView T_creation_time = findViewById(R.id.T_creation_time);
        TextView T_creator_reason = findViewById(R.id.T_creator_reason);
        Button T_isApprove = findViewById(R.id.T_approve_button);


        String ReqDocId = getIntent().getStringExtra("T_RequestId");

        DocumentReference docRef = ReqCollection.document(ReqDocId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Boolean approve_check = documentSnapshot.getBoolean("approve");

                if (!approve_check) {
                    T_isApprove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            docRef.update("approve", true);
                            recreate();
                        }
                    });
                }else{
                    T_isApprove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            docRef.update("approve" , false);
                            recreate();
                        }
                    });
                }
            }
        });

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                RequestInfo creator_Info = documentSnapshot.toObject(RequestInfo.class);
                if(creator_Info != null) {
                    T_creator_name.setText("Name : "+creator_Info.getUser().getName());
                    T_creator_branch.setText("Branch : "+creator_Info.getUser().getBranch());
                    T_creator_sem.setText("Semester : "+creator_Info.getUser().getSem());
                    T_creator_roll_no.setText("Roll No. : "+creator_Info.getUser().getRoll_no());
                    T_creation_time.setText("Created at : "+creator_Info.getCreatedAt());
                    T_creator_reason.setText(creator_Info.getReason());
                }
            }
        });
    }
}
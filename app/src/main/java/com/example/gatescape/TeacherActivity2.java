package com.example.gatescape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.gatescape.Adapters.RequestAdapter;
import com.example.gatescape.Adapters.T_RequestAdapter;
import com.example.gatescape.models.RequestInfo;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TeacherActivity2 extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private T_RequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher2);

        setUpRecyclerAdapter();
    }
    private void setUpRecyclerAdapter() {
        Query query = db.collection("Requests").orderBy("createdAt");

        FirestoreRecyclerOptions<RequestInfo> options = new FirestoreRecyclerOptions.Builder<RequestInfo>()
                .setQuery(query , RequestInfo.class)
                .build();

        adapter = new T_RequestAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.T_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemCLickListener(new T_RequestAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                String RequestId = documentSnapshot.getId();
                Intent intent = new Intent(TeacherActivity2.this , TeacherActivity3.class);
                intent.putExtra("T_RequestId" , RequestId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
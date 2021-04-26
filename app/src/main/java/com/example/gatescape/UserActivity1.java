package com.example.gatescape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserActivity1 extends AppCompatActivity {

    Button Apply , History , Status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        Apply = findViewById(R.id.Apply);
        History = findViewById(R.id.History);
        Status = findViewById(R.id.Status);

        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity1.this , UserActivity2.class));
            }
        });

        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(UserActivity1.this , UserActivity_3.class));
            }
        });
    }
}
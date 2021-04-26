package com.example.gatescape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gatescape.daos.RequestDao;
import com.example.gatescape.daos.UserDao;
import com.example.gatescape.models.UserData;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity2 extends AppCompatActivity {

    Button apply_button;
    FirebaseAuth mAuth;
    TextInputLayout Reason_text;
    private String TAG = "UserActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        apply_button = findViewById(R.id.apply_button);
        Reason_text = findViewById(R.id.Reason_text);

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String reason = Reason_text.getEditText().getText().toString();
                RequestDao requestDao = new RequestDao();
                requestDao.addRequest(reason);
                finish();

            }
        });
    }
}
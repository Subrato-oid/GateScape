package com.example.gatescape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gatescape.daos.RequestDao;
import com.example.gatescape.daos.UserDao;
import com.example.gatescape.models.UserData;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

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

                String reason = Objects.requireNonNull(Reason_text.getEditText()).getText().toString();
                RequestDao requestDao = new RequestDao();
                if(!reason.isEmpty()) {
                    requestDao.addRequest(reason);
                    Notification();
                    finish();
                }
                else {
                    Reason_text.setError("Reason can't be empty");
                    recreate();
                }
            }
        });
    }

    private void Notification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("n" , "n" , NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this , "n")
                .setContentText("GateScape")
                .setSmallIcon(R.drawable.ic_user)
                .setAutoCancel(true)
                .setContentText("New Gate Pass Request Added");

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999 , builder.build());
    }
}
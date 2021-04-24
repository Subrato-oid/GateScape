package com.example.gatescape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.gatescape.models.UserData;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    Button  Login , SignUp , ForgetPass;
    TextInputLayout Username , Password;
    TextView welcome_text , continue_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = findViewById(R.id.LoginButton1);
        SignUp = findViewById(R.id.SignUpButton1);
        ForgetPass = findViewById(R.id.ForgotPassword);
        Username = findViewById(R.id.username1);
        Password = findViewById(R.id.Password1);
        welcome_text = findViewById(R.id.welcomeText1);
        continue_text =findViewById(R.id.continue_text1);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext() , SignUpActivity.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this , Pair.create(welcome_text , "logo_text"),
                Pair.create(continue_text , "continue_text"), Pair.create(Username , "username_text"), Pair.create(ForgetPass , "middle_text") ,
                Pair.create(Password , "password_text") , Pair.create(Login , "login_singUp"), Pair.create(SignUp , "signUp_login"));

                startActivity(intent , options.toBundle());
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateRoll_no() | !validatePassword()){
                    return;
                }else{
                    isUser();
                }
            }
        });
    }

    private void isUser() {

        String Roll_No = Username.getEditText().getText().toString();
        String Enteredpassword = Password.getEditText().getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userCollection = db.collection("users");

        userCollection.whereEqualTo("roll_no" , Roll_No).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){

                        UserData dbUserInfo = document.toObject(UserData.class);

                        String dbpassword = dbUserInfo.getPassword();

                        if(dbpassword.equals(Enteredpassword)){
                            startActivity(new Intent(LoginActivity.this , UserActivity1.class));
                        }else{
                            Password.setError("Wrong password");
                        }
                    }
                }else{
                    Username.setError("Roll No. not found");
                    Log.d(TAG , "Error getting Documents" , task.getException());
                }
            }
        });
    }

    private Boolean validateRoll_no(){

        String val = Username.getEditText().getText().toString();

        if(val.isEmpty()){
            Username.setError("Field cannot be empty");
            return false;
        }else{
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){

        String val = Password.getEditText().getText().toString();

        if(val.isEmpty()){
            Password.setError("Field cannot be empty");
            return false;
        }else{
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }
}
package com.example.gatescape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.gatescape.daos.UserDao;
import com.example.gatescape.models.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    Button Login, SignUp;
    TextInputLayout name, branch, sem, roll_no, email, phone_no, password;

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    CollectionReference userCollection;
    private String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Login = findViewById(R.id.LoginButton);
        SignUp = findViewById(R.id.SignUpButton);
        name = findViewById(R.id.username);
        branch = findViewById(R.id.Branch);
        sem = findViewById(R.id.Sem);
        roll_no = findViewById(R.id.roll_no);
        email = findViewById(R.id.Email);
        phone_no = findViewById(R.id.phone_no);
        password = findViewById(R.id.Password);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateName() | !validateBranch() | !validateSem() | !validateRoll_no() | !validateEmail() | !validatePhone_no() | !validatePassword()){
                    return;
                }else{
                    CreateUserAuth();
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this , LoginActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void CreateUserAuth (){

        String username = name.getEditText().getText().toString();
        String user_branch = branch.getEditText().getText().toString();
        String user_sem = sem.getEditText().getText().toString();
        String user_roll_no = roll_no.getEditText().getText().toString();
        String user_email = email.getEditText().getText().toString();
        String user_phone_no = phone_no.getEditText().getText().toString();
        String user_password = password.getEditText().getText().toString();

        mAuth.createUserWithEmailAndPassword(user_email, user_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            UserData userData = new UserData(username, user_branch ,user_sem, user_roll_no, user_email, user_phone_no, user_password);
                            UserDao userDao = new UserDao();
                            userDao.addUser(userData , mAuth.getCurrentUser().getUid());

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser firebaseUser) {
        if(firebaseUser != null){
            startActivity(new Intent(SignUpActivity.this , LoginActivity.class));
        }else{
            return;
        }
    }

    //Validate function for all the field , to ensure that they are not empty before SignUp.
    private Boolean validateName() {
        String val = name.getEditText().getText().toString();

        if(val.isEmpty()){
            name.setError("Field cannot be empty");
            return (false);
        }
        else{
            name.setError(null);
            return true;
        }
    }

    private Boolean validateBranch() {
        String val = branch.getEditText().getText().toString();

        if(val.isEmpty()){
            branch.setError("Field cannot be empty");
            return (false);
        }
        else{
            branch.setError(null);
            return true;
        }
    }

    private Boolean validateSem() {
        String val = sem.getEditText().getText().toString();

        if(val.isEmpty()){
            sem.setError("Field cannot be empty");
            return (false);
        }
        else{
            sem.setError(null);
            return true;
        }
    }

    private Boolean validateRoll_no() {
        String val = roll_no.getEditText().getText().toString();

        if(val.isEmpty()){
            roll_no.setError("Field cannot be empty");
            return (false);
        }
        else{
            roll_no.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();

        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            return (false);
        }
        else{
            email.setError(null);
            return true;
        }
    }

    private Boolean validatePhone_no() {
        String val = phone_no.getEditText().getText().toString();

        if(val.isEmpty()){
            phone_no.setError("Field cannot be empty");
            return (false);
        }
        else{
            phone_no.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return (false);
        }
        else{
            password.setError(null);
            return true;
        }
    }
}
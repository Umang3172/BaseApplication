package com.example.baseapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserActivity extends AppCompatActivity {
    EditText editLoginEmail, editLoginPassword;
    Button btnLogin;
    FirebaseAuth firebaseAuth;
    TextView resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        editLoginEmail = findViewById(R.id.editTextTextEmailAddressLogin);
        editLoginPassword = findViewById(R.id.editTextTextPasswordLogin);
        btnLogin = findViewById(R.id.btn_login);
        resetPassword = findViewById(R.id.text_reset_pass);

        firebaseAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailId = new EditText(view.getContext());
                AlertDialog.Builder changePassword = new AlertDialog.Builder(view.getContext());
                changePassword.setTitle("Reset Password");
                changePassword.setMessage("Enter your email to reset the password");
                changePassword.setView(emailId);
                changePassword.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = emailId.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(view.getContext(),"Password reset link sent to given email successfully.",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(view.getContext(),"Error occurred in sending reset link: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                changePassword.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                changePassword.create().show();
            }
        });

    }

    private void userLogin() {
        String email = editLoginEmail.getText().toString();
        String password = editLoginPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            editLoginEmail.setError("Email required!");
            editLoginEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            editLoginPassword.setError("Password required!");
            editLoginPassword.requestFocus();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginUserActivity.this, "Login successful :)", Toast.LENGTH_SHORT).show();
                                SharedPreferences.Editor editor = getApplicationContext()
                                        .getSharedPreferences("emailId",MODE_PRIVATE)
                                        .edit();

                                editor.putString("email",email);
                                editor.apply();
                                startActivity(new Intent(LoginUserActivity.this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            }
                            else{
                                Toast.makeText(LoginUserActivity.this,"Something went wrong: "
                                        +task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}
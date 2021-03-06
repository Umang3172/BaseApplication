package com.example.baseapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class RegisterUserActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnRegister;
    TextView textLogIn;
    SignInButton btnGoogleLogIn;

    FirebaseAuth firebaseAuth;

    private GoogleSignInClient mGoogleSignInClient;
    public static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        editEmail = findViewById(R.id.editTextTextEmailAddress);
        editPassword = findViewById(R.id.editTextTextPassword);
        btnRegister = findViewById(R.id.btn_register);
        textLogIn = findViewById(R.id.text_log_in);
        btnGoogleLogIn = findViewById(R.id.btn_google_login);

        firebaseAuth = FirebaseAuth.getInstance();
        requestGoogleSignIn();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        btnGoogleLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        textLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterUserActivity.this, LoginUserActivity.class));
            }
        });
    }

    private void requestGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id1))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(RegisterUserActivity.this,gso);
    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount user = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(user.getIdToken());
                SharedPreferences.Editor editor = getApplicationContext()
                        .getSharedPreferences("MyPrefs",MODE_PRIVATE)
                        .edit();
                editor.putString("userName",user.getDisplayName());
                editor.putString("userEmail",user.getEmail());
                editor.putString("userProfile",user.getPhotoUrl().toString());
                editor.apply();
            } catch (ApiException e) {
                e.printStackTrace();
                Log.d("login failed",e.toString());
            }
        }   ;
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterUserActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterUserActivity.this,MainActivity.class).putExtra("val",1));
                        }
                        else{
                            Toast.makeText(RegisterUserActivity.this,"Something went wrong: "
                                    +task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void registerUser() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            editEmail.setError("Email required!");
            editEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            editPassword.setError("Password required!");
            editPassword.requestFocus();
        }
        else{
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterUserActivity.this, "User registration successful :)", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterUserActivity.this,LoginUserActivity.class));
                            }
                            else{
                                Toast.makeText(RegisterUserActivity.this, "Registration failed: "+task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
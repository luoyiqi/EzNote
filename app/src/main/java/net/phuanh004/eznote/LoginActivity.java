package net.phuanh004.eznote;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView tvSignUp;
    EditText etEmail,etPass;
    Button btnLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);

//        mAuth.signOut();
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this,NotesActivity.class);
            startActivity(intent);
        }
//        Log.d("^^^", "onCreate: "+ mAuth.getCurrentUser());



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = etEmail.getText().toString();
                String Pass = etPass.getText().toString();
                if(Pass.equals("") && User.equals("")){
                    Toast.makeText(LoginActivity.this, "Enter email address and password",
                            Toast.LENGTH_SHORT).show();

                }
                else if(Pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Invalid password",
                            Toast.LENGTH_SHORT).show();

                }
                else if(User.equals("")){
                    Toast.makeText(LoginActivity.this, "Invalid email address",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Login();
                }
            }
        });



        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    private void Login() {
        String User = etEmail.getText().toString();
        String Pass = etPass.getText().toString();
        mAuth.signInWithEmailAndPassword(User, Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "OK",
                            Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,NotesActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Incorrect email or password",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }
}


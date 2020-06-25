package com.example.firebaseappone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private EditText email;
    private  EditText password;
    private Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=(EditText)findViewById(R.id.emailS);
        password=(EditText)findViewById(R.id.passwordS);
        SignUp=(Button)findViewById(R.id.signUpS);
        mFirebaseAuth=FirebaseAuth.getInstance();
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId=email.getText().toString();
                String pwd=password.getText().toString();
                if(emailId.isEmpty() || pwd.isEmpty())
                {
                    email.setError("Fill Info");
                }
                else
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailId,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUpActivity.this, "Successfully Created ", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignUpActivity.this, "Something went wrong! Try again ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
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

public class MainActivity extends AppCompatActivity {

  private FirebaseAuth mFirebaseAuth;
  private EditText email;
  private  EditText password;
  private Button SignIn;
  private Button SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        SignIn=(Button)findViewById(R.id.signIn);
        SignUp=(Button)findViewById(R.id.signUp);
        mFirebaseAuth=FirebaseAuth.getInstance();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId=email.getText().toString();
                String pwd=password.getText().toString();
                if(emailId.isEmpty() || pwd.isEmpty())
                {
                    email.setError("Fill Info");
                    password.setError("Fill Info");
                }
                else
                {
                    mFirebaseAuth.signInWithEmailAndPassword(emailId,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Successfully Created ", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Try Again ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
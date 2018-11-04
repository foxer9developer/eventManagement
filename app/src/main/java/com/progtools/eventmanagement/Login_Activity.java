package com.progtools.eventmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference Users;

    private EditText Name;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        database=FirebaseDatabase.getInstance();
        Users=database.getReference("Users");

        Name=(EditText) findViewById(R.id.mUsername);
        Password=(EditText) findViewById(R.id.mPassword);
        Login=(Button) findViewById(R.id.loginbtn);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate( final String username, final String password){

//        Users.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.child(username).exists()){
//                    if(!username.isEmpty()){
//                        String user = dataSnapshot.child(username).getValue(String.class);
//                        String pass = dataSnapshot.child(password).getValue(String.class);
//                        if(pass.equals(password)){
//                            Toast.makeText(Login_Activity.this, "Successfully logged IN", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//                            Toast.makeText(Login_Activity.this, "wrong password input", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else{
//                        Toast.makeText(Login_Activity.this, "Unregistered Username", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(Login_Activity.this, "Retry Please, Network problem", Toast.LENGTH_SHORT).show();
//            }
//        });

        FirebaseAuth user = FirebaseAuth.getInstance();
        user.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login_Activity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login_Activity.this, MainActivity.class);
                     startActivity(intent);
                } else {
                    Toast.makeText(Login_Activity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

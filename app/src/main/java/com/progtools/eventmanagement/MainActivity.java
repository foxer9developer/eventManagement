 package com.progtools.eventmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     private FirebaseAuth firebaseAuth;
     private Button buttonLogout;


    private RecyclerView recyclerView;
    String []Items={"item 0", "item 1", "item 2", "item 3", "item 4", "item 5", "item 6", "item 7", "item 8", "item 9"};
//    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this, Items ));

 //       mfirestore= FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View view){
        if (view==buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login_Activity.class));
        }
    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }
}

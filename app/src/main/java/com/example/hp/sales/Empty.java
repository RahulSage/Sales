package com.example.hp.sales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Empty extends AppCompatActivity implements View.OnClickListener{
    private Button b;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,Login.class));


        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        b = (Button)findViewById(R.id.button);
        b.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}

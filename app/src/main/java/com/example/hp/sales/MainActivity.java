package com.example.hp.sales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.medi);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imageView.startAnimation(animation);
        final Intent intent = new Intent(this,Login.class);
        Thread time = new Thread(){
            public void run()
            {
                try{
                    sleep(3000);

                }  catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        time.start();


    }
}

package com.example.tugasanimasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    ImageView wave;
    TextView textLogo;

    int index;
    Handler handler = new Handler();
    CharSequence charSequence;
    long delay = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.music_logo);
        wave = findViewById(R.id.music_wave);
        textLogo = findViewById(R.id.text_logo);


        ObjectAnimator logoAnimator = ObjectAnimator.ofFloat(logo, "rotation", 10);
        logoAnimator.setDuration(500);
        logoAnimator.setRepeatCount(ValueAnimator.INFINITE);
        logoAnimator.setRepeatMode(ValueAnimator.REVERSE);
        logoAnimator.start();

        ObjectAnimator waveAnimator = ObjectAnimator.ofPropertyValuesHolder(
                wave,
                PropertyValuesHolder.ofFloat("scaleX", 1.3f),
                PropertyValuesHolder.ofFloat("x", 300,600)
        );
        waveAnimator.setDuration(2000);
        waveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        waveAnimator.setRepeatMode(ValueAnimator.REVERSE);
        waveAnimator.start();

        animatText("MUSIC");

        //Initialize handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Redirect to main page
                startActivity(new Intent(MainActivity.this,
                        MainActivity2.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                //finis activity
                finish();
            }
        }, 4000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //setText
            textLogo.setText(charSequence.subSequence(0,index++));
            if(index <= charSequence.length()){
                //When index is equal to text length run handler
                handler.postDelayed(runnable, delay);
            }
        }
    };

    //Animated text
    public void animatText(CharSequence cs){
        //set text
        charSequence = cs;
        //clear index
        index = 0;
        //clear text
        textLogo.setText("");
        //remove call back
        handler.removeCallbacks(runnable);
        //run handler
        handler.postDelayed(runnable,delay);
    }

}
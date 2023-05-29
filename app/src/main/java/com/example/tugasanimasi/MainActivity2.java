package com.example.tugasanimasi;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    //Durasi untuk mengatur waktu dari animasi
    private static final int ANIMATION_DURATION = 8000;
    private static final int ANIMATION_DURATION2 = 4000;
    //Delay mengatur jeda antat gambar
    private static final long ANIMATION_DELAY = 500;

    ImageView playButton;
    ImageView loveButton;
    ImageView backButton;
    ImageView markerPlay;
    ImageView nextImage;
    ImageView imageCover;
    TextView textMove;

    Boolean playMusic = false;
    Boolean loveMusic = false;

    float position;
    int count = 0;
    ObjectAnimator objectAnimator;
    ObjectAnimator textAnimator1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        playButton = findViewById(R.id.play_button);
        loveButton = findViewById(R.id.love);
        backButton = findViewById(R.id.back_button);
        markerPlay = findViewById(R.id.cirlce_play);
        nextImage = findViewById(R.id.next_image);
        imageCover = findViewById(R.id.image_detail_png);
        textMove = findViewById(R.id.text_move);

        //Play music
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = markerPlay.getX();
                if (position == 880){
                    Toast.makeText(MainActivity2.this, "Music Completed",
                            Toast.LENGTH_SHORT).show();
                    markerPlay.setX(150);
                    playButton.setImageResource(R.drawable.play);
                    playMusic = false;
                    textAnimator1.pause();
                    textMove.setVisibility(View.INVISIBLE);
                }else {
                    if (playMusic == false) {
                        objectAnimator = ObjectAnimator.ofFloat(markerPlay, "x", position, 880);
                        objectAnimator.setDuration(ANIMATION_DURATION);
                        objectAnimator.start();
                        playButton.setImageResource(R.drawable.pause);
                        playMusic = true;

                        textMove.setVisibility(View.VISIBLE);
                        textAnimator1 = ObjectAnimator.ofFloat(textMove, "x", -100, 900);
                        textAnimator1.setDuration(ANIMATION_DURATION2);
                        textAnimator1.setRepeatCount(ValueAnimator.INFINITE);
                        textAnimator1.setRepeatMode(ValueAnimator.RESTART);

                        textAnimator1.start();
                    } else {
                        objectAnimator.pause();
                        playButton.setImageResource(R.drawable.play);
                        playMusic = false;

                        textAnimator1.pause();
                        textMove.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        //Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markerPlay.setX(150);
                playButton.setImageResource(R.drawable.play);
                playMusic = false;
            }
        });

        //Love music
        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loveMusic == false){
                    loveButton.setImageResource(R.drawable.heart);
                    loveButton.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.zoom_fade_in
                    ));

                    loveButton.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.bounce
                    ));
                    loveButton.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.bounce_back
                    ));
                    loveMusic = true;
                }
                else{
                    loveButton.setImageResource(R.drawable.hearts);
                    loveButton.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.bounce
                    ));
                    loveButton.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.bounce_back
                    ));
                    loveMusic = false;
                }
            }
        });

        //Next Image
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0) {
                    imageCover.setImageResource(R.drawable.zombie1);
                    imageCover.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.swipe
                    ));

                    count = count + 1;
                }else if(count == 1){
                    imageCover.setImageResource(R.drawable.zombie2);
                    imageCover.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.swipe
                    ));

                    count = count + 1;
                }else if(count==2){
                    imageCover.setImageResource(R.drawable.zombie3);
                    imageCover.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.swipe
                    ));

                    count = count + 1;
                }else if(count==3){
                    imageCover.setImageResource(R.drawable.zombie4);
                    imageCover.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.swipe
                    ));

                    count = count + 1;
                }else {
                    imageCover.setImageResource(R.drawable.zombie5);
                    imageCover.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.swipe
                    ));

                    count = 0;
                }

            }
        });
    }
}
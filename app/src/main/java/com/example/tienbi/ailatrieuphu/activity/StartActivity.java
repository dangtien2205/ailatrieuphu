package com.example.tienbi.ailatrieuphu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.tienbi.ailatrieuphu.MusicPlayer;
import com.example.tienbi.ailatrieuphu.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);

        addControls();
    }

    private void addControls() {
        findViewById(R.id.btnExit).setOnClickListener(this);
        findViewById(R.id.btnPlay).setOnClickListener(this);

        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.start_to_left);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.start_to_left);
        Animation animation3 = AnimationUtils.loadAnimation(this,R.anim.start_to_left);
        Animation animation4 = AnimationUtils.loadAnimation(this,R.anim.start_to_left);

        animation1.setDuration(800);
        animation2.setDuration(1200);
        animation3.setDuration(1600);
        animation4.setDuration(2000);

        findViewById(R.id.btnPlay).startAnimation(animation1);
        findViewById(R.id.btnRate).startAnimation(animation3);
        findViewById(R.id.btnShare).startAnimation(animation4);
        MusicPlayer.getMusicPlayer().setup(R.raw.bgmusic);
        MusicPlayer.getMusicPlayer().play(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnExit:
                finish();
                break;
            case R.id.btnPlay:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}

package com.example.tienbi.ailatrieuphu;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by TienBi on 14/10/2016.
 */
public class MusicPlayer {
    public static final int PLAYER_IDLE=-1;
    public static final int PLAYER_PLAY=1;
    public static final int PLAYER_PAUSE=2;
    public static final int PLAYER_STOP =3;
    private MediaPlayer mediaPlayer;
    private int state;
    private static MusicPlayer instance;

    public static MusicPlayer getMusicPlayer(){
        if (instance==null)
            instance=new MusicPlayer();
        return instance;
    }

    public MusicPlayer() {
        state=PLAYER_IDLE;
    }

    public void setup(int n) {
        mediaPlayer = MediaPlayer.create(App.getContext(),n);
//        try {
//            state=PLAYER_IDLE;
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setDataSource(path);
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public void play(boolean n){
        if(state==PLAYER_IDLE){
            mediaPlayer.start();
            mediaPlayer.setLooping(n);
            state=PLAYER_PLAY;
        }
    }
    public void stop(){
        if(state==PLAYER_PLAY || state==PLAYER_PAUSE) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
            state=PLAYER_IDLE;
        }
    }
    public void pause(){
        if(state==PLAYER_PLAY ) {
            mediaPlayer.pause();
            state=PLAYER_PAUSE;
        }
    }

    public int getState() {
        return state;
    }

    public void resume(){
        if(state==PLAYER_PAUSE) {
            mediaPlayer.start();
            state=PLAYER_PLAY;
        }
    }

    public void setFinish(final int n){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
                setup(n);
                play(true);
            }
        });
    }
}

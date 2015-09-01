package com.example.regin.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Regin on 15/5/28.
 */
public class AudioPlayer {
    private MediaPlayer mediaPlayer;
    public void stop(){
        if(mediaPlayer!=null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public void pause(){
        mediaPlayer.pause();
    }
    public void start(){
        mediaPlayer.start();
    }
    public void play(Context c){
        stop();
//        mediaPlayer = MediaPlayer.create(c,R.raw.test);
        mediaPlayer = MediaPlayer.create(c,R.raw.one_small_step);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });
        mediaPlayer.start();
    }
}

package com.example.regin.videoplayer;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.VideoView;

import java.io.File;


public class VideoPlayerActivity extends FragmentActivity {

//    private VideoView videoView;
//    android.widget.MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_player);
//        videoView = (VideoView)findViewById(R.id.video_view);
//        mediaController = new android.widget.MediaController(this);
//        File file = new File("/mnt/sdcard/1566.mp4");
////        Uri uri = Uri.parse("android.resource://"+
////                "com.example.regin.videoplayer/raw/apollp_17_stroll");
//        System.out.println(""+file.getAbsolutePath());
//
//            try {
////            videoView.setVideoURI(uri);/Users/Regin/Downloads
//                videoView.setVideoPath(file.getAbsolutePath());
//                videoView.setMediaController(mediaController);
//                mediaController.setMediaPlayer(videoView);
//
//                videoView.requestFocus();
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

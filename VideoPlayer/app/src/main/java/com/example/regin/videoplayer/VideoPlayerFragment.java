package com.example.regin.videoplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import java.io.File;



public class VideoPlayerFragment extends Fragment {




    private VideoView videoView;
    android.widget.MediaController mediaController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video_player, container, false);
        videoView = (VideoView)v.findViewById(R.id.video_view);
        mediaController = new android.widget.MediaController(getActivity());
        File file = new File("/mnt/sdcard/1566.mp4");
//        Uri uri = Uri.parse("android.resource://"+
//                "com.example.regin.videoplayer/raw/apollp_17_stroll");
     //   System.out.println(""+file.getAbsolutePath());

        try {
//            videoView.setVideoURI(uri);/Users/Regin/Downloads
            videoView.setVideoPath(file.getAbsolutePath());
            videoView.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoView);

            videoView.requestFocus();

        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }






}

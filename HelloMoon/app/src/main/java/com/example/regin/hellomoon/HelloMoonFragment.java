package com.example.regin.hellomoon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Regin on 15/5/28.
 */
public class HelloMoonFragment extends Fragment {
    private AudioPlayer player = new AudioPlayer();
    private Button playbutton;
    private Button stopbutton;
    private Button pausebutton;
    private Button startbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_hello_moon,container,false);
        playbutton = (Button)v.findViewById(R.id.hellomoon_playbutton);
             playbutton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     player.play(getActivity());
                 }
             });
//        playbutton.setOnClickListener();
        stopbutton = (Button)v.findViewById(R.id.hellomoon_stopbutton);
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
            }
        });

        pausebutton = (Button)v.findViewById(R.id.hellomoon_pausebutton);
        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();

            }
        });
        startbutton = (Button)v.findViewById(R.id.hellomoon_startbutton);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }

}

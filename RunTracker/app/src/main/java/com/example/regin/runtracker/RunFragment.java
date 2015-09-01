package com.example.regin.runtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Regin on 15/6/17.
 */
public class RunFragment extends Fragment {
    private TextView startedTextView;
    private TextView latitudeTextView;
    private TextView longtitudeTextView;
    private TextView altitudeTextView;
    private TextView durationTextView;
    private Button startButton;
    private Button stopButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_run,container,false);
        startedTextView = (TextView)v.findViewById(R.id.run_startedTextView);
        latitudeTextView = (TextView)v.findViewById(R.id.run_latitudeTextView);
        longtitudeTextView = (TextView)v.findViewById(R.id.run_longitudeTextView);
        altitudeTextView = (TextView)v.findViewById(R.id.run_altitudeTextView);
        durationTextView = (TextView)v.findViewById(R.id.run_durationTextView);

        startButton = (Button)v.findViewById(R.id.run_startButton);
        stopButton = (Button)v.findViewById(R.id.run_stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        return v;
    }
}

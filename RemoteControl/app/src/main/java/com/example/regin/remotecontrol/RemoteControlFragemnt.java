package com.example.regin.remotecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Regin on 15/6/13.
 */
public class RemoteControlFragemnt extends Fragment {
    private TextView selectedTextView;
    private TextView workingTextview;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_remote_control,container,false);
        workingTextview = (TextView)v.
                findViewById(R.id.fragment_remote_control_workingTextView);
        selectedTextView = (TextView)v.
                findViewById(R.id.fragment_remote_control_selectedTextView);
        View.OnClickListener numOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)v;
                String working = workingTextview.getText().toString();
                String text = textView.getText().toString();
                if(working.equals("0")){
                    workingTextview.setText(text);
                }else {
                    workingTextview.setText(working + text);
                }
            }
        };
//        Button buttonzero = (Button)v.findViewById(R.id.fragment_remote_control_zero);
//        buttonzero.setOnClickListener(numOnclickListener);
//        Button buttonone = (Button)v.findViewById(R.id.fragment_remote_control_one);
//        buttonone.setOnClickListener(numOnclickListener);
        TableLayout tableLayout = (TableLayout)v.findViewById(R.id.fragment_remote_control_tableLayout);
        int num = 1;

        for( int i = 2;i < tableLayout.getChildCount()-1;i++){
            TableRow tableRow = (TableRow)tableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++ ){
                Button button = (Button)tableRow.getChildAt(j);
                button.setText(""+num);
                button.setOnClickListener(numOnclickListener);
                num++;
            }
        }
        TableRow bottomRow = (TableRow)tableLayout.getChildAt(tableLayout.getChildCount()-1);

//        Button buttondelete = (Button)bottomRow.getChildAt(0);
        Button buttondelete = (Button)v.findViewById(R.id.delete);
        buttondelete.setText("Delete");
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextview.setText("0");
            }
        });
//        Button buttonzero = (Button)bottomRow.getChildAt(1);
        Button buttonzero = (Button)v.findViewById(R.id.zero);
        buttonzero.setText("0");
        buttonzero.setOnClickListener(numOnclickListener);
//        Button buttonenter = (Button)v.findViewById(R.id.fragment_remote_control_enter);
//        Button buttonenter = (Button)bottomRow.getChildAt(2);
        Button buttonenter = (Button)v.findViewById(R.id.enter);
        buttonenter.setText("Enter");
        buttonenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence working = workingTextview.getText();
                if(working.length() > 0){
                     selectedTextView.setText(working);
                }
                workingTextview.setText("0");
            }
        });
        return v;
    }
}

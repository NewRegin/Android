package com.example.regin.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Regin on 15/5/27.
 */
public class TimePickerFragment extends DialogFragment {
    @NonNull
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";
    private Date date;
    public static TimePickerFragment newInstance(Date date){
        Bundle bd = new Bundle();
        bd.putSerializable(EXTRA_DATE,date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bd);
        return fragment;
    }
    private void sendResult(int resultCode){
        if(getTargetFragment() == null){
            return;
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DATE,date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(),resultCode, i);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        date = (Date)getArguments().getSerializable(EXTRA_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        int minute = calendar.get(calendar.MINUTE);
        final int second = calendar.get(calendar.SECOND);

//        return super.onCreateDialog(savedInstanceState);
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.timepicker,null);

//        DatePicker dp = new DatePicker(getActivity());

        TimePicker timePicker = (TimePicker)v
                .findViewById(R.id.time_picker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                date = new Date(year,month,day,hourOfDay,minute,second);
                getArguments().putSerializable(EXTRA_DATE, date);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
//                .setView(dp)
                .setTitle(R.string.time_picker_title)
//                .setPositiveButton(android.R.string.ok,null)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }
}

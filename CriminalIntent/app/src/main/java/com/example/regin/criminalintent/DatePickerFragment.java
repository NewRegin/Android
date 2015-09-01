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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Regin on 15/5/27.
 */
public class DatePickerFragment extends DialogFragment {
    @NonNull
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";
    private Date date;
    public static DatePickerFragment newInstance(Date date){
        Bundle bd = new Bundle();
        bd.putSerializable(EXTRA_DATE,date);

        DatePickerFragment fragment = new DatePickerFragment();
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
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
         final int hour = calendar.get(calendar.HOUR_OF_DAY);
         final int minute = calendar.get(calendar.MINUTE);
         final int second = calendar.get(calendar.SECOND);

//        return super.onCreateDialog(savedInstanceState);
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_date,null);
//        DatePicker dp = new DatePicker(getActivity());

        DatePicker datePicker = (DatePicker)v
                .findViewById(R.id.date_datepicker);
        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
                date = new GregorianCalendar(year, month, dayOfMonth,hour,minute,second).getTime();
              //  date = new Date(year,month,dayOfMonth,hour,minute,second);
                getArguments().putSerializable(EXTRA_DATE,date);
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
//                .setView(dp)
                .setTitle(R.string.date_picker_title)
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

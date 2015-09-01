package com.example.regin.criminalintent;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.DialogPreference;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import javax.xml.transform.Result;

public class CrimeFragenment extends android.support.v4.app.Fragment {

    public static final String EXTRA_CRIME_ID =
            "com.example.regin.criminalintent.crime_id";
    private Crime crime;
    private EditText editText;
    private CheckBox solvedcheckBox;
    private Button datebutton2;
    private Button datebutton1;
    private ImageButton imageButton;
    private ImageView imageView;
    private Button supectButton;
    private static final String DIALOG_DATE = "date";
    private static final String DIALOG_TIME = "time";
    private static final String DIALOG_IMAGE = "image";
    private static final int RESULT_DATE = 0;
    private static final int RESULT_TIME = 1;
    private static final int RESULT_PHOTO = 2;
    private static final int REQUEST_SUPECT = 3;
   // ActionBar actionBar = getActionBar();
    // private OnFragmentInteractionListener mListener;




    public CrimeFragenment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        crime = new Crime();
//        UUID id = (UUID)getActivity().getIntent()
//                .getSerializableExtra(EXTRA_CRIME_ID);
        UUID id = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
        crime = CrimeLab.getsCrimeLab(getActivity()).getCrime(id);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(NavUtils.getParentActivityName(getActivity())!=null)
                     NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
             return super.onOptionsItemSelected(item);
        }
    }

    public static CrimeFragenment newInstance(UUID id)
    {
        Bundle bd = new Bundle();
        bd.putSerializable(EXTRA_CRIME_ID, id);
        CrimeFragenment crimeFragenment = new CrimeFragenment();
        crimeFragenment.setArguments(bd);
        return crimeFragenment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  crime = new Crime();
      View v = inflater.inflate(R.layout.fragment_crime_fragenment, container, false);
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
//            if(NavUtils.getParentActivityName(getActivity())!=null)
//                getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//        }
      editText = (EditText)v.findViewById(R.id.crime_title);

      editText.setText(crime.getmTitle());

      editText.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }


          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {
              crime.setmTitle(s.toString());
            //  Log.e("TAG",crime.getmTitle());
          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
        datebutton1 = (Button)v.findViewById(R.id.crime_date);
        datebutton2 = (Button)v.findViewById(R.id.crime_date_time);
        String st = crime.getDate().toString();

        datebutton1.setText(st.subSequence(0,10)+" "+st.substring(30));
        datebutton2.setText(st.subSequence(11,20));
//        datebutton.setEnabled(false);
        datebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(crime.getDate());
                dialog.setTargetFragment(CrimeFragenment.this,RESULT_DATE);
                dialog.show(fm,DIALOG_DATE);


            }
        });
        datebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(crime.getDate());
                dialog.setTargetFragment(CrimeFragenment.this,RESULT_TIME);
                dialog.show(fm,DIALOG_TIME);


            }
        });
//
        solvedcheckBox = (CheckBox)v.findViewById(R.id.crime_soloved);

        solvedcheckBox.setChecked(crime.isSolved());

        solvedcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });
        imageButton = (ImageButton)v.findViewById(R.id.crime_imagebutton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Intent i = new Intent(getActivity(),CrimeCameraActivity.class);
//                startActivity(i);
                Intent i = new Intent(getActivity(),CrimeCameraActivity.class);
//                i.setAction("android.media.action.IMAGE_CAPTURE");
//                i.addCategory("android.intent.category.DEFAULT");
//                startActivity(i);
                startActivityForResult(i,RESULT_PHOTO);
            }
        });
        PackageManager pm = getActivity().getPackageManager();
        boolean hasACamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)||
                pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)||
                Build.VERSION.SDK_INT<Build.VERSION_CODES.GINGERBREAD||
                Camera.getNumberOfCameras()>0;
        if (!hasACamera){
            imageButton.setEnabled(false);}


        imageView = (ImageView)v.findViewById(R.id.crime_imageview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photo p = crime.getPhoto();
                try {
                    android.support.v4.app.FragmentManager fm = getActivity()
                            .getSupportFragmentManager();
                    String path = getActivity().getFileStreamPath(p.getFilename())
                            .getAbsolutePath();
                    ImageFragment.newInstance(path)
                            .show(fm, DIALOG_IMAGE);
                }catch (NullPointerException e){
                    Log.e("CrimeFragment","photo is null");
                }

            }
        });
        Button send = (Button)v.findViewById(R.id.crime_report);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
//                i.putExtra("text",getCrimeReport());
//                i.putExtra("subject",getString(R.string.crime_report_subject));
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.crime_report_subject));
                i = Intent.createChooser(i, getString(R.string.send_report));
                startActivity(i);
            }
        });
        supectButton = (Button)v.findViewById(R.id.crime_suspect);
        supectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract
                .Contacts.CONTENT_URI);
                startActivityForResult(i,REQUEST_SUPECT);

            }
        });
        if (crime.getName() != null){
            supectButton.setText(crime.getName());
        }
        return v;
    }

    private void showPhoto(){
        Photo p = crime.getPhoto();
        BitmapDrawable bitmapDrawable = null;
        if (p != null){
            String path = getActivity()
                    .getFileStreamPath(p.getFilename()).getAbsolutePath();
            String test = getActivity()
                    .getFileStreamPath(p.getFilename()).toString();
            Log.e("TEST",""+test);
            bitmapDrawable = PicturesUtils.getScaledDrawable(getActivity(),path);
        }
        imageView.setImageDrawable(bitmapDrawable);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) return;
        if(requestCode == RESULT_DATE){
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
            String st = crime.getDate().toString();
            Log.e("TAG",st);
            Log.e("TAG",date.toString());
            datebutton1.setText(st.subSequence(30,34)+" "+st.subSequence(0,10));
//            datebutton.setText(crime.getDate().toString());

//            datebutton2.setText(st.subSequence(11,20));
//            datebutton.setText(crime.getDate().toString());
        }else if(requestCode == RESULT_TIME){
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            crime.setDate(date);
//            Bundle i = data.getBundleExtra(TimePickerFragment.EXTRA_DATE);

            String st = crime.getDate().toString();

            datebutton2.setText(st.subSequence(11,20));
//            datebutton.setText(crime.getDate().toString());

//            datebutton2.setText(st.subSequence(11,20));
//            datebutton.setText(crime.getDate().toString());
        }else if(requestCode == RESULT_PHOTO){
            String filename = data
                    .getStringExtra(CrimeCameraFragment.EXTRA_PHOTO_FILENAME);
            if (filename != null){
//                Log.i("CrimeFragment",""+filename);
                    Photo p = new Photo(filename);
                    crime.setPhoto(p);
                    showPhoto();
//                    Log.i("CrimeFragment","Crime: "+ crime.getmTitle() +"has a photo");
            }


        }else if (requestCode == REQUEST_SUPECT){
                Uri uri = data.getData();
                String[] queryFileds = new String[]{
                        ContactsContract.Contacts.DISPLAY_NAME
                };
            Cursor c = getActivity().getContentResolver()
                    .query(uri,queryFileds,null,null,null );
            if (c.getCount() == 0){
                c.close();
                return;
            }
            c.moveToFirst();
            String supectName = c.getString(0);
            crime.setName(supectName);
            supectButton.setText(supectName);
            c.close();


        }
    }

    private String getCrimeReport(){
        String solvedString = null;
        if(crime.isSolved()){
            solvedString = getString(R.string.crime_report_solved);
        }
        String dateFormat = "EEE,MMM-dd'号";

        String dateString = android.text.format.DateFormat.format(dateFormat,crime.getDate()).toString();
        String suspect = crime.getName();
        if(suspect == null){
            suspect = getString(R.string.crime_report_noSupect);
        }else{
            suspect = getString(R.string.crime_report_supect,suspect);
        }
        String report = getString(R.string.crime_report,crime.getmTitle(),
                dateString,solvedString,suspect);
        return report;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
     //   mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        showPhoto();
    }

    @Override
    public void onPause() {
        super.onPause();

       CrimeLab.getsCrimeLab(getActivity()).saveCrimes();

    }

    @Override
    public void onStop() {
        super.onStop();
        PicturesUtils.cleanImageView(imageView);
    }
}

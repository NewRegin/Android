package com.example.regin.criminalintent;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.UUID;


public class CrimePagerActivity extends FragmentActivity {

    private ViewPager viewPager;
    private ArrayList<Crime> crimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_crime_pager);
        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);
//        ActionBar actionBar = getActionBar();
//        actionBar.show();
        crimes = CrimeLab.getsCrimeLab(this).getCrimes();

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {


            @Override
            public int getCount() {
                return crimes.size();
            }
            @Override
            public Fragment getItem(int i) {
//                return null;
                Crime crime = crimes.get(i);
                return CrimeFragenment.newInstance(crime.getmID());
//                return f;
            }
        });
        UUID id = (UUID)getIntent()
                .getSerializableExtra(CrimeFragenment.EXTRA_CRIME_ID);
        for (int i = 0;i<crimes.size();i++){
            if(crimes.get(i).getmID().equals(id)){
                viewPager.setCurrentItem(i);
                break;
            }
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                    Crime crime = crimes.get(i);
                    if(crime.getmTitle()!=null){
                        setTitle(crime.getmTitle());
                    }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crime_pager, menu);
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

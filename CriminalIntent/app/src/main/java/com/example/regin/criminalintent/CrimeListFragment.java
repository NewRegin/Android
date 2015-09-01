package com.example.regin.criminalintent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Regin on 15/5/11.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> crimes;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.crime_title);

        crimes = CrimeLab.getsCrimeLab(getActivity()).getCrimes();
//        ArrayAdapter<Crime>adapter =
//                new ArrayAdapter<Crime>(getActivity(),android.R.layout.simple_list_item_1,crimes);
        CrimeAdapter adapter = new CrimeAdapter(crimes);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ListView listView = (ListView)v.findViewById(android.R.id.list);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
                 registerForContextMenu(listView);
        }else {
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    mode.setTitle(R.string.crime_title);
                    inflater.inflate(R.menu.menu_list_item_context,menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_item_delete_crime:
                            CrimeAdapter crimeAdapter = (CrimeAdapter)getListAdapter();
                            CrimeLab crimeLab = CrimeLab.getsCrimeLab(getActivity());

                            for (int i = crimeAdapter.getCount();i>=0;i--){
                                if (getListView().isItemChecked(i)){
                                    crimeLab.deleteCrime(crimeAdapter.getItem(i));
                                }

                            }
                            mode.finish();
                            crimeAdapter.notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }



                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        }

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                Crime c = new Crime();
                CrimeLab.getsCrimeLab(getActivity()).addCrime(c);
                Intent i = new Intent(getActivity(),CrimePagerActivity.class);
                i.putExtra(CrimeFragenment.EXTRA_CRIME_ID,c.getmID());
               // startActivityForResult(i,0);
                startActivity(i);

                return true;
            default:
                  return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        v.getId();
        getActivity().getMenuInflater().inflate(R.menu.menu_list_item_context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = adapterContextMenuInfo.position;
        CrimeAdapter crimeAdapter = (CrimeAdapter)getListAdapter();
        Crime crime = crimeAdapter.getItem(position);
        switch (item.getItemId()){
            case R.id.menu_item_delete_crime:
                CrimeLab.getsCrimeLab(getActivity())
                        .deleteCrime(crime);
                crimeAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
//       Crime c = (Crime)(getListAdapter()).getItem(position);
        Crime c =(Crime) ( getListAdapter()).getItem(position);
//       Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
//
//        Log.e("AS",c.getmTitle());
        Intent i = new Intent(getActivity(),CrimePagerActivity.class);
        i.putExtra(CrimeFragenment.EXTRA_CRIME_ID,c.getmID());
        startActivity(i);
    }
    //与Crime交互的Adapter
    private class CrimeAdapter extends ArrayAdapter<Crime>{
        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0, (java.util.List<Crime>) crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }
            Crime c = getItem(position);
            CheckBox cb = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedcheckbox);
            cb.setChecked(c.isSolved());
            TextView dt = (TextView)convertView.findViewById(R.id.crime_list_item_date);
            String st = c.getDate().toString();

            dt.setText(st);
            TextView title = (TextView)convertView.findViewById(R.id.crime_list_item_title);
            title.setText(c.getmTitle());
            return convertView;
        }
    }
}

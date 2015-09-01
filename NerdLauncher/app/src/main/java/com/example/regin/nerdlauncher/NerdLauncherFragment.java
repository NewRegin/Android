package com.example.regin.nerdlauncher;

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Regin on 15/6/12.
 */
public class NerdLauncherFragment extends android.support.v4.app.ListFragment {
    private static final String TAG = "NerdLanucherFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startUpIntent = new Intent(Intent.ACTION_MAIN);
        startUpIntent.addCategory(Intent.CATEGORY_LAUNCHER);

         PackageManager pm = getActivity().getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(startUpIntent,0);

        Log.i(TAG,"Find "+activities.size()+"activities");
        Collections.sort(activities,new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo lhs, ResolveInfo rhs) {
                   PackageManager pm = getActivity().getPackageManager();
                   return String.CASE_INSENSITIVE_ORDER.compare(
                           lhs.loadLabel(pm).toString(),
                           rhs.loadLabel(pm).toString());
            }
        });



        ArrayAdapter<ResolveInfo> adapter =
                new ArrayAdapter<ResolveInfo>(
                getActivity(),R.layout.simple_test,R.id.textview,
                        activities){
            public View getView(int pos,View convertView,ViewGroup parent){
                PackageManager pm = getActivity().getPackageManager();
                View v = super.getView(pos,convertView,parent);

                TextView textView = (TextView)v;

                ResolveInfo resolveInfo = getItem(pos);
                textView.setCompoundDrawablesWithIntrinsicBounds(resolveInfo.loadIcon(pm),null,null,null);
                textView.setText(resolveInfo.loadLabel(pm));

                return v;
            }
        };
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ResolveInfo resolveInfo = (ResolveInfo)l
                .getAdapter().getItem(position);
        ActivityInfo activityInfo = resolveInfo.activityInfo;

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClassName(activityInfo.applicationInfo.packageName,
                activityInfo.name);

        startActivity(i);
    }
}

package com.example.regin.draganddraw;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends SingleFragmentActivity {

    protected Fragment createFragment(){
        return new DragAndDrawFragment();
    }
}

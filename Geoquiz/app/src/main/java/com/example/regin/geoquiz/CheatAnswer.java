package com.example.regin.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CheatAnswer extends ActionBarActivity {

    private TextView tv;
    private TextView tv1;
    private boolean mCheattIndex = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat_answer);

        findViewById(R.id.Sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                tv = (TextView)findViewById(R.id.Cheat);
               boolean b = i.getBooleanExtra("answer",true);
                if(b==true)
                    tv.setText("True");
                else
                    tv.setText("False");
                mCheattIndex = true;

                i = new Intent();
                i.putExtra("data",mCheattIndex);
                setResult(1,i);
            //   startActivity(new Intent(CheatAnswer.this,MainActivity.class));
              //  tv.setText();
//                Toast.makeText(CheatAnswer.this,i.getBooleanExtra("answer",true),Toast.LENGTH_LONG).show();

            }
        });
        tv1 = (TextView)findViewById(R.id.API);
        tv1.setText("API"+Build.VERSION.SDK_INT);

        //finish();
        Intent t = new Intent();
        t.putExtra("data", mCheattIndex);
        setResult(1,t);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cheat_answer, menu);
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

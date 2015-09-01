package com.example.regin.senddata;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Set extends ActionBarActivity {
        private TextView text;
        private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        Intent i = getIntent();
      //  Bundle data = i.getExtras();
//        Bundle data = i.getBundleExtra("data")
        // text = (TextView)findViewById(R.id.textView);
        editText =(EditText) findViewById(R.id.editText);
       // text.setText(i.getStringExtra("data"));
//           text.setText(String.format("name=%s,age=%d,name1=%s",data.getString("name"),data.getInt("age"),data.getString("name1","10")));
      //  SendClass sc = (SendClass) i.getSerializableExtra("data");
        SendClass sc = i.getParcelableExtra("data");
        Bundle bl = sc.getBl();
//        text.setText(String.format("%s,%d,%d",bl.getString("name"),bl.getInt("age"),sc.getAge()));
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();
                i.putExtra("data",editText.getText().toString());
                setResult(1,i);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set, menu);
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

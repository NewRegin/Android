package com.example.regin.geoquiz;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
//    private Button mTrueButton;
//    private Button mFalseButton;
    private static final String KEY_INDEX = "Index";
    private TextView tv;
    private boolean test;
      private int mCurrentIndex = 0;
      private TrueFalse[]  trueFalse = new TrueFalse[]{
        new TrueFalse(R.string.one,true),
        new TrueFalse(R.string.two,false),
        new TrueFalse(R.string.three,false),
        new TrueFalse(R.string.four,true),
};
    private void update(){
            int question = trueFalse[mCurrentIndex].getmQuestion();
            tv.setText(question);

    }
    private boolean CheckAnswer(boolean answer){
          int i = 0;
          boolean k = false;
          if(answer == trueFalse[mCurrentIndex].ismTrueQuestion())
          {
              i = R.string.True_Toast;
              k = true;
          }else {
              i = R.string.False_Toast;

          }
        Toast.makeText(MainActivity.this,i,Toast.LENGTH_SHORT).show();
        return k;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
        {
            //如果Bundle没有数据，则使用默认值0；
         //   mCurrentIndex = savedInstanceState.getInt("KEY_INDEX",0);
          mCurrentIndex = savedInstanceState.getInt("KEY_INDEX");
        //  Log.isLoggable("TAG",mCurrentIndex);
          //  System.out.println(mCurrentIndex);
            Log.e("TAG","curr: "+mCurrentIndex);
        }

       // Log.v("TAG","curr"+mCurrentIndex);
        setContentView(R.layout.activity_main);
        tv =  (TextView)findViewById(R.id.TextID);
        update();
//        findViewById(R.id.Next).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mCurrentIndex = (mCurrentIndex+1)%trueFalse.length;
//                update();
//            }
//        });
       findViewById(R.id.True_Button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               Toast.makeText(MainActivity.this,R.string.True_Toast,Toast.LENGTH_SHORT).show();
                test = CheckAnswer(true);
               if(test==true)
               {
                    mCurrentIndex = (mCurrentIndex+1)%trueFalse.length;
                    update();
               }
           }
       });
        findViewById(R.id.False_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,R.string.False_Toast,Toast.LENGTH_SHORT).show();
                test = CheckAnswer(false);
                if(test==true)
                {
                    mCurrentIndex = (mCurrentIndex+1)%trueFalse.length;
                    update();
                }
            }
        });
        findViewById(R.id.showAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Intent();

//               startActivity(new Intent(MainActivity.this,CheatAnswer.class).putExtra("answer",trueFalse[mCurrentIndex].ismTrueQuestion()));
                 startActivityForResult(new Intent(MainActivity.this,CheatAnswer.class).putExtra("answer",trueFalse[mCurrentIndex].ismTrueQuestion()),0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (data.getBooleanExtra("data",false)==false)
                Toast.makeText(MainActivity.this, "Good Boy!!!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Cheat!!!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG","onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KEY_INDEX",mCurrentIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

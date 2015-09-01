package com.example.regin.machinealu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import com.example.regin.machinealu.machine.machine.bean.ChatMessage;
import com.example.regin.machinealu.machine.machine.bean.ChatMessage.Type;
import com.example.regin.machinealu.machine.utils.HttpsUtils;

public class MainActivity extends Activity {

    private ListView mMsgs;
    private ChatMessageAdapter mAdapter;
    private List<ChatMessage>mDatas;
    private EditText mInputMsg;
    private Button mSendMsg;

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            // 等待接收，子线程完成数据的返回
            ChatMessage fromMessge = (ChatMessage) msg.obj;
            mDatas.add(fromMessge);
            mAdapter.notifyDataSetChanged();
            mMsgs.setSelection(mDatas.size()-1);
        };

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();
        initListener();
    }
    public void initView(){
        mMsgs = (ListView)findViewById(R.id.id_listview_msgs);
        mInputMsg = (EditText)findViewById(R.id.id_input_msg);
        mSendMsg = (Button)findViewById(R.id.id_send_msg);
    }
    public void initDatas(){
        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("有什么吩咐", Type.INCOMING, new Date()));
        mAdapter = new ChatMessageAdapter(this, mDatas);
        mMsgs.setAdapter(mAdapter);
    }
    private void initListener()
    {
        mSendMsg.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String toMsg = mInputMsg.getText().toString();
                if (TextUtils.isEmpty(toMsg))
                {
                    Toast.makeText(MainActivity.this, "发送消息不能为空！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                ChatMessage toMessage = new ChatMessage();
                toMessage.setDate(new Date());
                toMessage.setMsg(toMsg);
                toMessage.setType(Type.OUTCOMING);
                mDatas.add(toMessage);
                mAdapter.notifyDataSetChanged();
                mMsgs.setSelection(mDatas.size()-1);

                mInputMsg.setText("");

                new Thread()
                {
                    public void run()
                    {
                        ChatMessage fromMessage = HttpsUtils.sendMessage(toMsg);
                        Message m = Message.obtain();
                        m.obj = fromMessage;
                        mHandler.sendMessage(m);
                    };
                }.start();

            }
        });
    }


    }




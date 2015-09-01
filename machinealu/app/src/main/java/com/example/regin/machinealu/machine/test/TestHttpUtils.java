package com.example.regin.machinealu.machine.test;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.regin.machinealu.machine.utils.HttpsUtils;

/**
 * Created by Regin on 15/3/10.
 */
public class TestHttpUtils extends AndroidTestCase{
        public void TestSendInfo(){
            String res = HttpsUtils.doGet("给我讲个笑话");
            Log.e("TAG", res);
            res = HttpsUtils.doGet("给我讲个鬼故事");
            Log.e("TAG", res);
            res = HttpsUtils.doGet("北京今日天气");
            Log.e("TAG", res);
            res = HttpsUtils.doGet("刘洋是谁");
            Log.e("TAG", res);


        }
}

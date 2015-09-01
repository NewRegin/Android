package com.example.regin.machinealu.machine.utils;

import com.example.regin.machinealu.machine.machine.bean.ChatMessage;
import com.example.regin.machinealu.machine.machine.bean.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;

import com.example.regin.machinealu.machine.machine.bean.ChatMessage.Type;

/**
 * Created by Regin on 15/3/10.
 */
public class HttpsUtils {

        private static final String URL = "http://www.tuling123.com/openapi/api";
        private static final String API_KEY = "e2c900512c98664fe830eb21937dd4a6";
        public static ChatMessage sendMessage(String msg){
            ChatMessage chatMessage = new ChatMessage();
            String jsonRes = doGet(msg);
            Gson gson = new Gson();
            Result result = null;

            try {

                result = gson.fromJson(jsonRes, Result.class);
                chatMessage.setMsg(result.getText());
            } catch (JsonSyntaxException e) {
               chatMessage.setMsg("服务器繁忙，请稍后再试");
            }

            chatMessage.setDate(new Date());
            chatMessage.setType(Type.INCOMING);
            return chatMessage;
        }

        public static String doGet(String msg){
            String result = "";
            String url = "";
            ByteArrayOutputStream baos = null;
            InputStream is = null;
            url = setParams(msg);
            try
            {
                java.net.URL urlNet = new java.net.URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();

                conn.setReadTimeout(5*1000);
                conn.setConnectTimeout(5*1000);
                conn.setRequestMethod("GET");

                is = conn.getInputStream();
                int len = -1;
                byte[] buf = new byte[128];
                baos = new ByteArrayOutputStream();
                while (is.read(buf)!=-1){
                    baos.write(buf,0,len);

                }
                baos.flush();
                result = new String(baos.toByteArray());
            }catch (MalformedURLException e){

                e.printStackTrace();
            }catch (IOException e){

                e.printStackTrace();
            }finally {
                try
                {
                    if (baos != null) {
                        baos.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();

                }
                try {


                    if (is != null) {
                        is.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            return result;
        }

    private static String setParams(String msg) {
        String url = "";
        try {
            url = URL+"?key="+API_KEY+"&info="+URLEncoder.encode(msg,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

}

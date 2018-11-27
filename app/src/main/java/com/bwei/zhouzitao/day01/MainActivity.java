package com.bwei.zhouzitao.day01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String urlpath="http://www.xieast.com/api/news/news.php?page=1";
    private ListView lv;
    public List<Bean.DataBean> list ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String data = (String) msg.obj;
                Gson gson = new Gson();
                Bean bean = gson.fromJson(data, Bean.class);
                list = bean.getData();

                Myadapter adapter = new Myadapter(list,MainActivity.this);
                lv.setAdapter(adapter);



            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("urx",list.get(i).getUrl());
                startActivity(intent);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlpath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        String data = CharStreams.toString(new InputStreamReader(connection.getInputStream(), "utf-8"));

                        Message message = Message.obtain();
                        message.obj = data;
                        handler.sendMessage(message);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


}

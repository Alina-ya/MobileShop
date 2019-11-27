package com.huatec.edu.mobileshop.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.huatec.edu.mobileshop.R;
import com.huatec.edu.mobileshop.http.MemberResult;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetWorkActivity extends AppCompatActivity implements View.OnClickListener {
    Handler handler = new Handler();
    private TextView tv_result;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.bt_request).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_request:
            httpRequest("123","123");
            break;
        }
    }

    private void httpRequest(final String username,final String password){
        new Thread(){
            public void run(){
                super.run();
                OkHttpClient client = new OkHttpClient();
                FormBody body = new FormBody.Builder()
                        .add("input",username)
                        .add("password",password)
                        .build();
                Request request = new Request.Builder()
                            .url("http://10.216.42.237:8080/MobileShop/member/login2")
                        .post(body)
                        .build();
                try{
                    Response response = client.newCall(request).execute();
                    String result = response.body().string();
                    Gson gson = new Gson();
                    final MemberResult memberResult = gson.fromJson(result, MemberResult.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(memberResult!=null && memberResult.data!=null){
                                tv_result.setText(
                                        String.format("用户名：%s\n邮箱：%s"
                                        ,memberResult.data.uname
                                        ,memberResult.data.email)
                                );
                            }
                        }
                    });
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }.start();
    }
}

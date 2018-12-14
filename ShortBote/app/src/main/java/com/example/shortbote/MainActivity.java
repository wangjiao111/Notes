package com.example.shortbote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;
    private EditText etContent;
    private Button bt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber=(EditText) findViewById(R.id.et_number);
        etContent=(EditText) findViewById(R.id.et_content);
        bt_send=(Button) findViewById(R.id.bt_send);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_send:
                        //获取文本内容
                        String content=etContent.getText().toString().trim();
                        //获取电话号码
                        String number=etNumber.getText().toString().trim();
                        if(TextUtils.isEmpty(number)){
                            Toast.makeText(MainActivity.this,"电话号码不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        } else if(TextUtils.isEmpty(content)){
                            Toast.makeText(MainActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            //有内容则发送
                            SmsManager smsManager=SmsManager.getDefault();
                            //内容超出最大限度则拆分成多条短信
                            ArrayList<String> contents=smsManager.divideMessage(content);
                            for (String str:contents) {
                                smsManager.sendTextMessage(number, null, content, null, null);
                            }
                        }
                        break;
                }
            }
        });

    }
}

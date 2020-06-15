package com.wsy.everylesson.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wsy.everylesson.R;

public class PhonePaw_Login extends AppCompatActivity {

    private TextView mLoginVerfi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_paw__login);
        initView();
    }

    private void initView() {
        mLoginVerfi = (TextView) findViewById(R.id.verfi_login);
        mLoginVerfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhonePaw_Login.this, VerificationActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.aidl_client;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.ipc.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface myAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getServer(View view) {
        switch (view.getId()) {
            //绑定远程服务端
            case R.id.btn_bind:
                bindServer();
                break;
            //获取远程服务端的数据
            case R.id.btn_get:
                getServerData();
                break;
        }
    }

    private void getServerData() {
        if (myAidlInterface != null) {
            try {
                String str = myAidlInterface.getString();
                Log.e("TAG", "获取到的数据是：" + str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindServer() {
        Intent intent = new Intent();
        intent.setAction("com.example.ipc.MyService");
        intent.setPackage("com.example.ipc");
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        //和远程服务绑定到一起了
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        //和远程服务断开绑定了
        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface = null;
        }
    };

    @Override
    protected void onDestroy() {
        if (conn != null)
            unbindService(conn);
        super.onDestroy();
    }
}

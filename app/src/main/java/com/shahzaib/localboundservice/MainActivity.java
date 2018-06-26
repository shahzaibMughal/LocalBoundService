package com.shahzaib.localboundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     We will see, how to
     - create
     - start
     - interact
     with a local private bound service.

     The purpose of the bound service is to obtain the current time from the system
     and return that information to the activity where it will be displayed to the user.
     */

    MyLocalBoundService myLocalBoundService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MyLocalBoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

    }

    public void showTime(View view) {
        TextView textView = findViewById(R.id.textView);
        if(isBound)
        {
            textView.setText(myLocalBoundService.getCurrentTime());
        }
        else
        {
            textView.setText("Service is not bounded");
        }
    }


    private ServiceConnection myConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className,
                                       IBinder service)
        {
            MyLocalBoundService.MyBinder myBinder = (MyLocalBoundService.MyBinder) service;
            myLocalBoundService = myBinder.getService();
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0)
        {
            isBound = false;
        }
    };


}

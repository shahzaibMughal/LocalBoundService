package com.shahzaib.localboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyLocalBoundService extends Service {

    private final IBinder myIBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myIBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",Locale.getDefault());
        return (dateFormat.format(new Date()));
    }

    public class MyBinder extends Binder
    {
        MyLocalBoundService getService() {
            return MyLocalBoundService.this;
        }
    }
}



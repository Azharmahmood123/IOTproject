package com.fyp.iot.project;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class AppController extends Application {

    @SuppressLint("StaticFieldLeak")
    private static AppController mInstance;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        context = AppController.this.getApplicationContext();

        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context);
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }

    public Context getContext() {
        return context;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}

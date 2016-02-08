package com.fhb.sqliteapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by VAIO on 2/7/2016.
 */



public class StethoSample extends Application {
    public void onCreate() {
        super.onCreate();
      //  Stetho.initializeWithDefaults(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }


}


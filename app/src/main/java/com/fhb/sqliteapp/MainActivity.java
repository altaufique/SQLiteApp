package com.fhb.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText editTextPkgName;
    EditText editTextPkgGold;
    EditText editTextGoldQty;
    EditText editTextPkgFee;
    Button buttonPkgUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);

        editTextPkgName = (EditText)findViewById(R.id.editText_PkgName);
        editTextPkgGold = (EditText)findViewById(R.id.editText_PkgGold);
        editTextGoldQty = (EditText)findViewById(R.id.editText_GoldQty);
        editTextPkgFee = (EditText)findViewById(R.id.editText_PkgFee);
        buttonPkgUpdate = (Button)findViewById(R.id.button_PkgUpdate); // this var is use for action block

        updateData();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    public void updateData () {
        buttonPkgUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editTextPkgName.getText().toString(),
                                editTextPkgGold.getText().toString(),
                                editTextGoldQty.getText().toString(),
                                editTextPkgFee.getText().toString());

                        if (isInserted =true)
                            Toast.makeText(MainActivity.this,"Data is inserted.",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data is not inserted.",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}

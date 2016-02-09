package com.fhb.sqliteapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB; // myDB is DatabaseHelper data type. it is not a class but a referenced to DatabaseHelper method, subclass etc..

    EditText editTextPkgName; // ditto
    EditText editTextPkgGold;
    EditText editTextGoldQty;
    EditText editTextPkgFee;
    Button buttonPkgUpdate;
    Button buttonGetPkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // This is called when database is created.
        super.onCreate(savedInstanceState);     // Using 'super' to call parent class (AppCompatActivity) method of onCreate.
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);

        // Casting declared Widgets
        editTextPkgName = (EditText)findViewById(R.id.editText_PkgName);
        editTextPkgGold = (EditText)findViewById(R.id.editText_PkgGold);
        editTextGoldQty = (EditText)findViewById(R.id.editText_GoldQty);
        editTextPkgFee = (EditText)findViewById(R.id.editText_PkgFee);
        buttonPkgUpdate = (Button)findViewById(R.id.button_PkgUpdate); // this var is use in action code in insertData method
        buttonGetPkg = (Button)findViewById(R.id.button_GetPkg); // this var is use for action code in getPkg method

        callStetho();
        insertData();
        getData();
    }

    public void insertData () {
        buttonPkgUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editTextPkgName.getText().toString(),
                                editTextPkgGold.getText().toString(),
                                editTextGoldQty.getText().toString(),
                                editTextPkgFee.getText().toString());

                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Success!! Data is inserted.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Failed!! Data is not inserted.", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void getData() {
        buttonGetPkg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get the data from database
                        Cursor result = myDB.getAllData(); // Cursor data type as a ref to Cursor class.

                        if(result.getCount() == 0) {
                            // error message
                            showMessage("ERROR", "No data found!!!");
                            return;
                        }

                        // Show the result
                        // set a variable to hold the string
                        StringBuffer stringBuffer = new StringBuffer(); //buffer now is empty

                        // Loop the entire data
                        while (result.moveToNext()) {
                            stringBuffer.append("Package: " + result.getString(1) +"\n");
                            stringBuffer.append("Name: " + result.getString(2) +"\n");
                            stringBuffer.append("Gold_qty: " + result.getString(3) +"\n");
                            stringBuffer.append("Fee: RM" + result.getString(4) + "\n\n");
                        }
                        showMessage("PowerGold Package", stringBuffer.toString());
                    }
                }
        );
    }

    public void showMessage (String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    // Call Stheto library to do database and other resources checking
    public void callStetho () {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}

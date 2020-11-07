package com.satwik.permissions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     Button bt1;
     TextView tv1;
     EditText et1;
     Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=findViewById(R.id.bt1);
        tv1=findViewById(R.id.tv1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo newinfo=cm.getActiveNetworkInfo();
                boolean isConnected=newinfo != null && newinfo.isConnected();
                tv1.setText(isConnected? "Connected": "Not Connected");
            }
        });

        bt2=findViewById(R.id.bt2);
        et1=findViewById(R.id.et1);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             int perm= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);
             if(perm== PackageManager.PERMISSION_GRANTED)
                 make_call();
             else {
                 ActivityCompat.requestPermissions(MainActivity.this,
                         new String[]{ Manifest.permission.CALL_PHONE },
                         121);
             }
            }
        });

    }
       void make_call()
       {
        String number=et1.getText().toString();
        Uri uri=Uri.parse("tel:"+number);
        Intent i= new Intent(Intent.ACTION_CALL,uri);
        startActivity(i);
    }
}
package com.msnit.localcafe;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.msnit.localcafe.cafe.CafeManagement;
import com.msnit.localcafe.server.HostServer;

public class MainActivity extends AppCompatActivity {
    TextView status;
    Button startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.start_server);
        status = findViewById(R.id.status);
        RecyclerView reqList = findViewById(R.id.requestsList);

        OrdersAdapter ordersAdapter = new OrdersAdapter(getApplicationContext());
        reqList.setAdapter(ordersAdapter);
        reqList.setLayoutManager(new LinearLayoutManager(this));
        CafeManagement.adapter = ordersAdapter;
        CafeManagement.recyclerView = reqList;


        startBtn.setOnClickListener(v -> {
            if (!HostServer.isOn())
                HostServer.startServer(getApplicationContext());
            else HostServer.stopServer();
            checkServerStatus();
        });

    }


    public void checkServerStatus() {
        if (HostServer.isOn()) {
            status.setText("يعمل");
            startBtn.setText("إيقاف");
        } else {
            status.setText("متوقف");
            startBtn.setText("تشغيل");

        }
    }

    private void startServerService(View v) {
        Intent serverIntent = new Intent(this, ServerService.class);

        startService(serverIntent);
    }

    private void stopServerService(View v) {
        Intent serverIntent = new Intent(this, ServerService.class);

        stopService(serverIntent);

    }
}
package com.example.http.simpleintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.filter;

public class MainActivity extends AppCompatActivity {
    //1
    EditText fieldA;
    EditText fieldB;
    TextView result;
    Button plusButton;
    Button minusButton;
    Button multiplyButton;
    Button devideButton;


    LocalBroadcastManager localBroadcastManager;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO odbierz odpowiedź i wyświetl wynik na ekranie
            // double result = intent.getDoubleExtra("NAZWA_POLA", 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fieldA = (EditText) findViewById(R.id.first_value);
        fieldB = (EditText) findViewById(R.id.second_value);
        plusButton = (Button) findViewById(R.id.button_plus);
        minusButton = (Button) findViewById(R.id.button_minus);
        multiplyButton = (Button) findViewById(R.id.button_multiply);
        devideButton = (Button) findViewById(R.id.button_divide);
        result = (TextView) findViewById(R.id.result);

        plusButton.setOnClickListener(new View.OnClickListener() {//2
            @Override
            public void onClick(View v) {
                double valueA = Double.valueOf(fieldA.getText().toString()); //4
                double valueB = Double.valueOf(fieldB.getText().toString()); //4

                Intent intent = new Intent(MainActivity.this,SimpleIntentService.class);//3
                intent.setAction("action.PLUS");
                intent.putExtra("extra.VALUE_A",valueA); //5
                intent.putExtra("extra.VALUE_B",valueB); //5

                startService(intent); //6

            }
        });


        IntentFilter filter = new IntentFilter();
         filter.addAction("ADD");
        //TODO: wstaw nazwę akcji którą chcesz odebrać

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, filter);


        // TODO: Wysyłanie parametrów po kliknięciu na guzik
        Intent intent = new Intent();
        intent.setAction("ADD");
        intent.putExtra("X", 2);
        intent.putExtra("Y", 5);
        localBroadcastManager.sendBroadcast(intent);
    }



    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}

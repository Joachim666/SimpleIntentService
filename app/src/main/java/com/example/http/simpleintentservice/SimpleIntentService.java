package com.example.http.simpleintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


public class SimpleIntentService extends IntentService {

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            double valueA = intent.getDoubleExtra("extra.VALUE_A",0);
            double valueB = intent.getDoubleExtra("extra.VALUE_B",0); //8
            Log.i("TEST","Nazwa akcji " + action); //7

            if ("action.PLUS".equals(action)) {
                double result = add(valueA,valueB);//9
                Log.i("TEST", "wyniki: " + result); //10
                broadcastResult( result);//12



                //TODO pobierz parametry, oblicz i odeślij wynik
            }
            else if ("NAZWA_AKCJI_2".equals(action)) {

                //TODO pobierz parametry, oblicz i odeślij wynik
            }
        }
    }

    private void broadcastResult(double result) {//11
        Intent intent = new Intent();
        intent.setAction("action.CALCULATION_RESULT");
        intent.putExtra("extra.VALUE", result);

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.sendBroadcast(intent);
    }

    private double add(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        return a / b;
    }
}

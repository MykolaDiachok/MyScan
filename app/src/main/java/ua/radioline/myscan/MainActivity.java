package ua.radioline.myscan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button buttonEnable;
    Button buttonDisable;
    TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        buttonEnable = (Button) findViewById(R.id.button);
        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.hht.emdk.datawedge.api.ACTION_SCANNERINPUTPLUGIN");
                intent.putExtra("com.hht.emdk.datawedge.api.EXTRA_PARAMETER", "ENABLE_PLUGIN");
                sendBroadcast(intent);
                intent = new Intent();
                intent.setAction("com.hht.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER");
                intent.putExtra("com.hht.emdk.datawedge.api.EXTRA_PARAMETER", "ENABLE_TRIGGERBUTTON");
                sendBroadcast(intent);
                intent = new Intent();
                intent.setAction("com.hht.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER");
                intent.putExtra("com.hht.emdk.datawedge.api.EXTRA_PARAMETER", "DISABLE_BEEP");
                sendBroadcast(intent);
            }
        });
        buttonDisable = (Button) findViewById(R.id.button2);
        buttonDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.hht.emdk.datawedge.api.ACTION_SOFTSCANTRIGGER");
                intent.putExtra("com.hht.emdk.datawedge.api.EXTRA_PARAMETER", "DISABLE_TRIGGERBUTTON");
                sendBroadcast(intent);
                intent = new Intent();
                intent.setAction("com.hht.emdk.datawedge.api.ACTION_SCANNERINPUTPLUGIN");
                intent.putExtra("com.hht.emdk.datawedge.api.EXTRA_PARAMETER", "DISABLE_PLUGIN");
                sendBroadcast(intent);
            }
        });

        tempTextView = (TextView) findViewById(R.id.textView);

        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String code = intent.getExtras().getString("com.hht.emdk.datawedge.data_string");
                tempTextView.setText(code);
                // do something

                unregisterReceiver(this);
            }
        }
                , new IntentFilter("DATA_SCAN"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


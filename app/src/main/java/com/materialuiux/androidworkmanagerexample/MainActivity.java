package com.materialuiux.androidworkmanagerexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.materialuiux.androidworkmanagerexample.Adapter.Ad_Quotes;
import com.materialuiux.androidworkmanagerexample.model.Quotes;
import com.materialuiux.androidworkmanagerexample.notification.NotificationHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private EditText hours;
    private EditText minutes;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerView);
        setData();
    }

    // set data into recyclerView
    private void setData() {
        new AsyncTask<Void, Void, ArrayList<Quotes>>() {
            @Override
            protected ArrayList<Quotes> doInBackground(Void... voids) {
                return DataReader.getAllQuotes(mContext);
            }

            @Override
            protected void onPostExecute(ArrayList<Quotes> quotesArrayList) {
                Log.d("HERE", quotesArrayList.toString());
                Ad_Quotes ad_quotes = new Ad_Quotes(mContext, quotesArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(ad_quotes);
            }
        }.execute();
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
        if (id == R.id.action_notifications) {
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     *
     * Dialog for setting the time for the notification
     *
     */
    public void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        hours = dialog.findViewById(R.id.editTextHH);
        minutes = dialog.findViewById(R.id.editTextMM);
        ToggleButton setNotification = dialog.findViewById(R.id.toggleButton);
        setNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToggleButton(view);
            }
        });
        Button cancelNotification = dialog.findViewById(R.id.cancel_action);
        cancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarms();
            }
        });
        dialog.show();

    }


    public void clickToggleButton(View view) {
        boolean isEnabled = view.isEnabled();
        if (isEnabled) {
            NotificationHelper.scheduleRepeatingNotification(mContext, hours.getText().toString(), minutes.getText().toString());
        } else {
            NotificationHelper.cancelAlarm();
        }
    }

    public void cancelAlarms() {
        NotificationHelper.cancelAlarm();
    }

}

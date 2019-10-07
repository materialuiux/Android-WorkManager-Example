package com.materialuiux.androidworkmanagerexample.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.materialuiux.androidworkmanagerexample.Constants;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class NotificationHelper {


    private static AlarmManager alarmManagerRTC;
    private static PendingIntent alarmIntentRTC;

    /**
     * here we will schedule Repeating Notification
     *
     * @param context
     * @param hour    hour Notification that set by user
     * @param min     min  Notification that set by user
     */
    public static void scheduleRepeatingNotification(Context context, String hour, String min) {
        //get calendar instance to be able to select what time notification should be scheduled
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //Setting time of the day (8am here) when notification will be sent every day (default)
        calendar.set(Calendar.HOUR_OF_DAY, Integer.getInteger(hour, 8));
        calendar.set(Calendar.MINUTE, Integer.getInteger(min, 0));
        calendar.set(Calendar.SECOND, 0);

        //Setting intent to class where Alarm broadcast message will be handled
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(Constants.EXTRA_TYPE, Constants.TYPE_RELEASE);

        //Setting alarm pending intent
        alarmIntentRTC = PendingIntent.getBroadcast(context, Constants.ALARM_TYPE_RTC, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //getting instance of AlarmManager service
        alarmManagerRTC = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        //Setting alarm to wake up device every day for clock time.
        //AlarmManager.RTC_WAKEUP is responsible to wake up device for sure, which may not be good practice all the time.
        if (alarmManagerRTC != null) {
            alarmManagerRTC.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntentRTC);
        }
    }

    /**
     * here we will cancel alarms that been set in past
     */
    public static void cancelAlarm() {
        if (alarmManagerRTC != null) {
            alarmManagerRTC.cancel(alarmIntentRTC);
        }
    }
}

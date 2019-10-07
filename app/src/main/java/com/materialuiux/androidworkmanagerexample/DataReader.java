package com.materialuiux.androidworkmanagerexample;

import android.content.Context;
import com.materialuiux.androidworkmanagerexample.model.Quotes;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class DataReader {

    /**
     * here we will get all the quotes and display it in the main activity.
     */
    public static ArrayList<Quotes> getAllQuotes(Context mContext) {
        ArrayList<Quotes> quotesArrayList = new ArrayList<>();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = mContext.getResources().getAssets().open("quotes.txt", Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            while (input.readLine() != null) {
                StringTokenizer st = new StringTokenizer(input.readLine(), ";");
                String quotes = st.nextToken();
                String publisher = st.nextToken();
                quotesArrayList.add(new Quotes(quotes, publisher));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return quotesArrayList;
    }

    /**
     * here we are going to get one random quotes for the notification.
     */
    public static Quotes getRandomQuotes(Context mContext) {
        ArrayList<Quotes> quotesArrayList = new ArrayList<>();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = mContext.getResources().getAssets().open("quotes.txt", Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            while (input.readLine() != null) {
                StringTokenizer st = new StringTokenizer(input.readLine(), ";");
                String quotes = st.nextToken();
                String publisher = st.nextToken();
                quotesArrayList.add(new Quotes(quotes, publisher));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        Random rn = new Random();
        int range = quotesArrayList.size() - 1;
        int randomNum = rn.nextInt(range) + 1;
        return quotesArrayList.get(randomNum);
    }

}

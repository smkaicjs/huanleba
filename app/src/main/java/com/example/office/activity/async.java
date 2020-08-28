package com.example.office.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.office.mcontext.Mycontext;

import java.io.File;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class async extends AsyncTask<String, Integer, File> {

    @Override
    protected File doInBackground(String... strings) {
        int size = strings.length;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(strings[0]).build();
        publishProgress(size);
        return null;
    }

    @Override
    protected void onPreExecute() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Mycontext.getcontext());
        WindowManager windowManager = (WindowManager) Mycontext.getcontext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);


        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onCancelled(File file) {
        super.onCancelled(file);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}

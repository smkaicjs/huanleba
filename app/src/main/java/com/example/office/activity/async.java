package com.example.office.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.office.mcontext.Mycontext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class async extends AsyncTask<String, Integer, Integer> {


    public static final int TYPY_fail = 0;
    public static final int TYPY_succ = 1;
    public static final int TYPY_pause = 2;
    public static final int TYPY_canceled = 3;
    private int lastprocess;

    public boolean isCancele() {
        return isCancele;
    }

    public void setCancele(boolean cancele) {
        isCancele = cancele;
    }

    public boolean isPause() {
        return isPause;
    }
    public void setPause(boolean pause) {
        isPause = pause;
    }

    private boolean isCancele = false;
    private boolean isPause = false;
    private DownloaderLinster downloaderLinster;
    public async (DownloaderLinster downloaderLinster){
        this.downloaderLinster = downloaderLinster;

    }
    @Override
    protected Integer doInBackground(String... strings) {
        String Downurl = strings[0];
        RandomAccessFile savefile = null;
        InputStream inputStream = null;
        String filename = Downurl.substring(Downurl.lastIndexOf("/"));
        String dircetory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        File file = new File(dircetory + filename);
        Long filelength = 0L;
        if (file.exists()){
            filelength = file.length();
        }
        long contentlength = 0L;
        try {
            contentlength = getcontentlength(Downurl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contentlength == 0L){
            return TYPY_fail;
        }else if (contentlength == filelength){
            return TYPY_succ;
        }
        OkHttpClient client = new OkHttpClient();//xiazai
        Request request = new Request.Builder().url(Downurl).addHeader("RANGE","bytes="+
                filelength+"-").build();
        try {
            Response response = client.newCall(request).execute();
            inputStream = response.body().byteStream();
            savefile = new RandomAccessFile(file,"rw");
            byte[] bytes = new byte[1024];
            savefile.seek(filelength);
            int total = 0;
            int len;
            while ((len = inputStream.read(bytes))!= -1){
                if (isCancele){
                    return TYPY_canceled;
                }else if (isPause){
                    return TYPY_pause;
                }else {
                    total += len;
                    savefile.write(bytes,0,len);
                    int pro = (int) ((filelength+total)/contentlength)*100;
                    publishProgress(pro);
                }
            }
            response.close();
            return TYPY_succ;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (isCancele&&file!=null){
                file.delete();
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return TYPY_fail;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPY_canceled:
                downloaderLinster.cancle();
                break;
            case TYPY_succ:
                downloaderLinster.success();
                break;
            case TYPY_fail:
                downloaderLinster.failed();
                break;
            case TYPY_pause:
                downloaderLinster.pause();
                break;

        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int proess = values[0];
        if (proess>lastprocess){
            downloaderLinster.onproess(proess);
            lastprocess = proess;
        }

    }
    public interface DownloaderLinster {
        void pause();
        void success();
        void cancle();
        void failed();
        void onproess(int proess);
    }
    private long getcontentlength(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response res = client.newCall(request).execute();
        if (res!=null&&res.isSuccessful()){
            Long contentlength = res.body().contentLength();
            res.close();
            return contentlength;
        }
        return 0L;
    }
}

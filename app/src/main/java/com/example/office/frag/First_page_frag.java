package com.example.office.frag;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.example.office.R;
import com.example.office.activity.User_firstpage;
import com.example.office.activity.mywebview;
import com.example.office.mcontext.Mycontext;
import com.example.office.service.DownloadServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class First_page_frag extends Fragment implements View.OnClickListener{
    private mywebview webView;
    private SwipeRefreshLayout refreshLayout;
    private String url;
    public static final String TAG = "下拉测试";
    private MyWebChromeClient mywebchromeclict;
    private FrameLayout myframe;
    private FloatingActionButton button;
    private FragmentActivity activity;
    public static final int LOADWEB = 1;
    public static final int RELOAD = 2;
    private Handler mhhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case LOADWEB:
                    seturl(url);
                    break;
                case RELOAD:
                    webView.loadUrl(webView.copyBackForwardList().getCurrentItem().getUrl());
                    Log.d(TAG, "handleMessage: 到了reload");
//                    webView.reload();
                    webView.requestFocus();
                    refreshLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
//    private DownloadServices.binder mbinder;
////    private ServiceConnection connection = new ServiceConnection() {
////        @Override
////        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
////            mbinder = (DownloadServices.binder) iBinder;
////        }
////
////        @Override
////        public void onServiceDisconnected(ComponentName componentName) {
////
////        }
////    };



    public First_page_frag(String url) {
        this.url = url;
    }

    public First_page_frag() {
    }

    private Intent intent;
    private int startx,endx;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(Mycontext.getcontext()).inflate(R.layout.page1_frag,
                container,false);
        activity = getActivity();
        button = view.findViewById(R.id.refresh_button);

        webView = view.findViewById(R.id.web_view);





        refreshLayout = view.findViewById(R.id.swipe_refresh);
        refreshLayout.setColorSchemeResources(R.color.color1);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               mhhandler.sendMessage(mhhandler.obtainMessage(RELOAD));
           }

        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(webView.copyBackForwardList().getCurrentItem().getUrl());
            }
        });
        webView.setOnScrollListener(new mywebview.IScrollListener() {
            @Override
            public void onScrollChanged(int scrollY) {
                if (scrollY == 0) {
                    //开启下拉刷新
                    refreshLayout.setEnabled(true);
                } else {
                    //关闭下拉刷新
                    refreshLayout.setEnabled(false);
                }
            }
        });



//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        startx = (int) motionEvent.getX();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        endx = (int) motionEvent.getX();
//                        if (endx-startx > 60 && webView.canGoBack()){
//                            webView.goBack();
//                        }else if (endx-startx < 60 && webView.canGoForward()){
//                            webView.goForward();
//                        }else {
//
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });
        myframe = view.findViewById(R.id.frame_page_my);



//        if (ContextCompat.checkSelfPermission(Mycontext.getcontext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET,
//                    Manifest.permission.READ_CONTACTS,
//                    Manifest.permission.FOREGROUND_SERVICE,
//                    Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//        }

        mhhandler.sendMessage(mhhandler.obtainMessage(LOADWEB));
        Button go_back = view.findViewById(R.id.go_back);
        Button go_to = view.findViewById(R.id.go_to);
        go_to.setBackgroundColor(getResources().getColor(R.color.palevioletred));
        go_back.setBackgroundColor(getResources().getColor(R.color.saddlebrown));

//        WindowManager windowManager = (WindowManager) Mycontext.getcontext().getSystemService(Context.WINDOW_SERVICE);
//        windowManager.addView(LayoutInflater.from(Mycontext.getcontext()).inflate(R.layout.loadurl,null), new WindowManager.LayoutParams(
//                MATCH_PARENT,MATCH_PARENT
//        ));


//        intent = new Intent(Mycontext.getcontext(),DownloadServices.class);
//        getActivity().startService(intent);
//        getActivity().bindService(intent,connection,Context.BIND_AUTO_CREATE);
        go_back.setAlpha(0.3F);
        go_to.setAlpha(0.3F);
        go_back.setOnClickListener(this);
        go_to.setOnClickListener(this);
        return view;
    }
    public WebView getWebView(){return webView;}

    private void seturl (final String url){
        webView.setWebChromeClient(mywebchromeclict);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        final User_firstpage act = (User_firstpage) getActivity();
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {


                Log.d("获取url测试", "onDownloadStart获取的url: "+s);
//                mbinder.startdownload(s);
                act.downloadres(s);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        try {
            String lasturl = webView.copyBackForwardList().getCurrentItem().getUrl();
            SharedPreferences.Editor editor = Mycontext.getcontext().getSharedPreferences("LASTURL", Context.MODE_PRIVATE).edit();
            editor.putString("URL",lasturl);
            editor.commit();
            webView.clearCache(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getActivity().unbindService(connection);

    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 2:
//                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//
//
//                }else{
//                    Toast.makeText(Mycontext.getcontext(),"权限不足无法下载",Toast.LENGTH_SHORT).show();
//                }
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.go_back:
                if (webView.canGoBack()){
                    webView.goBack();
                }else {
                    webView.loadUrl("http://www.baidu.com");
                }
                break;
            case R.id.go_to:
                if (webView.canGoForward()){
                    webView.goForward();
                }
                break;
        }
    }
    class MyWebChromeClient extends WebChromeClient{
        private CustomViewCallback mCallBack;

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            fullScreen();
            webView.setVisibility(View.GONE);
            myframe.setVisibility(View.VISIBLE);
            myframe.addView(view);
            mCallBack=callback;
            super.onShowCustomView(view, callback);

        }


        @Override
        public void onHideCustomView() {
            fullScreen();
            if (mCallBack!=null){
                mCallBack.onCustomViewHidden();
            }
            webView.setVisibility(View.VISIBLE);
            myframe.removeAllViews();
            myframe.setVisibility(View.GONE);
            super.onHideCustomView();
        }


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress < 100) {
                refreshLayout.setRefreshing(true);
                Log.d(TAG, "handleMessage: 到了if");
            } else {
                Log.d(TAG, "handleMessage: 到了else");
                refreshLayout.setRefreshing(false);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private void fullScreen() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
    //



}

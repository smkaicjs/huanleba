package com.example.office.frag;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.office.R;
import com.example.office.mcontext.Mycontext;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class First_page_frag extends Fragment implements View.OnClickListener{
    private WebView webView;
    private String url;
    private MyWebChromeClient mywebchromeclict;
    private FrameLayout myframe;
    private Activity activity;



    public First_page_frag(String url) {
        this.url = url;
    }

    public First_page_frag() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(Mycontext.getcontext()).inflate(R.layout.page1_frag,
                container,false);
        activity = getActivity();
        webView = view.findViewById(R.id.web_view);
        myframe = view.findViewById(R.id.frame_page_my);
        seturl(url);
        Button go_back = view.findViewById(R.id.go_back);
        Button go_to = view.findViewById(R.id.go_to);
        go_to.setBackgroundColor(getResources().getColor(R.color.palevioletred));
        go_back.setBackgroundColor(getResources().getColor(R.color.saddlebrown));

//        WindowManager windowManager = (WindowManager) Mycontext.getcontext().getSystemService(Context.WINDOW_SERVICE);
//        windowManager.addView(LayoutInflater.from(Mycontext.getcontext()).inflate(R.layout.loadurl,null), new WindowManager.LayoutParams(
//                MATCH_PARENT,MATCH_PARENT
//        ));


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
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        String lasturl = webView.copyBackForwardList().getCurrentItem().getUrl();
        SharedPreferences.Editor editor = Mycontext.getcontext().getSharedPreferences("LASTURL", Context.MODE_PRIVATE).edit();
        editor.putString("URL",lasturl);
        editor.commit();
    }


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
    }
    private void fullScreen() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

}

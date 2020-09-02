package com.example.office.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class mywebview extends WebView {

    public mywebview(@NonNull Context context) {
        super(context);
    }

    public mywebview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public mywebview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public mywebview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollListener != null) {
            mScrollListener.onScrollChanged(t);
        }
    }
    public interface IScrollListener {
        void onScrollChanged(int scrollY);
    }

    private IScrollListener mScrollListener;

    public void setOnScrollListener(IScrollListener listener) {
        mScrollListener = listener;
    }
}

package com.grimar.feedy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.roudj.feedy.R;

public class FragmentWebView extends Fragment {

    private String curURL;

    public FragmentWebView() {
    }

    public void init(String url) {
        curURL = url;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_content, container, false);

        if (curURL != null) {

            WebView webview = (WebView) view.findViewById(R.id.web_view);
            webview.setWebViewClient(new webClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(curURL);
        }
        setRetainInstance(true);
        return view;

    }

    public void updateUrl(String url) {
        curURL = url;
        WebView webview = (WebView) getView().findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }

    private class webClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return false;
        }
    }
}


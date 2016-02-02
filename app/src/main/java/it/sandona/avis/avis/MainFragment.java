package it.sandona.avis.avis;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import it.sandona.avis.avis.Parcelable.WebViewParcelable;
import it.sandona.avis.avis.helper.Utils;

/**
 * Created by Root on 28/01/2016.
 */
public class MainFragment extends Fragment {

    private WebView webView;
//    private ProgressBar progress;
    private SwipeRefreshLayout swipeRefreshLayout;

    Utils utils;
    WebViewParcelable model;

    String subDomain;
    int bgColor;
    String title;
    String localUrl;

    Bundle bundle;

    boolean started = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        bundle = this.getArguments();
        if (bundle != null) {
            model = bundle.getParcelable("WebView");
            subDomain = model.getSubDomain();
            bgColor = model.getColor();
            title = model.getTitle();
            localUrl = model.getLocalUrl();
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        webView = (WebView) view.findViewById(R.id.webView);
//        progress = (ProgressBar) view.findViewById(R.id.progressBar);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }

        });

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    refreshData();
                } catch (RuntimeException e) {
                    showActionBarProgress(false);
                    throw e;
                }
            }
        });

        utils = new Utils(getActivity().getApplicationContext());

        if (utils.isConnected()) {
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            webView.loadUrl(Utils.protocol + Utils.domain + subDomain + Utils.css);
        } else {
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.setBackgroundColor(bgColor); //<-- Color to transparent
            if (!localUrl.equals("")) {
                webView.loadUrl(localUrl);
            } else {
                webView.setBackgroundResource(R.drawable.offline);
            }
            Toast.makeText(getActivity().getApplicationContext(), "Offline", Toast.LENGTH_LONG).show();
        }
        return view;
    }

    public void refreshData() {
        if (utils.isConnected()) {
            webView.loadUrl(webView.getUrl());
            showActionBarProgress(true);
        }
        showActionBarProgress(false);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().equals(Utils.domain)) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            showActionBarProgress(false);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (utils.isConnected()) {
                if (!started) {
                    showActionBarProgress(true);
                    started = !started;
                }
            }
        }
    }

    public void showActionBarProgress(boolean value) {
/*        if (value) {
            progress.setVisibility(View.VISIBLE);
            progress.setProgress(0);
        } else {
            progress.setVisibility(View.GONE);
            progress.setProgress(100);
        }*/
        swipeRefreshLayout.setRefreshing(value);
        swipeRefreshLayout.setEnabled(!value);
    }
}
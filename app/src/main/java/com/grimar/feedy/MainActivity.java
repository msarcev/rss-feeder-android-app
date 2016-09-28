package com.grimar.feedy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.grimar.feedy.adapters.LeftMenuAdapter;
import com.grimar.feedy.adapters.PortalsListAdapter;
import com.grimar.feedy.adapters.QuickLinksAdapter;
import com.grimar.feedy.adapters.StringListItem;
import com.grimar.feedy.data.News;
import com.grimar.feedy.fragments.FragmentArticlesList;
import com.grimar.feedy.fragments.FragmentHiddenList;
import com.grimar.feedy.fragments.FragmentPortali;
import com.grimar.feedy.fragments.FragmentWebView;
import com.grimar.feedy.rss.RSSFeed;
import com.grimar.feedy.rss.RssFetcher;
import com.roudj.feedy.R;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private FragmentManager fm;
    private FragmentTransaction ft;

    private FragmentWebView content;
    private FragmentArticlesList article_list;
    private FragmentPortali frag_portali;
    private FragmentHiddenList hidden_list;
    private ListView mArticlesListView;
    private ListView mPortaliListView;
    private WebView mWebView;
    private TextView textKategorija;
    private TextView textPortal;

    private String naslovPortal;
    private String rssUrlString;
    private String tempUnicode;
    private String tempPortalName;
    private String lastUrl;
    private RSSFeed myRssFeed = null;
    private ArrayList<StringListItem> articlesArrayList = null;
    private ArrayList<StringListItem> portalsArray = null;
    private ArrayList<StringListItem> quickLinksArray = null;
    private int lastMenuChoice = 0;

    private Button toggleListbtn;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;

    private LeftMenuAdapter articleAdapter;
    private PortalsListAdapter portalsAdapter;
    private QuickLinksAdapter quickLinksAdapter;

    private Boolean menuListExpanded = true;
    private Boolean menuPortaliListExpanded = false;

    private int deviceWidth;
    private int deviceHeight;

    private int MINSWIPE;

    private GestureDetector gesturedetector = null;
    private View swipe_layout;
    private ClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        clickHandler = new ClickHandler(this);

        if (savedInstanceState == null) {

            setContentView(R.layout.main_layout);
            setTempUnicode(new News().getPortalEncoding().get(0));
            setRssUrlString(News.getPortalURL().get(0));
            setNaslovPortal(News.getPortalName().get(0));
            setPortalsArray(new ArrayList<StringListItem>());
            setArticlesArrayList(new ArrayList<StringListItem>());
            setQuickLinksArray(new ArrayList<StringListItem>());

            setArticleAdapter(new LeftMenuAdapter(getApplicationContext(), getArticlesArrayList()));
            setPortalsAdapter(new PortalsListAdapter(getApplicationContext(), getPortalsArray()));
            setQuickLinksAdapter(new QuickLinksAdapter(getApplicationContext(), getQuickLinksArray()));

            setContent(new FragmentWebView());
            getContent().init("www.google.com");
            setArticle_list(new FragmentArticlesList());
            setFrag_portali(new FragmentPortali());
            setHidden_list(new FragmentHiddenList());

            fm = getSupportFragmentManager();
            ft = fm.beginTransaction()
                    .add(R.id.content_holder, getContent(), "FragmentContent")
                    .add(R.id.list_fragment_holder, getArticle_list(), "FragmentArticles")
                    .add(R.id.holder1, getFrag_portali(), "FragmentPortali")
                    .add(R.id.list_fragment_hidden_holder, getHidden_list(), "FragmentHiddenList")
                    .hide(getFrag_portali())
                    .hide(getHidden_list());
            ft.commit();

            setTempPortalName(getPortalTitle());
            new RssFetcher(this).execute();
            displayToast(getResources().getString(R.string.load_text) + " " + getPortalTitle());

        } else {

            setContentView(R.layout.main_layout);

            initButtons();
            restoreSavedInstances(savedInstanceState);

            setArticleAdapter(new LeftMenuAdapter(getApplicationContext(), getArticlesArrayList()));
            setPortalsAdapter(new PortalsListAdapter(getApplicationContext(), getPortalsArray()));
            setQuickLinksAdapter(new QuickLinksAdapter(getApplicationContext(), getQuickLinksArray()));

            fm = getSupportFragmentManager();
            setContent((FragmentWebView) fm.findFragmentByTag("FragmentContent"));
            setArticle_list((FragmentArticlesList) fm.findFragmentByTag("FragmentArticles"));
            setFrag_portali((FragmentPortali) fm.findFragmentByTag("FragmentPortali"));
            setHidden_list((FragmentHiddenList) fm.findFragmentByTag("FragmentHiddenList"));

            if (getMenuListExpanded()) {

            } else if (!getMenuListExpanded()) {
                LinearLayout content_holder = (LinearLayout) findViewById(R.id.content_holder);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) content_holder.getLayoutParams();
                params.addRule(RelativeLayout.RIGHT_OF, R.id.list_fragment_hidden_holder);
                content_holder.setLayoutParams(params);
                ft = fm.beginTransaction()
                        .hide(getArticle_list());
                ft.commit();
                toggleArticleList();

            }
            if (getMenuPortaliListExpanded()) {
                togglePortaliList();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        setMinSwipe();

        initButtons();

        FrameLayout swipe_stripe = (FrameLayout) findViewById(R.id.swipe_stripe);
        swipe_stripe.setOnClickListener(this);
        FrameLayout hidden_swipe_stripe = (FrameLayout) findViewById(R.id.hidden_list_frame);
        hidden_swipe_stripe.setOnClickListener(this);

        setTextKategorija((TextView) this.findViewById(R.id.kategorija));
        setTextPortal((TextView) this.findViewById(R.id.text_portal));
        getTextPortal().setText(getPortalTitle());

        swipe_layout = (RelativeLayout) findViewById(R.id.swipe_layout);
        gesturedetector = new GestureDetector(this, new FlingGestureResolver(this));
        swipe_layout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesturedetector.onTouchEvent(event);
                return true;
            }
        });

        setmArticlesListView((ListView) findViewById(R.id.articles_list_view));
        setmPortaliListView((ListView) findViewById(R.id.portali_list_view));
        getmArticlesListView().setAdapter(getArticleAdapter());
        getmPortaliListView().setAdapter(getPortalsAdapter());

        //Zooma WebView
        setmWebView((WebView) findViewById(R.id.web_view));
        WebSettings webSettings = getmWebView().getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        if (getLastUrl() != null) {
            getContent().updateUrl(getLastUrl());
        } else if (!getArticlesArrayList().isEmpty()) {
            getContent().updateUrl(getArticlesArrayList().get(0).getUrl());
        }

        //Listener left listview
        getmArticlesListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                vibrate(60);
                if (getMenuPortaliListExpanded()) {
                    togglePortaliList();
                    resetPortaliClicked();
                }
                String tempurl = ((StringListItem) getArticleAdapter().getItem(position)).getUrl();
                setLastUrl(tempurl);
                setmWebView((WebView) findViewById(R.id.web_view));
                getmWebView().loadUrl(tempurl);
            }
        });

    }

    public void initButtons() {
        setToggleListbtn((Button) findViewById(R.id.hide_btn));
        setBtn1((Button) findViewById(R.id.btn1));
        setBtn2((Button) findViewById(R.id.btn2));
        setBtn3((Button) findViewById(R.id.btn3));
        setBtn4((Button) findViewById(R.id.btn4));
        setBtn5((Button) findViewById(R.id.btn5));
        setBtn6((Button) findViewById(R.id.btn6));
        setBtn7((Button) findViewById(R.id.btn7));

        getToggleListbtn().setOnClickListener(this);
        getBtn1().setOnClickListener(this);
        getBtn2().setOnClickListener(this);
        getBtn3().setOnClickListener(this);
        getBtn4().setOnClickListener(this);
        getBtn5().setOnClickListener(this);
        getBtn6().setOnClickListener(this);
        getBtn7().setOnClickListener(this);
    }

    public void setMinSwipe() {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        setDeviceWidth(displaymetrics.widthPixels);
        setDeviceHeight(displaymetrics.heightPixels);
        setMINSWIPE(getDeviceWidth() * 23 / 100);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("portalsArray", getPortalsArray());
        outState.putParcelableArrayList("quickLinksArray", getQuickLinksArray());
        outState.putParcelableArrayList("articlesArrayList", getArticlesArrayList());
        outState.putBoolean("menuListExpanded", getMenuListExpanded());
        outState.putBoolean("menuPortaliListExpanded", getMenuPortaliListExpanded());

        outState.putString("rssUrlString", getRssUrlString());
        outState.putString("tempPortalName", getTempPortalName());
        outState.putString("tempUnicode", getTempUnicode());
        outState.putString("naslovPortal", getPortalTitle());
        outState.putString("tempurl", getLastUrl());

    }

    public void restoreSavedInstances(Bundle savedInstanceState) {

        ArrayList<StringListItem> portaliArrayList = savedInstanceState.getParcelableArrayList("portalsArray");
        ArrayList<StringListItem> articlesArrayList = savedInstanceState.getParcelableArrayList("articlesArrayList");
        ArrayList<StringListItem> quickLinksArray = savedInstanceState.getParcelableArrayList("quickLinksArray");
        setPortalsArray(portaliArrayList);
        setArticlesArrayList(articlesArrayList);
        setQuickLinksArray(quickLinksArray);
        setMenuListExpanded(savedInstanceState.getBoolean("menuListExpanded"));
        setMenuPortaliListExpanded(savedInstanceState.getBoolean("menuPortaliListExpanded"));
        setRssUrlString(savedInstanceState.getString("rssUrlString"));
        setTempPortalName(savedInstanceState.getString("tempPortalName"));
        setTempUnicode(savedInstanceState.getString("tempUnicode"));
        setNaslovPortal(savedInstanceState.getString("naslovPortal"));
        setLastUrl(savedInstanceState.getString("tempurl"));
    }

    public void updatePortaliListeners() {
        getmPortaliListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {
                vibrate(60);
                if (getMenuPortaliListExpanded()) {
                    togglePortaliList();
                }
                if (getMenuListExpanded()) {
                    toggleArticleList();
                }
                resetPortaliClicked();
                getArticlesArrayList().clear();
                String toast = getResources().getString(R.string.load_text) + " " + ((StringListItem) getPortalsAdapter().getItem(position)).getString();
                displayToast(toast);
                setRssUrlString(((StringListItem) getPortalsAdapter().getItem(position)).getUrl());
                setTempPortalName(((StringListItem) getPortalsAdapter().getItem(position)).getString());
                setTempUnicode(((StringListItem) getPortalsAdapter().getItem(position)).unicode);
                setNaslovPortal(getTempPortalName());
                new RssFetcher(MainActivity.this).execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getMenuPortaliListExpanded()) {
            togglePortaliList();
            resetPortaliClicked();
        } else if (getMenuListExpanded()) {
            toggleArticleList();
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                WebView webview = (WebView) findViewById(R.id.web_view);
                String s = webview.getUrl();
                s += "\n \n \n";
                s += getResources().getString(R.string.app_html);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, s);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
        }
        return true;
    }

    public void resetPortaliClicked() {
        getBtn1().setBackgroundResource(R.drawable.cat_item_mid);
        getBtn2().setBackgroundResource(R.drawable.cat_item_mid);
        getBtn3().setBackgroundResource(R.drawable.cat_item_mid);
        getBtn4().setBackgroundResource(R.drawable.cat_item_mid);
        getBtn5().setBackgroundResource(R.drawable.cat_item_mid);
        getBtn6().setBackgroundResource(R.drawable.cat_item_mid);
        getBtn7().setBackgroundResource(R.drawable.cat_item_mid);
    }

    public void togglePortaliList() {

        if (!getMenuPortaliListExpanded()) {

            fm = getSupportFragmentManager();
            ft = fm.beginTransaction()
                    .setCustomAnimations(R.anim.enter_portali_list, R.anim.exit_portali_list)
                    .show(getFrag_portali());
            ft.commit();

        } else {

            fm = getSupportFragmentManager();
            ft = fm.beginTransaction()
                    .setCustomAnimations(R.anim.enter_portali_list, R.anim.exit_portali_list)
                    .hide(getFrag_portali());
            ft.commit();

        }

        setMenuPortaliListExpanded(!getMenuPortaliListExpanded());

    }

    public void toggleArticleList() {
        if (getMenuListExpanded()) {

            ft = fm.beginTransaction()
                    .setCustomAnimations(0, 0)
                    .show(getHidden_list());
            ft.commit();

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    LinearLayout content_holder = (LinearLayout) findViewById(R.id.content_holder);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) content_holder.getLayoutParams();
                    params.addRule(RelativeLayout.RIGHT_OF, R.id.list_fragment_hidden_holder);
                    content_holder.setLayoutParams(params);

                    ft = fm.beginTransaction()
                            .setCustomAnimations(R.anim.expand_article_list, R.anim.collapse_article_list)
                            .hide(getArticle_list());
                    ft.commit();
                }

                ;
            }, 10);


        } else {

            ft = fm.beginTransaction()
                    .setCustomAnimations(R.anim.expand_article_list, R.anim.collapse_article_list)
                    .show(getArticle_list());
            ft.commit();

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    LinearLayout content_holder = (LinearLayout) findViewById(R.id.content_holder);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) content_holder.getLayoutParams();
                    params.addRule(RelativeLayout.RIGHT_OF, R.id.list_fragment_holder);
                    content_holder.setLayoutParams(params);


                    ft = fm.beginTransaction()
                            .setCustomAnimations(0, 0)
                            .hide(getHidden_list());
                    ft.commit();
                }

                ;
            }, 260);


        }
        setMenuListExpanded(!getMenuListExpanded());
    }

    public void displayToast(String s) {
        LayoutInflater inflater = getLayoutInflater();
        // Inflate the Layout
        View layout = inflater.inflate(R.layout.my_custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_layout));

        TextView text = (TextView) layout.findViewById(R.id.textToShow);
        // Set the Text to show in TextView
        text.setText(s);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void vibrate(int milisec) {
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(milisec);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gesturedetector.onTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        clickHandler.processClick(v);
    }

    public int getMINSWIPE() {
        return MINSWIPE;
    }

    public void setMINSWIPE(int MINSWIPE) {
        this.MINSWIPE = MINSWIPE;
    }

    public LeftMenuAdapter getArticleAdapter() {
        return articleAdapter;
    }

    public void setArticleAdapter(LeftMenuAdapter articleAdapter) {
        this.articleAdapter = articleAdapter;
    }

    public PortalsListAdapter getPortalsAdapter() {
        return portalsAdapter;
    }

    public void setPortalsAdapter(PortalsListAdapter portalsAdapter) {
        this.portalsAdapter = portalsAdapter;
    }

    public QuickLinksAdapter getQuickLinksAdapter() {
        return quickLinksAdapter;
    }

    public void setQuickLinksAdapter(QuickLinksAdapter quickLinksAdapter) {
        this.quickLinksAdapter = quickLinksAdapter;
    }

    public Boolean getMenuListExpanded() {
        return menuListExpanded;
    }

    public void setMenuListExpanded(Boolean menuListExpanded) {
        this.menuListExpanded = menuListExpanded;
    }

    public Boolean getMenuPortaliListExpanded() {
        return menuPortaliListExpanded;
    }

    public void setMenuPortaliListExpanded(Boolean menuPortaliListExpanded) {
        this.menuPortaliListExpanded = menuPortaliListExpanded;
    }

    public int getDeviceWidth() {
        return deviceWidth;
    }

    public void setDeviceWidth(int deviceWidth) {
        this.deviceWidth = deviceWidth;
    }

    public int getDeviceHeight() {
        return deviceHeight;
    }

    public void setDeviceHeight(int deviceHeight) {
        this.deviceHeight = deviceHeight;
    }

    public FragmentWebView getContent() {
        return content;
    }

    public void setContent(FragmentWebView content) {
        this.content = content;
    }

    public FragmentArticlesList getArticle_list() {
        return article_list;
    }

    public void setArticle_list(FragmentArticlesList article_list) {
        this.article_list = article_list;
    }

    public FragmentPortali getFrag_portali() {
        return frag_portali;
    }

    public void setFrag_portali(FragmentPortali frag_portali) {
        this.frag_portali = frag_portali;
    }

    public FragmentHiddenList getHidden_list() {
        return hidden_list;
    }

    public void setHidden_list(FragmentHiddenList hidden_list) {
        this.hidden_list = hidden_list;
    }

    public ListView getmArticlesListView() {
        return mArticlesListView;
    }

    public void setmArticlesListView(ListView mArticlesListView) {
        this.mArticlesListView = mArticlesListView;
    }

    public ListView getmPortaliListView() {
        return mPortaliListView;
    }

    public void setmPortaliListView(ListView mPortaliListView) {
        this.mPortaliListView = mPortaliListView;
    }

    public WebView getmWebView() {
        return mWebView;
    }

    public void setmWebView(WebView mWebView) {
        this.mWebView = mWebView;
    }

    public TextView getTextKategorija() {
        return textKategorija;
    }

    public void setTextKategorija(TextView textKategorija) {
        this.textKategorija = textKategorija;
    }

    public TextView getTextPortal() {
        return textPortal;
    }

    public void setTextPortal(TextView textPortal) {
        this.textPortal = textPortal;
    }

    public String getPortalTitle() {
        return naslovPortal;
    }

    public void setNaslovPortal(String naslovPortal) {
        this.naslovPortal = naslovPortal;
    }

    public String getRssUrlString() {
        return rssUrlString;
    }

    public void setRssUrlString(String rssUrlString) {
        this.rssUrlString = rssUrlString;
    }

    public String getTempUnicode() {
        return tempUnicode;
    }

    public void setTempUnicode(String tempUnicode) {
        this.tempUnicode = tempUnicode;
    }

    public String getTempPortalName() {
        return tempPortalName;
    }

    public void setTempPortalName(String tempPortalName) {
        this.tempPortalName = tempPortalName;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    public RSSFeed getMyRssFeed() {
        return myRssFeed;
    }

    public void setMyRssFeed(RSSFeed myRssFeed) {
        this.myRssFeed = myRssFeed;
    }

    public ArrayList<StringListItem> getArticlesArrayList() {
        return articlesArrayList;
    }

    public void setArticlesArrayList(ArrayList<StringListItem> articlesArrayList) {
        this.articlesArrayList = articlesArrayList;
    }

    public ArrayList<StringListItem> getPortalsArray() {
        return portalsArray;
    }

    public void setPortalsArray(ArrayList<StringListItem> portalsArray) {
        this.portalsArray = portalsArray;
    }

    public ArrayList<StringListItem> getQuickLinksArray() {
        return quickLinksArray;
    }

    public void setQuickLinksArray(ArrayList<StringListItem> quickLinksArray) {
        this.quickLinksArray = quickLinksArray;
    }

    public int getLastMenuChoice() {
        return lastMenuChoice;
    }

    public void setLastMenuChoice(int lastMenuChoice) {
        this.lastMenuChoice = lastMenuChoice;
    }

    public Button getToggleListbtn() {
        return toggleListbtn;
    }

    public void setToggleListbtn(Button toggleListbtn) {
        this.toggleListbtn = toggleListbtn;
    }

    public Button getBtn1() {
        return btn1;
    }

    public void setBtn1(Button btn1) {
        this.btn1 = btn1;
    }

    public Button getBtn2() {
        return btn2;
    }

    public void setBtn2(Button btn2) {
        this.btn2 = btn2;
    }

    public Button getBtn3() {
        return btn3;
    }

    public void setBtn3(Button btn3) {
        this.btn3 = btn3;
    }

    public Button getBtn4() {
        return btn4;
    }

    public void setBtn4(Button btn4) {
        this.btn4 = btn4;
    }

    public Button getBtn5() {
        return btn5;
    }

    public void setBtn5(Button btn5) {
        this.btn5 = btn5;
    }

    public Button getBtn6() {
        return btn6;
    }

    public void setBtn6(Button btn6) {
        this.btn6 = btn6;
    }

    public Button getBtn7() {
        return btn7;
    }

    public void setBtn7(Button btn7) {
        this.btn7 = btn7;
    }
}
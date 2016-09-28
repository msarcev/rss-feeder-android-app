package com.grimar.feedy.rss;

import android.os.AsyncTask;

import com.grimar.feedy.MainActivity;
import com.grimar.feedy.adapters.StringListItem;
import com.roudj.feedy.R;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Mario on 27.9.2016..
 */
public class RssFetcher extends AsyncTask<Void, Void, Void> {

    public String imePortala;
    private MainActivity mainActivity;

    public RssFetcher(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            imePortala = mainActivity.getTempPortalName();
            URL rssUrl = new URL(mainActivity.getRssUrlString());
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser xmlParser = parserFactory.newSAXParser();
            XMLReader xmlReader = xmlParser.getXMLReader();
            RSSHandler rssHandler = new RSSHandler();
            xmlReader.setContentHandler(rssHandler);

            //Encofing
            InputSource myInputSource = new InputSource(new InputStreamReader(rssUrl.openStream(), Charset.forName(mainActivity.getTempUnicode())));
            xmlReader.parse(myInputSource);

            mainActivity.setMyRssFeed(rssHandler.getFeed());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        if (mainActivity.getMyRssFeed() != null) {
            mainActivity.getTextPortal().setText(imePortala);
            mainActivity.getArticlesArrayList().clear();

            int i = mainActivity.getMyRssFeed().getList().size();
            for (int j = 0; j < i; j++) {
                String A = mainActivity.getMyRssFeed().getItem(j).getTitle();
                String B = mainActivity.getMyRssFeed().getItem(j).getLink();
                mainActivity.getArticlesArrayList().add((new StringListItem(A, B)));
            }

            mainActivity.setLastUrl(mainActivity.getMyRssFeed().getItem(0).getLink());
            mainActivity.getmWebView().loadUrl(mainActivity.getLastUrl());

            if (!mainActivity.getMenuListExpanded()) {
                mainActivity.toggleArticleList();
            }

        } else {
            mainActivity.vibrate(300);
            mainActivity.displayToast(mainActivity.getResources().getString(R.string.error_msg));
        }

        super.onPostExecute(result);
    }
}

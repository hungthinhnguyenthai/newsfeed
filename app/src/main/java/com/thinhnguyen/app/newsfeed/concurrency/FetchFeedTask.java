package com.thinhnguyen.app.newsfeed.concurrency;

import android.content.Context;
import android.util.Xml;

import com.thinhnguyen.app.newsfeed.model.Channel;
import com.thinhnguyen.app.newsfeed.model.Item;
import com.thinhnguyen.app.newsfeed.model.ItemWrapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author thinh.nguyen
 */
public class FetchFeedTask extends Thread {

    private Context parent, basContext;
    private Channel info;
    private ItemWrapper itemWrapper = new ItemWrapper();

    public FetchFeedTask(Context parent, Context basContext, Channel info) {
        this.parent = parent;
        this.basContext = basContext;
        this.info = info;
    }

    @Override
    public void run() {
        String title = null;
        String link = null;
        String description = null;

        boolean isItem = false;
        URL url = null;
        InputStream inputStream = null;
        try {

            url = new URL(info.getUrl());
            inputStream = url.openConnection().getInputStream();
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if(name == null)
                    continue;

                if(eventType == XmlPullParser.END_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")) {
                    description = result;
                }

                if (title != null && link != null && description != null) {
                    if(isItem) {
                        Item item = new Item(title, description, link);
                        itemWrapper.add(item);
                    }
                    title = null;
                    link = null;
                    description = null;
                    isItem = false;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ItemWrapper getItems() {
        return itemWrapper;
    }
}

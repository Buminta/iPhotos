package ipicture.hemlock.com.ipic.utils;

import android.app.Application;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by me866chuan on 1/21/15.
 */
public class GATraceHandler extends Application{
    private static final String PROPERTY_ID = "UA-48485504-5";
    private boolean flagHandlerExeption = false;
    public static ExceptionReporter reporter = null;
    public HashMap mTrackers = new HashMap();
    public enum TrackerName {
        APP_TRACKER, GLOBAL_TRACKER, ECOMMERCE_TRACKER,
    }
    public synchronized Tracker getTracker(TrackerName appTracker) {
        if (!mTrackers.containsKey(appTracker)) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker t = analytics.newTracker(PROPERTY_ID);
            mTrackers.put(appTracker, t);
        }
        if(flagHandlerExeption) hanlderExeption(appTracker);
        return (Tracker) mTrackers.get(appTracker);
    }
    public void hanlderExeption(TrackerName appTracker){
        ArrayList<String> packages = new ArrayList<String>();
        packages.add(getPackageName());
        reporter = new ExceptionReporter((Tracker) mTrackers.get(appTracker), Thread.getDefaultUncaughtExceptionHandler(), this);
        reporter.setExceptionParser(new AnalyticsExceptionParser(this, packages));
    }
}
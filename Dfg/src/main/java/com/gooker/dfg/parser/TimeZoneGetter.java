package com.gooker.dfg.parser;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.gooker.dfg.R;

import org.xmlpull.v1.XmlPullParserException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by sczhang on 15/6/19.
 * https://github.com/android/platform_packages_apps_settings/blob/7779ef1a48e1115aa719c65c3809702f19dca5df/src/com/android/settings/ZonePicker.java
 */
public class TimeZoneGetter {

    private static final String TAG = "TimeZoneGetter";

    private static final String KEY_ID = "id";  // value: String
    private static final String KEY_DISPLAYNAME = "name";  // value: String
    private static final String KEY_GMT = "gmt";  // value: String
    private static final String KEY_OFFSET = "offset";  // value: int (Integer)
    private static final String XMLTAG_TIMEZONE = "timezone";
    private final List<HashMap<String, Object>> mZones =
            new ArrayList<HashMap<String, Object>>();
    private final HashSet<String> mLocalZones = new HashSet<String>();
    private final Date mNow = Calendar.getInstance().getTime();
    private final SimpleDateFormat mZoneNameFormatter = new SimpleDateFormat("zzzz");

    private List<HashMap<String, Object>> getZones(Context context) {

        try {
            XmlResourceParser xrp = context.getResources().getXml(R.xml.timezones);
            while (xrp.next() != XmlResourceParser.START_TAG) {
                continue;
            }
            xrp.next();
            while (xrp.getEventType() != XmlResourceParser.END_TAG) {
                while (xrp.getEventType() != XmlResourceParser.START_TAG) {
                    if (xrp.getEventType() == XmlResourceParser.END_DOCUMENT) {
                        return mZones;
                    }
                    xrp.next();
                }
                if (xrp.getName().equals(XMLTAG_TIMEZONE)) {
                    String olsonId = xrp.getAttributeValue(0);
                    addTimeZone(olsonId);
                }
                while (xrp.getEventType() != XmlResourceParser.END_TAG) {
                    xrp.next();
                }
                xrp.next();
            }
            xrp.close();
        } catch (XmlPullParserException xppe) {
            Log.e(TAG, "Ill-formatted timezones.xml file");
        } catch (java.io.IOException ioe) {
            Log.e(TAG, "Unable to read timezones.xml file");
        }
        return mZones;
    }

    private void addTimeZone(String olsonId) {
        // We always need the "GMT-07:00" string.


        final TimeZone tz = TimeZone.getTimeZone(olsonId);

        // For the display name, we treat time zones within the country differently
        // from other countries' time zones. So in en_US you'd get "Pacific Daylight Time"
        // but in de_DE you'd get "Los Angeles" for the same time zone.
        String displayName;
        if (mLocalZones.contains(olsonId)) {
            // Within a country, we just use the local name for the time zone.
            mZoneNameFormatter.setTimeZone(tz);
            displayName = mZoneNameFormatter.format(mNow);
        } else {
            // For other countries' time zones, we use the exemplar location.
            final String localeName = Locale.getDefault().toString();
//            displayName = TimeZoneNames.getExemplarLocation(localeName, olsonId);
        }

        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(KEY_ID, olsonId);
//        map.put(KEY_DISPLAYNAME, displayName);
//        map.put(KEY_GMT, DateTimeSettings.getTimeZoneText(tz, false));
        map.put(KEY_OFFSET, tz.getOffset(mNow.getTime()));

        mZones.add(map);
    }
}

/**
 *
 */
package com.gooker.dfg.ui.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Jun 9, 2014 3:55:15 PM
 */
public class MediaControlReceiver extends BroadcastReceiver {

    public static final String ACTION_MEDIA_BUTTON = "com.aoeng.degu.ACTION_MEDIA_BUTTON";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            Intent internalIntent = new Intent(ACTION_MEDIA_BUTTON);
            internalIntent.putExtras(intent.getExtras());
            context.sendBroadcast(internalIntent);
        }
    }
}
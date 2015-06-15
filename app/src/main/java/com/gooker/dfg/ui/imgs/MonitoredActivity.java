/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gooker.dfg.ui.imgs;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

public class MonitoredActivity extends Activity {

    private final ArrayList<LifeCycleListener> mListeners =
            new ArrayList<LifeCycleListener>();

    public static interface LifeCycleListener {

        public void onActivityCreated(com.aoeng.degu.ui.imgs.MonitoredActivity activity);

        public void onActivityDestroyed(com.aoeng.degu.ui.imgs.MonitoredActivity activity);

        public void onActivityPaused(com.aoeng.degu.ui.imgs.MonitoredActivity activity);

        public void onActivityResumed(com.aoeng.degu.ui.imgs.MonitoredActivity activity);

        public void onActivityStarted(com.aoeng.degu.ui.imgs.MonitoredActivity activity);

        public void onActivityStopped(com.aoeng.degu.ui.imgs.MonitoredActivity activity);
    }

    public static class LifeCycleAdapter implements LifeCycleListener {

        public void onActivityCreated(com.aoeng.degu.ui.imgs.MonitoredActivity activity) {

        }

        public void onActivityDestroyed(com.aoeng.degu.ui.imgs.MonitoredActivity activity) {

        }

        public void onActivityPaused(com.aoeng.degu.ui.imgs.MonitoredActivity activity) {

        }

        public void onActivityResumed(com.aoeng.degu.ui.imgs.MonitoredActivity activity) {

        }

        public void onActivityStarted(com.aoeng.degu.ui.imgs.MonitoredActivity activity) {

        }

        public void onActivityStopped(com.aoeng.degu.ui.imgs.MonitoredActivity activity) {

        }
    }

    public void addLifeCycleListener(LifeCycleListener listener) {

        if (mListeners.contains(listener)) return;
        mListeners.add(listener);
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {

        mListeners.remove(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityCreated(this);
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityDestroyed(this);
        }
    }

    @Override
    protected void onStart() {

        super.onStart();
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityStarted(this);
        }
    }

    @Override
    protected void onStop() {

        super.onStop();
        for (LifeCycleListener listener : mListeners) {
            listener.onActivityStopped(this);
        }
    }
}

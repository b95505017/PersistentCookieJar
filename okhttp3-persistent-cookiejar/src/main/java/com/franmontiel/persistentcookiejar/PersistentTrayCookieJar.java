package com.franmontiel.persistentcookiejar;

import android.content.Context;

import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.tray.AppPrefsCookiePersistor;

public class PersistentTrayCookieJar extends PersistentCookieJar {

    public PersistentTrayCookieJar(Context context) {
        super(new SetCookieCache(), new AppPrefsCookiePersistor(context));
    }
}

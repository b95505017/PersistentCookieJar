package com.franmontiel.persistentcookiejar.tray;

import android.content.Context;
import android.text.TextUtils;

import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import com.franmontiel.persistentcookiejar.persistence.SerializableCookie;

import net.grandcentrix.tray.core.TrayItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Cookie;

public class AppPrefsCookiePersistor implements CookiePersistor {

    private final AppPreferences sharedPreferences;

    public AppPrefsCookiePersistor(Context context) {
        sharedPreferences = new AppPreferences(context);
    }

    @Override
    public List<Cookie> loadAll() {
        List<Cookie> cookies = new ArrayList<>(sharedPreferences.getAll().size());

        for (TrayItem entry : sharedPreferences.getAll()) {
            if (TextUtils.isEmpty(entry.value())) {
                continue;
            }
            Cookie cookie = new SerializableCookie().decode(entry.value());
            if (cookie != null) {
                cookies.add(cookie);
            }
        }
        return cookies;
    }

    @Override
    public void saveAll(Collection<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.persistent()) {
                sharedPreferences.put(createCookieKey(cookie), new SerializableCookie().encode(cookie));
            }
        }
    }

    @Override
    public void removeAll(Collection<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            sharedPreferences.remove(createCookieKey(cookie));
        }
    }

    private static String createCookieKey(Cookie cookie) {
        return (cookie.secure() ? "https" : "http") + "://" + cookie.domain() + cookie.path() + "|" + cookie.name();
    }

    @Override
    public void clear() {
        sharedPreferences.clear();
    }
}

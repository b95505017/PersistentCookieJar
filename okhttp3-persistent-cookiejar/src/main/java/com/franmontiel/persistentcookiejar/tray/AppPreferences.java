package com.franmontiel.persistentcookiejar.tray;

import android.content.Context;

import net.grandcentrix.tray.core.AbstractTrayPreference;
import net.grandcentrix.tray.core.TrayStorage;
import net.grandcentrix.tray.provider.ContentProviderStorage;

class AppPreferences extends AbstractTrayPreference<ContentProviderStorage> {

    AppPreferences(final Context context) {
        super(new ContentProviderStorage(context, context.getPackageName(), TrayStorage.Type.USER), 1);
    }
}

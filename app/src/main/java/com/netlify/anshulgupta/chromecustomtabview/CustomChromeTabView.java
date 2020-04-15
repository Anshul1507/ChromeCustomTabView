package com.netlify.anshulgupta.chromecustomtabview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.core.content.ContextCompat;

public class CustomChromeTabView {
    //For linking
    public CustomTabsServiceConnection customTabsServiceConnection;
    public CustomTabsClient mClient;
    CustomTabsSession customTabsSession;

    public void customTabLinking(String url, Context context){
        customTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //pre-warning means to fast the surfing
                mClient = customTabsClient;
                mClient.warmup(0L);
                customTabsSession = mClient.newSession(null);
            }


            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mClient = null;
            }
        };
        CustomTabsClient.bindCustomTabsService(context,"com.android.chrome",customTabsServiceConnection);
        Uri uri = Uri.parse(url);

        //Create an Intent Builder
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        //Begin Customizing
        //Set Toolbar Colors
        intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));

        //Set Start and Exit Animations
        intentBuilder.setStartAnimations(context,android.R.anim.slide_out_right,android.R.anim.slide_in_left);
        intentBuilder.setExitAnimations(context,android.R.anim.slide_in_left,android.R.anim.slide_out_right);

        //build custom tabs intent
        CustomTabsIntent customTabsIntent =  intentBuilder.build();
        customTabsIntent.intent.setPackage("com.android.chrome");
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intentBuilder.setShowTitle(true);
        intentBuilder.enableUrlBarHiding();

        //Setting a custom back button


        //launch the url
        customTabsIntent.launchUrl(context,uri);

    }

}

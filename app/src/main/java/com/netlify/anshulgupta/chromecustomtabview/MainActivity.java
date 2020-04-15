package com.netlify.anshulgupta.chromecustomtabview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton fbBtn,telegramBtn,discordBtn;
    String telegramURL = "https://t.me/joinchat/PHHOwkXHjvgooz6AW0RAMig";
    String fbURL = "https://www.facebook.com/groups/2611721272445122/";
    String discordURL = "https://discord.gg/BVmUAjS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbBtn = findViewById(R.id.facebook_button);
        telegramBtn = findViewById(R.id.telegram_button);
        discordBtn = findViewById(R.id.discord_button);

        final CustomChromeTabView customChromeTabView = new CustomChromeTabView();
        customChromeTabView.customTabLinking(fbURL, getApplicationContext());

        PackageManager pm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            pm = (getApplicationContext()).getPackageManager();
        }
        final PackageManager finalPm = pm;

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fbPN = "com.facebook.katana";
                if(isPackageInstalled(fbPN, finalPm)) {
                    //if app is installed, open it in app
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fbPN)));
                }else{
                    // if not, open in chrome custom tabs
                    CustomChromeTabView customChromeTabView = new CustomChromeTabView();
                    customChromeTabView.customTabLinking(fbURL, getApplicationContext());
                }
            }
        });


        telegramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telegramPN = "org.telegram.messenger"; //for telegram
                String telegramXPN = "org.thunderdog.challegram"; //for telegram-X
                if(isPackageInstalled(telegramPN, finalPm)) {
                    //if app is installed, open it in app
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(telegramURL)));
                }else if(isPackageInstalled(telegramXPN,finalPm)){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(telegramURL)));
                }
                else{
                    // if not, open in chrome custom tabs
                    CustomChromeTabView customChromeTabView = new CustomChromeTabView();
                    customChromeTabView.customTabLinking(telegramURL, getApplicationContext());
                }
            }
        });

        discordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discordPN = "com.discord";
                if(isPackageInstalled(discordPN, finalPm)) {
                    //if app is installed, open it in app
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(discordURL)));
                }else{
                    // if not, open in chrome custom tabs
                    CustomChromeTabView customChromeTabView = new CustomChromeTabView();
                    customChromeTabView.customTabLinking(discordURL, getApplicationContext());
                }
            }
        });
    }
    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}

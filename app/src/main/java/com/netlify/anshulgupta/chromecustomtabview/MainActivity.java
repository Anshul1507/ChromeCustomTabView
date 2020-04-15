package com.netlify.anshulgupta.chromecustomtabview;

import androidx.appcompat.app.AppCompatActivity;

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

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customChromeTabView.customTabLinking(fbURL,getApplicationContext());
            }
        });

        telegramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customChromeTabView.customTabLinking(telegramURL,getApplicationContext());
            }
        });

        discordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customChromeTabView.customTabLinking(discordURL,getApplicationContext());
            }
        });
    }
}

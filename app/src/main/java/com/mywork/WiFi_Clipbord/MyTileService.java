package com.mywork.WiFi_Clipbord;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.icu.text.CaseMap;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MyTileService extends TileService {
    private final String LOG_TAG = "MyTitleService";
    private final int STATE_ON = 1;
    private  final int STATE_OFF = 2;
    private int togglestate = STATE_ON;
    MainActivity m1;
    MainActivity.MyThread t1;

    @Override
    public void onTileAdded() {
        Log.d(LOG_TAG, "onTitleAdded");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(LOG_TAG, "onTaskRemoved");
    }

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        Log.d(LOG_TAG, "onStartListening" + tile.getLabel());
    }

    @Override
    public void onStopListening() {
        Log.d(LOG_TAG, "onStopListening");
    }

    @Override
    public void onClick() {
        Log.d(LOG_TAG, "onClick" + Integer.toString(getQsTile().getState()));
        t1.run();

        Icon icon;
        if(togglestate == STATE_OFF){
            togglestate = STATE_ON;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.clipboard);

        }
        else{
            togglestate = STATE_OFF;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.ic_baseline_content_copy_24);
        }

        getQsTile().setIcon(icon);
        getQsTile().updateTile();
    }
}

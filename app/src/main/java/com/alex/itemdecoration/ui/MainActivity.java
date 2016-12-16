package com.alex.itemdecoration.ui;

import android.os.Bundle;
import android.view.View;

import com.alex.itemdecoration.R;
import com.alex.itemdecoration.ui.base.IDActivity;
import com.alex.itemdecoration.ui.gridlayout.GActivity;
import com.alex.itemdecoration.ui.hlinearlayout.HActivity;
import com.alex.itemdecoration.ui.vlinearlayout.VActivity;

import org.alex.helper.IntentHelper;
import org.alex.model.ParcelableMap;


public class MainActivity extends IDActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateData(Bundle bundle) {


    }

    @Override
    public void onClick(View v, int id) {
        super.onClick(v, id);
        if(R.id.bt_1 == id){
            IntentHelper.getInstance().startActivity(VActivity.class);
        }else if(R.id.bt_2 == id){
            IntentHelper.getInstance().startActivity(HActivity.class);
        }else if(R.id.bt_3 == id){
            ParcelableMap map = new ParcelableMap();
            map.put("spanCount", 2);
            IntentHelper.getInstance().parcelableMap(map).startActivity(GActivity.class);
        }else if(R.id.bt_4 == id){
            ParcelableMap map = new ParcelableMap();
            map.put("spanCount", 3);
            IntentHelper.getInstance().parcelableMap(map).startActivity(GActivity.class);
        }else if(R.id.bt_5 == id){
            ParcelableMap map = new ParcelableMap();
            map.put("spanCount", 4);
            IntentHelper.getInstance().parcelableMap(map).startActivity(GActivity.class);
        }else if(R.id.bt_6 == id){
            ParcelableMap map = new ParcelableMap();
            map.put("spanCount", 5);
            IntentHelper.getInstance().parcelableMap(map).startActivity(GActivity.class);
        }
    }
}

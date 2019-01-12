package com.zulfakarelzaf.authenticjogja.model;


import android.content.Context;

import com.zulfakarelzaf.authenticjogja.common.Dummy;
import com.zulfakarelzaf.authenticjogja.R;

import java.util.List;

/**
 * Created by zulfakar on 25/05/18.
 * For Educational and Personal purposes
 */

public class SubMenuItem {
    private Context context;
    private String subMenuTitle;
    Dummy dataDummy;

    public SubMenuItem(Context context, String subMenuTitle) {
        this.context = context;
        this.subMenuTitle = subMenuTitle;
        dataDummy = new Dummy(context.getApplicationContext());
    }

    public List<ItemModel> getSubMenuList() {
        if (subMenuTitle.equals(context.getResources().getString(R.string.culinary))) {
            return dataDummy.getListKuliner();
        } else if (subMenuTitle.equals(context.getResources().getString(R.string.craft))) {
            return dataDummy.getListKerajinan();
        } else if (subMenuTitle.equals(context.getResources().getString(R.string.events))) {
            return dataDummy.getAcara();
        } else {
            return dataDummy.getBahasaDaerah();
        }
    }

    public void setSubMenuTitle(String subMenuTitle) {
        this.subMenuTitle = subMenuTitle;
    }
}

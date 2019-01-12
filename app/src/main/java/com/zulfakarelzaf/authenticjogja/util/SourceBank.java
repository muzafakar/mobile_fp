package com.zulfakarelzaf.authenticjogja.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SourceBank {

    private Context mContext;

    public SourceBank(Context mContext) {
        this.mContext = mContext;
    }

    public List<String> getAllSource(String path){
        List<String> source = new ArrayList<>();
        AssetManager am = mContext.getAssets();
        InputStream in = null;
        try {
            in = am.open(path);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line =bf.readLine()) != null){
                source.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return source;

    }

}

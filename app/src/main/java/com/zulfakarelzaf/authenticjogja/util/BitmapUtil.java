package com.zulfakarelzaf.authenticjogja.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.zulfakarelzaf.authenticjogja.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {
    public static Bitmap mark(Bitmap src, String watermark, Context context) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.abu));
        paint.setTextSize(40);
        paint.setAntiAlias(true);

        canvas.drawText(watermark,20, h -40, paint);

        return result;
    }

    static public Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        try {

            File file =  new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            if(Build.VERSION.SDK_INT>=24){
                bmpUri =FileProvider.getUriForFile(
                        context,
                        context.getApplicationContext()
                                .getPackageName() + ".provider", file);
            }else{
                bmpUri = Uri.fromFile(file);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


}

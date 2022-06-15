package com.jvillacorta.fudapp.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.jvillacorta.fudapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Herramientas {
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap convertirBlobABitmap(byte[] byteArray){
        //byte[] byteArray = c.getBlob(index);

        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

        return bm;
    }

    public static void cargarImagenURLenIV(String url, ImageView iv){
        Picasso.get()
                .load(url)
                .error(R.mipmap.ic_launcher_round)
                .into(iv);
    }

    public static Drawable cargarImagenURL(String url, ImageView iv){
        Drawable drawable;
        Picasso.get()
                .load(url)
                .into( new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        iv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        iv.setImageDrawable(errorDrawable);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        iv.setImageDrawable(placeHolderDrawable);
                    }
                });
        drawable = iv.getDrawable();
        return drawable;
    }
}

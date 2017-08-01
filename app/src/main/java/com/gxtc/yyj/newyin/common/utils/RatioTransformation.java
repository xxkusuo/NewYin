package com.gxtc.yyj.newyin.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * Created by Jam on 2017/8/1.
 */

public class RatioTransformation extends BitmapTransformation {
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return ratioCrop(pool, toTransform);
    }

    private static Bitmap ratioCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        int width = source.getWidth();
        int height = source.getHeight();
        float resizeWidth = Global.mScreenWidth / 3;
        float resizeHeight = resizeWidth / width * height;
        int widthInt = (int) (resizeWidth + 0.5f);
        int heightInt = (int) (resizeHeight + 0.5f);
        Bitmap squared = Bitmap.createBitmap(source, 0, 0, widthInt, heightInt);
        Bitmap result = pool.get(widthInt, heightInt, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(widthInt, heightInt, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        canvas.drawRect(0, 0, widthInt, heightInt, paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ByteBuffer.allocate(Integer.SIZE));
    }
}

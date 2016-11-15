package com.qiang.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2016/11/13.
 */
public class BitmapUtils {
//测量适合的比值
    public static int calculateInSampleSize(BitmapFactory.Options options , int reqHeight, int reqWidth){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight|| width > reqWidth){
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
//压缩资源文件文件方法
    public static Bitmap decodeSampledBitmapFromResource(Resources resources,int resId,int reqHeight,int reqWidth){

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources,resId,options);
        options.inSampleSize = calculateInSampleSize(options,reqHeight,reqWidth);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(resources,resId,options);
    }
    //压缩SD文件方法
    public static  Bitmap decodeSampledBitmapFromFile( String fileName ,int reqHeight ,int reqWidth){
        final BitmapFactory.Options options =new BitmapFactory.Options();
        options.inJustDecodeBounds = true ;
        BitmapFactory.decodeFile(fileName,options);
        options.inSampleSize = calculateInSampleSize(options,reqHeight,reqWidth);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(fileName,options);
    }
    //压缩字符数组传入图片方法
    public static  Bitmap decodeSampledBitmapFromByte(byte[] bytes,int reqHeight ,int reqWidth){
        final BitmapFactory.Options options =new BitmapFactory.Options();
        options.inJustDecodeBounds = true ;

        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        options.inSampleSize = calculateInSampleSize(options,reqHeight,reqWidth);
        options.inJustDecodeBounds = false;

        return  BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
    }
    //缓存



}

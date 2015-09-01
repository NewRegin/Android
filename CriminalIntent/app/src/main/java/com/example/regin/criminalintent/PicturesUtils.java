package com.example.regin.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

/**
 * Created by Regin on 15/6/10.
 */
public class PicturesUtils {
    @SuppressWarnings("deprecation")
    public static BitmapDrawable getScaledDrawable(Activity a,String path){
        Display dispaly = a.getWindowManager().getDefaultDisplay();
        float destWidth = dispaly.getWidth();
        float destHeight = dispaly.getHeight();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth){
            if (srcHeight > srcWidth){
                inSampleSize = Math.round(srcWidth/destWidth);
            }else{
                inSampleSize = Math.round(srcHeight/destHeight);
            }
        }
        options = new BitmapFactory.Options();
        options.inSampleSize =inSampleSize;

        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        return new BitmapDrawable(a.getResources(),bitmap);

    }
   public static void cleanImageView(ImageView imageView){
       if(!(imageView.getDrawable() instanceof BitmapDrawable))
           return;
       BitmapDrawable b = (BitmapDrawable)imageView.getDrawable();
       b.getBitmap().recycle();
       imageView.setImageDrawable(null);

   }
}

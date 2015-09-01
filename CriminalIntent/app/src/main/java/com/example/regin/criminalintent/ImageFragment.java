package com.example.regin.criminalintent;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Regin on 15/6/10.
 */
public class ImageFragment extends DialogFragment {
    public static final String EXTRA_IMAGE_PATH =
            "com.example.regin.criminalintent.image_path";
    private ImageView imageView;

    public static ImageFragment newInstance(String imagePath){
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_IMAGE_PATH, imagePath);

        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setArguments(bundle);
        imageFragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);

        return imageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        imageView = new ImageView(getActivity());
        String path = getArguments().getString(EXTRA_IMAGE_PATH);
        BitmapDrawable bitmapDrawable = PicturesUtils.getScaledDrawable(getActivity(),path);
        imageView.setImageDrawable(bitmapDrawable);
        return imageView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PicturesUtils.cleanImageView(imageView);
    }
}

package com.example.regin.criminalintent;

import java.io.FileOutputStream;
import java.io.IOException;
        import java.util.List;
import java.util.UUID;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
        import android.hardware.Camera.Size;
        import android.os.Build;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;


/**
 * Created by Regin on 15/6/10.
 */
public class CrimeCameraFragment extends Fragment {
    private static final String TAG = "CrimeCameraFragment";
    public static final String EXTRA_PHOTO_FILENAME =
            "com.example.regin.criminalintent.photo_filename";
    private Camera mCamera = null;
    private SurfaceView surfaceView;
    private View progressContainer;

    private Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            progressContainer.setVisibility(View.VISIBLE);
        }
    };
    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            String filename = UUID.randomUUID().toString() + ".jpg";
            FileOutputStream outputStream = null;
            boolean sucess = true;
            try {
                outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(data);
            } catch (Exception e) {
                Log.e(TAG, "Error writing to file " + filename, e);
                sucess = false;
            } finally {
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error to close file", e);
                    sucess = false;
                }
            }

            if(sucess){
                Intent i = new Intent();
                i.putExtra(EXTRA_PHOTO_FILENAME,filename);
                getActivity().setResult(Activity.RESULT_OK,i);

            }else{
                getActivity().setResult(Activity.RESULT_CANCELED);
            }
            getActivity().finish();
        }
    };

    @Override
    @SuppressWarnings("deprecation")
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_camera_crime,container,false);
        progressContainer = (View)v.findViewById( R.id.crime_camera_progressContainer);
        progressContainer.setVisibility(View.INVISIBLE);

        Button takeButton =  (Button)v.findViewById(R.id.crime_camera_takePictureButton);
        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().finish();
                if (mCamera != null) {
                      mCamera.takePicture(shutterCallback,null,pictureCallback);
                  }else {
                    Log.e(TAG,"mCamera = null");
                }
            }
        });
        surfaceView = (SurfaceView)v.findViewById(R.id.crime_camera_surfaceview);
        SurfaceHolder holder = surfaceView.getHolder();
        // deprecated, but required for pre-3.0 devices
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new SurfaceHolder.Callback() {

            public void surfaceCreated(SurfaceHolder holder) {
                // tell the camera to use this surface as its preview area
                try {
                    if (mCamera != null) {
                        mCamera.setPreviewDisplay(holder);
                    }
                } catch (IOException exception) {
                    Log.e(TAG, "Error setting up preview display", exception);
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                // we can no longer display on this surface, so stop the preview.
                if (mCamera != null) {
                    mCamera.stopPreview();
                }
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
                if (mCamera == null) return;

                // the surface has changed size; update the camera preview size
                Camera.Parameters parameters = mCamera.getParameters();
                Camera.Size s = getBestSupportedSize(parameters.getSupportedPreviewSizes(), w, h);
                parameters.setPreviewSize(s.width, s.height);
                s = getBestSupportedSize(parameters.getSupportedPictureSizes(),w,h);
                parameters.setPictureSize(s.width,s.height);
                mCamera.setParameters(parameters);
                try {
                    mCamera.startPreview();
                } catch (Exception e) {
                    Log.e(TAG, "Could not start preview", e);
                    mCamera.release();
                    mCamera = null;
                }
            }
        });

        return v;
    }


    private Camera.Size getBestSupportedSize(List<Camera.Size> sizes,int width,int height){
        Camera.Size bestSize = sizes.get(0);
        int largestSize = bestSize.width * bestSize.height;
        for(Camera.Size s : sizes){
            int area = s.height * s.width;
            if (area > largestSize){
                bestSize = s;
                largestSize = area;
            }
        }
        return bestSize;
    }
    @TargetApi(9)
    @Override
    public void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            mCamera = mCamera.open(0);
        }else {
            mCamera = mCamera.open();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mCamera != null){
            mCamera.release();
            mCamera = null;
        }
    }

}

package com.studioidan.popapp.dialog;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.squareup.picasso.Picasso;
import com.studioidan.pop_app.R;

/**
 * Created by PopApp_laptop on 30/05/17.
 */

public class DialogZoomImage extends BaseDialogFragment {
    public static final String ARG_URL = "arg.url";

    //DATA
    private String url;

    //VIEWS
    private SubsamplingScaleImageView zoomImageView;
    private ImageView imageView;


    public static DialogZoomImage getInstance(String url) {
        DialogZoomImage answer = new DialogZoomImage();
        Bundle bundle = new Bundle(1);
        bundle.putString(ARG_URL, url);
        answer.setArguments(bundle);
        return answer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(ARG_URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_zoom_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        zoomImageView = view.findViewById(R.id.zoomImageView);
        imageView = view.findViewById(R.id.imageView);
        imageView.setVisibility(View.VISIBLE);

        //String prefix = url.toLowerCase().contains(Keys.TEMP_IMAGE_NAME.toLowerCase()) ? "file:" : "";

        Picasso.get()
                .load(/*prefix +*/ url)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        //mAttacher.update();
                        zoomImageView.setImage(ImageSource.bitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap()));
                        zoomImageView.setMaxScale(8f);
                        imageView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

}

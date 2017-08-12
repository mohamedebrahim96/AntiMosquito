package com.vacuum.app.antimosquito.Fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vacuum.app.antimosquito.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;


import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
import android.widget.TextView;

import com.vacuum.app.antimosquito.R;

        import yalantis.com.sidemenu.interfaces.ScreenShotable;


public class HelpFragment extends Fragment implements ScreenShotable {


    private View Fragmentone_view;
    private Bitmap bitmap;

    public static HelpFragment newInstance() {
        HelpFragment helpFragment = new HelpFragment();

        return helpFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.help_fragment, container, false);
        TextView warning = rootView.findViewById(R.id.warning);
        Typeface typeface = Typeface.createFromAsset(rootView.getContext().getAssets(),"fonts/oswaldlight.ttf");
        warning.setTypeface(typeface);
        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(Fragmentone_view.getWidth(),
                        Fragmentone_view.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                Fragmentone_view.draw(canvas);
                HelpFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.Fragmentone_view = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

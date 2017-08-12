package com.vacuum.app.antimosquito.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vacuum.app.antimosquito.R;

import java.util.Random;


import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static android.R.attr.start;

public class FragmentOne extends Fragment implements ScreenShotable {


    Random r = new Random();
    private View Fragmentone_view;
    private Bitmap bitmap;
    ImageView imageView;
    View rootView;
    public MediaPlayer mp;
    TextView scan;
    public static boolean x = true;
    ScrollView scrollview;
    int i1,i2;
    Vibrator vibe;
    public static FragmentOne newInstance() {
        FragmentOne fragmentOne = new FragmentOne();
        return fragmentOne;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_one, container, false);

        imageView = rootView.findViewById(R.id.btn);
        scan = rootView.findViewById(R.id.scan);
        scrollview =  rootView.findViewById(R.id.scrollview);
        Typeface typeface = Typeface.createFromAsset(rootView.getContext().getAssets(),"fonts/oswaldlight.ttf");
        scan.setTypeface(typeface);

        imageView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                if(x == true)
                {
                    mp= MediaPlayer.create(getActivity().getApplicationContext(), R.raw.mosquito);
                    mp.start();
                    mp.setLooping(true);
                    x = false;
                    Toast.makeText(getActivity(), "scan", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.drawable.stop2);
                    thread();
                }else if(x == false){
                    mp.stop();
                    Toast.makeText(getActivity(), "stop", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.drawable.scan2);
                    x = true;
                }

            }
        });




        return rootView;
    }

    public void thread()
    {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (x == false)
                    {
                        Thread.sleep(1000);
                        if (getActivity()!=null) {
                            getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                i1 = r.nextInt(80 - 65) + 65;
                                i2 = r.nextInt(80 - 33) + 30;
                                if (i2>70){
                                    vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                                    String command = "warning:frequency +"+i2+"GHz,wavelength 0."+(i2-22)+"λ Ultrasonic +50dp "+i1+",000 ω\n";
                                    appendColoredText(scan, command, Color.RED);
                                    vibe.vibrate(20);
                                }else {
                                    scan.setTextColor(getResources().getColor(R.color.colorAccent));
                                    scan.append("Injecting:frequency +"+i2+"GHz,wavelength 0."+(i2-22)+"λ Infrasound -50dp "+i1+",000 ω\n");
                                }
                                /*Spannable spannableText = new SpannableString(scan.getText());
                                spannableText.setSpan(new ForegroundColorSpan(Color.GREEN), 0, command.length(), 0);
                                spannableText.setSpan(new ForegroundColorSpan(Color.RED), command.length(), command.length() + 40, 0);

                                LinearLayout layout = new LinearLayout(getActivity());
                                layout.addView(scan);
                                getActivity().setContentView(layout);*/


                                scrollview.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                      }
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }


    public static void appendColoredText(TextView tv, String text, int color) {
        int start = tv.getText().length();
        tv.append(text);
        int end = tv.getText().length();

        Spannable spannableText = (Spannable) tv.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
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
                FragmentOne.this.bitmap = bitmap;
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
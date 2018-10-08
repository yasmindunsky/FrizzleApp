package com.frizzl.app.frizzleapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.frizzl.app.frizzleapp.appBuilder.UserCreatedButton;

import java.util.Locale;

/**
 * Created by Noga on 12/09/2018.
 */

public class DesignSection extends RelativeLayout {
    private ImageButton playButton;
    private TextToSpeech tts;
    private UserCreatedButton userCreatedButton;

    public DesignSection(Context context, boolean runnable) {
        super(context);
        TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        };
        tts = new TextToSpeech(context, onInitListener, "com.google.android.tts");

        setId(R.id.constraintLayout);
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.design_element_bg));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);


        userCreatedButton = new UserCreatedButton(context, 1, 1);
        userCreatedButton.setBackgroundColor("#f8b119");
        userCreatedButton.setTextColor("#FFFFFF");
        Button buttonView = userCreatedButton.getThisView();
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // show the popup window
                PopupWindow popupWindow = userCreatedButton.getPropertiesTablePopupWindow(getContext());
                v.post(new Runnable() {
                    public void run() {
                        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                    }
                });
            }
        });
        layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5,5,5,5);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        buttonView.setLayoutParams(layoutParams);

        addView(buttonView);

        if (runnable) {
            playButton = new ImageButton(context);
            playButton.setBackground(getResources().getDrawable(R.drawable.run_button_background));
            playButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            playButton.setAdjustViewBounds(false);
            playButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_run_icon));

            playButton.setOnClickListener(runCode);

            LayoutParams playButtonLayoutParams = new LayoutParams(80, 80);
            playButtonLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            playButtonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            playButtonLayoutParams.setMarginEnd(20);
            playButton.setLayoutParams(playButtonLayoutParams);
            addView(playButton);
        }
    }
    OnClickListener runCode = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = getContext();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_design_section_run, null);

            Button button = popupView.findViewById(R.id.button);
            Button thisView = userCreatedButton.getThisView();
            button.setText(thisView.getText());
            button.setTextColor(thisView.getCurrentTextColor());
            button.setTypeface(thisView.getTypeface());
            int originalButtonDrawableRes = R.drawable.user_button_background;
            Drawable buttonDrawable = ContextCompat.getDrawable(context, originalButtonDrawableRes);
            buttonDrawable.setColorFilter(Color.parseColor(userCreatedButton.getProperties().get("android:backgroundTint")), PorterDuff.Mode.DARKEN);
            button.setBackground(buttonDrawable);

            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean onClickSet = userCreatedButton.getOnClick().equals("sayHello");
                    if (onClickSet) {
                        tts.speak("Hello", TextToSpeech.QUEUE_ADD, null);
                    }
                }
            });

            PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
            ImageButton closeButton = popupView.findViewById(R.id.close);
            closeButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
    };

    public UserCreatedButton getUserCreatedButton() {
        return userCreatedButton;
    }
}
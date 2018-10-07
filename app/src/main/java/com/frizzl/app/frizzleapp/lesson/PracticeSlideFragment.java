package com.frizzl.app.frizzleapp.lesson;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import com.frizzl.app.frizzleapp.Code;
import com.frizzl.app.frizzleapp.CodeKeyboard;
import com.frizzl.app.frizzleapp.DesignSection;
import com.frizzl.app.frizzleapp.R;
import com.frizzl.app.frizzleapp.CodeSection;
import com.frizzl.app.frizzleapp.UserProfile;
import com.frizzl.app.frizzleapp.appBuilder.UserCreatedButton;

//import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeSlideFragment extends Fragment {
    private int index;
    private int lessonNum;
    private PracticeSlide practiceSlide;
    private RelativeLayout relativeLayout;
    private static final int SIDES_MARGIN = 50;
    private static final int TOP_DOWN_MARGIN = 15;

    public PracticeSlideFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice_slide, container, false);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        relativeLayout.setFocusableInTouchMode(true);

        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        lessonNum = bundle.getInt("lesson");
        practiceSlide = (PracticeSlide) bundle.getSerializable("practice_slide");

        // Create elements by what's needed
        Context context = getContext();
        int currentAddedViewID = R.id.holder;

        if (practiceSlide.hasInfoText()){
            int infoTextStyle = R.style.Text_PracticeSlide_infoText;
            AppCompatTextView infoText = new AppCompatTextView(new ContextThemeWrapper(context, infoTextStyle));
            infoText.setId(R.id.infoText);
            infoText.setText(practiceSlide.getInfoText());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(SIDES_MARGIN,TOP_DOWN_MARGIN,SIDES_MARGIN,TOP_DOWN_MARGIN);
            layoutParams.addRule(RelativeLayout.BELOW, currentAddedViewID);
            infoText.setLayoutParams(layoutParams);
            relativeLayout.addView(infoText);
            currentAddedViewID = infoText.getId();
        }

        if (practiceSlide.hasTaskText()){
            int taskTextStyle = R.style.Text_PracticeSlide_taskText;
            AppCompatTextView taskText = new AppCompatTextView(new ContextThemeWrapper(context, taskTextStyle));
            taskText.setId(R.id.taskText);
            taskText.setText(practiceSlide.getTaskText());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(SIDES_MARGIN,TOP_DOWN_MARGIN,SIDES_MARGIN,TOP_DOWN_MARGIN);
            layoutParams.addRule(RelativeLayout.BELOW, currentAddedViewID);
            taskText.setLayoutParams(layoutParams);
            relativeLayout.addView(taskText);
            currentAddedViewID = taskText.getId();
        }

        if (practiceSlide.hasReminderText()){
            int reminderTextStyle = R.style.Text_PracticeSlide_reminderText;
            AppCompatTextView reminderText = new AppCompatTextView(new ContextThemeWrapper(context, reminderTextStyle));
            reminderText.setId(R.id.reminderText);
            reminderText.setText(practiceSlide.getReminderText());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(SIDES_MARGIN,TOP_DOWN_MARGIN,SIDES_MARGIN,TOP_DOWN_MARGIN);
            layoutParams.addRule(RelativeLayout.BELOW, currentAddedViewID);
            reminderText.setLayoutParams(layoutParams);
            relativeLayout.addView(reminderText);
            currentAddedViewID = reminderText.getId();
        }

        CodeKeyboard codeKeyboard = null;
        if (practiceSlide.hasCode()){
            Code code = practiceSlide.getCode();
            boolean mutable = code.getMutable();
            if (mutable){
                codeKeyboard = new CodeKeyboard(context);
                codeKeyboard.setId(R.id.codeKeyboard);
                codeKeyboard.setOrientation(LinearLayout.VERTICAL);
                RelativeLayout.LayoutParams keyboardLayoutParams = new
                        RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                keyboardLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                keyboardLayoutParams.setMargins(0,TOP_DOWN_MARGIN,0,0);
                codeKeyboard.setLayoutParams(keyboardLayoutParams);
                relativeLayout.addView(codeKeyboard);
            }
            CodeSection codeSection = new CodeSection(context, code.getCode(), code.getRunnable(), mutable, codeKeyboard);
            codeSection.setId(R.id.codeSection);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(SIDES_MARGIN,TOP_DOWN_MARGIN,SIDES_MARGIN,TOP_DOWN_MARGIN);
            layoutParams.addRule(RelativeLayout.BELOW, currentAddedViewID);
            codeSection.setLayoutParams(layoutParams);
            relativeLayout.addView(codeSection);
            currentAddedViewID = codeSection.getId();
        }

        if (practiceSlide.hasDesign()) {
            DesignSection designSection = new DesignSection(context, true);
            designSection.setId(R.id.designSection);
            designSection.setPadding(SIDES_MARGIN, SIDES_MARGIN, SIDES_MARGIN, SIDES_MARGIN);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            designSection.setLayoutParams(layoutParams);
            layoutParams.setMargins(SIDES_MARGIN, 0, SIDES_MARGIN, TOP_DOWN_MARGIN);
            layoutParams.addRule(RelativeLayout.BELOW, currentAddedViewID);
            designSection.setLayoutParams(layoutParams);
            relativeLayout.addView(designSection);
            currentAddedViewID = designSection.getId();
        }

        int checkButtonStyle = R.style.Button_CheckExercise;
        AppCompatButton callToActionButton = new AppCompatButton(new ContextThemeWrapper(context, checkButtonStyle));
        callToActionButton.setText(practiceSlide.getCallToActionText());
        callToActionButton.setBackground(getResources().getDrawable(R.drawable.check_button_background));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(550,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        if (codeKeyboard != null) {
            layoutParams.addRule(RelativeLayout.ABOVE, codeKeyboard.getId());
        }
        else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.setMargins(0,TOP_DOWN_MARGIN,0,TOP_DOWN_MARGIN);
        callToActionButton.setLayoutParams(layoutParams);
        callToActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                boolean moveOn = true;
                CharSequence buttonText = button.getText();
                if (buttonText.equals("Check") || buttonText.equals("Try Again")){
                    moveOn = checkPractice();
                }
                if (moveOn) {
                    moveToNextFragment();
                }
                else {
                    button.setText("Try Again");
                }
            }
        });
        relativeLayout.addView(callToActionButton);

        return view;
    }

    private boolean checkPractice() {
        boolean correct = true;

        // What slide are we at
        ViewPager viewPager = getActivity().findViewById(R.id.pager);
        int currentSlide = viewPager.getCurrentItem();

        int currentLevel = UserProfile.user.getCurrentLevel();
        if (currentLevel == 1) {
            if (currentSlide == 2) {
                CodeSection codeSection = relativeLayout.findViewById(R.id.codeSection);
                String code = codeSection.getCode();
                correct = checkIfContainsSpeakOutAndString(code, "this is so cool");
            }
        } else if (currentLevel == 2){
            if (currentSlide == 1){
                CodeSection codeSection = relativeLayout.findViewById(R.id.codeSection);
                String code = codeSection.getCode();
                correct &= checkIfContainsSpeakOutAndString(code, "goodbye");
            }
            if (currentSlide == 2){
                CodeSection codeSection = relativeLayout.findViewById(R.id.codeSection);
                String code = codeSection.getCode();
                correct &= checkIfContainsFunctionWithName(code, "sayMyName");
                correct &= checkIfContainsSpeakOutAndString(code, "");

            }
            if (currentSlide == 3){
                DesignSection designSection = relativeLayout.findViewById(R.id.designSection);
                UserCreatedButton userCreatedButton = designSection.getUserCreatedButton();
                correct &= userCreatedButton.getOnClick().equals("sayHello");
            }
        }
        return correct;
    }

    private boolean checkIfContainsFunctionWithName(String code, String functionName) {
        boolean correct = true;
        correct &= code.contains(functionName.trim());
        // Contains 'speakOut'
        correct &= code.indexOf("public void") >= 0 ? true : false;
        return correct;
    }

    private boolean checkIfContainsSpeakOutAndString(String code, String expectedString) {
        boolean correct = true;
        correct &= code.contains(expectedString.trim().toLowerCase());
        // Contains 'speakOut'
        correct &= code.indexOf("speakOut") >= 0 ? true : false;
        // Contains '("'
        code = code.replaceAll("\\s+", "");
        correct &= code.indexOf("(\"") >= 0 ? true : false;
        // Contains '");'
        correct &= code.indexOf("\");") >= 0 ? true : false;
        return correct;
    }

    private void moveToNextFragment() {
        // Change to next fragment onClick
        ViewPager viewPager = getActivity().findViewById(R.id.pager);
        int i = viewPager.getCurrentItem() + 1;
        viewPager.setCurrentItem(i);
        RoundCornerProgressBar progressBar = getActivity().findViewById(R.id.progressBar);
        progressBar.setProgress(i);
    }
}

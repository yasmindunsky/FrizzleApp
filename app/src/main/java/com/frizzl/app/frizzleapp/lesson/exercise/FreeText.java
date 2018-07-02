package com.frizzl.app.frizzleapp.lesson.exercise;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.frizzl.app.frizzleapp.R;

import java.util.ArrayList;

/**
 * Created by yasmin.dunsky on 29-Dec-17.
 */

public class FreeText extends Exercise {
    public FreeText(String type, String question, String imageSource, ArrayList<String> content, ArrayList<String> possibilities, ArrayList<String> answers) {
        super(type, question, imageSource, content, possibilities, answers);
    }

    public void createLayout(final RelativeLayout layout, Context context, final Button checkButton){
        int style = R.style.editText;
        final android.support.v7.widget.AppCompatEditText inputText = new android.support.v7.widget.AppCompatEditText(new ContextThemeWrapper(context, style), null, style);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.exerciseQuestion);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        int sizeInDP = 32;
        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, sizeInDP, layout.getResources()
                        .getDisplayMetrics());
        layoutParams.setMarginStart(marginInDp);
        layoutParams.setMarginEnd(marginInDp);
        inputText.setLayoutParams(layoutParams);
        inputText.setTextAppearance(context, R.style.editText);
        inputText.setHint(R.string.edit_text_hint);
        inputText.setId(R.id.userAnswerInput);
        layout.setGravity(Gravity.CENTER);
        layout.addView(inputText);

        inputText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(inputText.getText().toString().equals("")){
                    inputText.setTextColor(layout.getResources().getColor(R.color.TextLightGrey));
                    checkButton.setEnabled(false);
                } else {
                    inputText.setTextColor(layout.getResources().getColor(R.color.TextGrey));
                    checkButton.setEnabled(true);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    public boolean isCorrect(View view) {
        EditText freeTextAnswer = view.findViewById(R.id.userAnswerInput);
        String userFreeTextAnswer = freeTextAnswer.getText().toString();
        return containsCaseInsensitive(userFreeTextAnswer);
    }

    private boolean containsCaseInsensitive(String answer){
        for (String currentAns : this.getAnswers()){
            if (currentAns.equalsIgnoreCase(answer)){
                return true;
            }
        }
        return false;
    }


}
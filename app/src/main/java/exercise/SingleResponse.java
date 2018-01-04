package exercise;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.example.yasmindunsky.frizzleapp.R;

import java.util.ArrayList;

/**
 * Created by yasmin.dunsky on 29-Dec-17.
 */

public class SingleResponse extends Exercise {
    public SingleResponse(String type, String question, String imageSource, ArrayList<String> content, ArrayList<String> possibilities, ArrayList<String> answers) {
        super(type, question, imageSource, content, possibilities, answers);
    }

    @Override
    public void createLayout(RelativeLayout layout, Context context) {
        // add RadioGroup to layout
        RadioGroup possibilitiesButtons = new RadioGroup(context);
        LayoutParams layoutParams =
                new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.exerciseImage);
        possibilitiesButtons.setLayoutParams(layoutParams);
        possibilitiesButtons.setId(R.id.radioGroup);
        layout.addView(possibilitiesButtons);

        // create each possibility as a radio button and add to RadioGroup
        for (final String possibility : this.getPossibilities()) {
            final RadioButton button = new RadioButton(context);
            button.setText(possibility);
            possibilitiesButtons.addView(button);

        }
    }

    @Override
    public boolean isCorrect(View view) {
        // get the text on the checked radio button
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        RadioButton checkedButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
        String userSelectedAnswer = checkedButton.getText().toString();

        // compare the text on the checked button to the answer
        return userSelectedAnswer.equals(this.getAnswers().get(0));    }
}

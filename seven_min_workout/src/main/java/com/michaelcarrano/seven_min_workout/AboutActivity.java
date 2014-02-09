package com.michaelcarrano.seven_min_workout;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AboutFragment())
                    .commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class AboutFragment extends Fragment {

        public AboutFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_about, container, false);

            SpannableStringBuilder ssb = new SpannableStringBuilder();
            int start, end, flags = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

            ssb.append(getString(R.string.about_part1)+"\n\n");
            ssb.append(getString(R.string.about_part2)+"\n\n");
            ssb.append(getString(R.string.about_part3)+"\n\n");

            // FAQ section
            start = ssb.length();
            ssb.append(getString(R.string.about_part4)+"\n");
            end = ssb.length();
            ssb.setSpan(new StyleSpan(Typeface.BOLD), start, end, flags);

            ssb.append(getString(R.string.about_part5)+"\n");
            ssb.append(getString(R.string.about_part6)+"\n\n");
            ssb.append(getString(R.string.about_part7)+"\n");
            ssb.append(getString(R.string.about_part8)+"\n\n");
            ssb.append(getString(R.string.about_part9)+"\n");
            ssb.append(getString(R.string.about_part10)+"\n\n");
            ssb.append(getString(R.string.about_part11)+"\n");
            ssb.append(getString(R.string.about_part12)+"\n\n");

            // Disclosure section
            start = ssb.length();
            ssb.append(getString(R.string.about_part13)+"\n");
            end = ssb.length();
            ssb.setSpan(new StyleSpan(Typeface.BOLD), start, end, flags);

            ssb.append(getString(R.string.about_part14)+"\n\n");
            ssb.append(getString(R.string.about_part15)+"\n");
            ssb.append(getString(R.string.about_part16)+"\n");
            ssb.append(getString(R.string.about_part17)+"\n\n");
            ssb.append(getString(R.string.about_part18)+"\n");

            // Set the text
            TextView textView = (TextView) rootView.findViewById(R.id.about_text);
            textView.setText(ssb);
            return rootView;
        }
    }

}

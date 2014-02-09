package com.michaelcarrano.seven_min_workout.tests;

import com.michaelcarrano.seven_min_workout.AboutActivity;
import com.michaelcarrano.seven_min_workout.R;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import static android.test.ViewAsserts.assertOnScreen;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;

/**
 * Test to verify the AboutActivity is displaying the correct content.
 *
 * Created by michaelcarrano on 1/20/14.
 */
public class MainActivityInstrumentationTest extends ActivityInstrumentationTestCase2<AboutActivity> {

    private AboutActivity mAboutActivity;

    private TextView mTextView;

    @SuppressWarnings("deprecation")
    public MainActivityInstrumentationTest() {
        super("com.michaelcarrano.seven_min_workout", AboutActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mAboutActivity = getActivity();
        mTextView = (TextView) mAboutActivity.findViewById(R.id.about_text);
    }

    public void testTextView() {
        assertOnScreen(mAboutActivity.getWindow().getDecorView(), mTextView);
    }

    public void testLabel() {
        onView(withId(R.id.about_text)).check(matches(withText("Hello world!")));
    }

    public void testFalseLabel() {
        onView(withId(R.id.about_text)).check(matches(withText("What a label!")));
    }
}

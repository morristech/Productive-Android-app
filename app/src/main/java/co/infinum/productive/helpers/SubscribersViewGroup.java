package co.infinum.productive.helpers;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.infinum.productive.R;

/**
 * Created by noxqs on 28.12.15..
 */
public class SubscribersViewGroup extends LinearLayout {

    private Context context;

    private int smallSpacing;

    private int microSpacing;

    public SubscribersViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    public void addSubscriber(String name) {
        TextView tv = new TextView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        smallSpacing = (int) (getResources().getDimension(R.dimen.small_spacing) / getResources().getDisplayMetrics().density);
        microSpacing = (int) (getResources().getDimension(R.dimen.micro_spacing) / getResources().getDisplayMetrics().density);

        params.setMargins(smallSpacing, 0, smallSpacing, 0);
        tv.setLayoutParams(params);

        tv.setText(name);
        tv.measure(0, 0); // call so i can calculate the width of the textview
        tv.setBackgroundColor(ContextCompat.getColor(context, R.color.subscriberBackgroundColor));
        tv.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        tv.setPadding(microSpacing, microSpacing, microSpacing, microSpacing);
        addView(tv);

    }

}

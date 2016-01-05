package co.infinum.productive.helpers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import co.infinum.productive.models.Assignee;

/**
 * Created by noxqs on 04.01.16..
 */
public class SubscribersViewGroupWrapper extends LinearLayout {

    private Context context;

    public SubscribersViewGroupWrapper(Context context) {
        super(context);
        this.context = context;
    }

    public SubscribersViewGroupWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void addElementsToContainer(SubscribersViewGroupWrapper container, ArrayList<Assignee> fetchedSubscribers, float px) {

        SubscribersViewGroup v = new SubscribersViewGroup(context);

        int totalWidth = 0;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        v.setLayoutParams(params);

        container.addView(v);

        for (int i = 0; i < fetchedSubscribers.size(); i++) {

            totalWidth = totalWidth + v.addSubscriber(fetchedSubscribers.get(i).getName());

            if (totalWidth > container.getWidth() - px) {
                totalWidth = 0;
                v.removeViewAt(v.getChildCount() - 1);
                i--;
                v = new SubscribersViewGroup(context);
                container.addView(v);
            }
        }
    }


}

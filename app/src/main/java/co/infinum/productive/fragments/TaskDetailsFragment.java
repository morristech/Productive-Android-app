package co.infinum.productive.fragments;

import com.bumptech.glide.Glide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerTasksComponent;
import co.infinum.productive.dagger.modules.TasksModule;
import co.infinum.productive.helpers.SubscribersViewGroup;
import co.infinum.productive.models.Assignee;
import co.infinum.productive.models.Task;
import co.infinum.productive.mvp.presenters.TasksPresenter;
import co.infinum.productive.mvp.views.TasksView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mjurinic on 28.11.15..
 */
public class TaskDetailsFragment extends BaseFragment implements TasksView {

    public static final String TASK = "task";

    @Bind(R.id.subscriber)
    SubscribersViewGroup subscriber;

    @Bind(R.id.task_list_spinner)
    Spinner taskListSpinner;

    @Inject
    TasksPresenter presenter;

    @Bind(R.id.assignee_avatar_icon)
    CircleImageView assigneeAvatarIcon;

    @Bind(R.id.assignee_name)
    TextView assigneeName;

    @Bind(R.id.due_date_tv)
    TextView dueDateTv;

    @Bind(R.id.due_date)
    TextView dueDate;

    private Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        ButterKnife.bind(this, view);

        DaggerTasksComponent.builder()
                .tasksModule(new TasksModule(this))
                .build()
                .inject(this);

        task = (Task) getArguments().getSerializable(TASK);

        populateScreenWithData();

        presenter.getSubscribersOnTask(task);

        subscriber.measure(0, 0);

        return view;
    }

    private void populateScreenWithData() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.task_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskListSpinner.setAdapter(adapter);

        if (task.getAssignee() != null) {
            Glide.with(getActivity())
                    .load(task.getAssignee().getAvatarUrl())
                    .into(assigneeAvatarIcon);

            assigneeName.setText(task.getAssignee().getName());
        } else {
            assigneeName.setText(R.string.no_assignee_message);
            assigneeAvatarIcon.setVisibility(View.GONE);
        }
        if (task.getDueDate() != null) {
            String time = presenter.modifyTime(task.getDueDate());
            dueDate.setText(time);
        } else {
            dueDate.setText(R.string.no_due_date_message);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onTasksFetched(ArrayList<Task> tasks) {
        //nothing
    }

    @Override
    public void onUnsuccessfulTaskFetch(String message) {
        //nothing
    }

    @Override
    public void onTaskSubscribersFetched(ArrayList<Assignee> fetchedSubscribers) {
        for (int i = 0; i < fetchedSubscribers.size(); i++) {
            subscriber.addSubscriber(fetchedSubscribers.get(i).getName());
        }

    }

    @Override
    public void onTaskSubscriberError(String error) {

    }

}

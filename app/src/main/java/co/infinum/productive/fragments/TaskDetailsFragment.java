package co.infinum.productive.fragments;

import com.bumptech.glide.Glide;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerTasksComponent;
import co.infinum.productive.dagger.modules.TasksModule;
import co.infinum.productive.helpers.SubscribersViewGroupWrapper;
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

    @Bind(R.id.task_list)
    TextView taskListTv;

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

    @Bind(R.id.view_group_container)
    SubscribersViewGroupWrapper viewGroupContainer;

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

        return view;
    }

    private void populateScreenWithData() {

        if (task.getAssignee() != null) {
            Glide.with(getActivity())
                    .load(task.getAssignee().getAvatarUrl())
                    .into(assigneeAvatarIcon);
            taskListTv.setText(task.getTaskList().getName());
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

        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getResources().getDimension(R.dimen.default_spacing),
                getResources().getDisplayMetrics());

        presenter.setupSubscribers(viewGroupContainer, getActivity(), fetchedSubscribers, px);

    }

    @Override
    public void onTaskSubscriberError(String error) {
        showError(error);
    }

}

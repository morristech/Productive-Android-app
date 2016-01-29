package co.infinum.productive.activities;

import android.os.Bundle;

import javax.inject.Inject;

import co.infinum.productive.R;
import co.infinum.productive.dagger.components.DaggerNewTaskComponent;
import co.infinum.productive.dagger.modules.NewTaskModule;
import co.infinum.productive.mvp.presenters.NewTaskPresenter;
import co.infinum.productive.mvp.views.NewTaskView;

public class NewTaskActivity extends BaseActivity implements NewTaskView {

    @Inject
    NewTaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        DaggerNewTaskComponent.builder()
                .newTaskModule(new NewTaskModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String error) {

    }
}

package co.infinum.productive.activities;

import android.os.Bundle;

import co.infinum.productive.R;
import co.infinum.productive.models.ProjectTile;

public class TasksListActivity extends BaseActivity {

    public static final String PROJECT = "project";

    private ProjectTile projectTile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        projectTile = (ProjectTile) getIntent().getSerializableExtra(PROJECT);
    }
}

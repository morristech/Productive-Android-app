package co.infinum.productive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.infinum.productive.R;
import co.infinum.productive.models.Project;

public class TasksListActivity extends AppCompatActivity {

    public static final String PROJECT = "project";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        Intent intent = getIntent();
        Project pro = (Project) intent.getSerializableExtra(PROJECT);


    }
}

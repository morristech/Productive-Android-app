package co.infinum.productive.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.productive.R;
import co.infinum.productive.models.Project;

/**
 * Created by mjurinic on 09.11.15..
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private Context mContext;
    private ArrayList<Project> projects;

    public void add(Project s, int position) {
        position = position == -1 ? getItemCount()  : position;
        projects.add(position, s);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (position < getItemCount()) {
            projects.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_title)
        TextView title;

        @Bind(R.id.item_description)
        TextView description;

        @Bind(R.id.item_thumbnail)
        ImageView thumbnail;

        public SimpleViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public SimpleAdapter(Context context, ArrayList<Project> projects) {
        mContext = context;
        this.projects = projects;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(projects.get(position).getName());

        //TODO calculate elapsed time
        holder.description.setText("Updated 5 hours ago by " + projects.get(position).getProjectManager().getName());

        Glide.with(mContext).load(projects.get(position).getClient().getAvatarUrl())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO show project details
                Toast.makeText(mContext, "Position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}

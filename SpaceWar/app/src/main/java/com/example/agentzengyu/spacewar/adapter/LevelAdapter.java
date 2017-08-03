package com.example.agentzengyu.spacewar.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.agentzengyu.spacewar.R;
import com.example.agentzengyu.spacewar.activity.GameActivity;
import com.example.agentzengyu.spacewar.activity.LevelActivity;
import com.example.agentzengyu.spacewar.entity.basic.set.LevelLibrary;
import com.example.agentzengyu.spacewar.entity.basic.single.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 地图适配器
 */
public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.MapViewHolder> {
    private LevelActivity activity;
    private LayoutInflater inflater;
    private List<Level> levels = new ArrayList<>();

    public LevelAdapter(LevelActivity activity, LevelLibrary library) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        for (Object object : library.getLevels().entrySet()) {
            Map.Entry<String, Level> entry = (Map.Entry<String, Level>) object;
            levels.add(0, entry.getValue());
        }
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_level, parent, false);
        MapViewHolder holder = new MapViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, int position) {
        holder.getLlMap().setBackgroundResource(levels.get(position).getImage());
        holder.getLlMap().setTag(levels.get(position));
        holder.getTvName().setText(levels.get(position).getLevelName());
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    /**
     * 地图布局容器
     */
    public class MapViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlLevel;
        private TextView mTvName;

        public MapViewHolder(View itemView) {
            super(itemView);
            mLlLevel = (LinearLayout) itemView.findViewById(R.id.llLevel);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mLlLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, GameActivity.class);
                    intent.putExtra("Level", (Level) mLlLevel.getTag());
                    activity.startActivity(intent);
                }
            });
        }

        public LinearLayout getLlMap() {
            return mLlLevel;
        }

        public TextView getTvName() {
            return mTvName;
        }
    }
}

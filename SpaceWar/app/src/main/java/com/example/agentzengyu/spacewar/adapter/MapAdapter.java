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
import com.example.agentzengyu.spacewar.activity.MapActivity;
import com.example.agentzengyu.spacewar.database.ResourceMap;
import com.example.agentzengyu.spacewar.entity.set.MapLibrary;
import com.example.agentzengyu.spacewar.entity.single.MapItem;

/**
 * Created by Agent ZengYu on 2017/6/29.
 */

/**
 * 地图适配器
 */
public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapViewHolder> {
    private MapActivity activity;
    private LayoutInflater inflater;
    private MapLibrary data = null;

    public MapAdapter(MapActivity activity, MapLibrary data) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        this.data = data;
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_map, parent, false);
        MapViewHolder holder = new MapViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MapViewHolder holder, int position) {
        holder.getLlMap().setBackgroundResource((int)new ResourceMap().getMapIconMap().get(this.data.getMaps().get(position).getImage()));
        holder.getLlMap().setTag(data.getMaps().get(position));
        holder.getTvName().setText(this.data.getMaps().get(position).getMapName());
    }

    @Override
    public int getItemCount() {
        return this.data.getMaps().size();
    }

    /**
     * 地图布局容器
     */
    public class MapViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlMap;
        private TextView mTvName;

        public MapViewHolder(View itemView) {
            super(itemView);
            mLlMap = (LinearLayout) itemView.findViewById(R.id.llMap);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mLlMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, GameActivity.class);
                    intent.putExtra("MapItem", (MapItem) mLlMap.getTag());
                    activity.startActivity(intent);
                }
            });
        }

        public LinearLayout getLlMap() {
            return mLlMap;
        }

        public TextView getTvName() {
            return mTvName;
        }
    }
}

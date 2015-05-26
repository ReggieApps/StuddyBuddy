package com.example.adriangracia.studybuddy.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.objects.Information;

import java.util.Collections;
import java.util.List;

/**
 * Created by Robby on 5/25/2015.
 */
public class DrawerListAdapter extends RecyclerView.Adapter<DrawerListAdapter.MyViewHolder> {


    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();

    public DrawerListAdapter(Context context, List<Information> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information currentObj = data.get(position);
        holder.title.setText(currentObj.title);
        holder.icon.setImageResource(currentObj.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_text);
            icon = (ImageView) itemView.findViewById(R.id.list_icon);
        }
    }
}

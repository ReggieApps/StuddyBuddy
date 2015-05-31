package com.example.adriangracia.studybuddy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adriangracia.studybuddy.R;
import com.example.adriangracia.studybuddy.activities.MainActivity;
import com.example.adriangracia.studybuddy.objects.Information;
import com.facebook.login.LoginManager;

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
        holder.butt.setText(currentObj.title);
        holder.icon.setImageResource(currentObj.iconId);

        if(position==3){
            holder.butt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LoginManager.getInstance().logOut();
                    Toast.makeText(inflater.getContext(), "Logged out ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(inflater.getContext(), MainActivity.class);
                    inflater.getContext().startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        Button butt;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            butt = (Button) itemView.findViewById(R.id.list_button);
            icon = (ImageView) itemView.findViewById(R.id.list_icon);
        }
    }
}

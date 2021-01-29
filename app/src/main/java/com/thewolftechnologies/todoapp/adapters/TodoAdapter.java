package com.thewolftechnologies.todoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thewolftechnologies.todoapp.MainActivity;
import com.thewolftechnologies.todoapp.R;
import com.thewolftechnologies.todoapp.models.TodoModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {
    private MainActivity mContext;
    private List<TodoModel> mTodoModelList;
    private List<TodoModel> mTodoModelListCopy;

    public TodoAdapter(MainActivity context, List<TodoModel> todoModelList) {
        mContext = context;
        mTodoModelList = todoModelList;
        mTodoModelListCopy = todoModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_todo_item,parent,false);
        return new MyViewHolder(view);
    }

    public void setTodoModelList(List<TodoModel> todoModelList) {
        mTodoModelList = todoModelList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int status = Integer.parseInt(this.mTodoModelList.get(position).getStatus());
        String status_string;
        if (status ==0){
            status_string ="Pending";
        }else {
            status_string ="Complete";
        }

        holder.tv_name.setText(this.mTodoModelList.get(position).getName());
        holder.tv_date.setText(this.mTodoModelList.get(position).getTimestamp());
        holder.tv_status.setText(status_string);
    }

    @Override
    public int getItemCount() {
        if (mTodoModelList != null) {
            return mTodoModelList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name,tv_status,tv_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_todo_name);
            tv_status = itemView.findViewById(R.id.tv_todo_status);
            tv_date = itemView.findViewById(R.id.tv_todo_date);
        }
    }
}

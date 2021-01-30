package com.thewolftechnologies.todoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.thewolftechnologies.todoapp.adapters.TodoAdapter;
import com.thewolftechnologies.todoapp.models.TodoModel;
import com.thewolftechnologies.todoapp.utills.Network;
import com.thewolftechnologies.todoapp.viewModel.TodoViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewTodo;
    private LinearLayoutManager mLinearLayoutManager;
    TodoAdapter mAdapter;
    private List<TodoModel> mTodoModelList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TodoViewModel mTodoViewModel;

    Network mNetwork = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTodo = new Intent(MainActivity.this,CreateTodo.class);
                startActivity(createTodo);

            }
        });

        // handle to_do list data
        mSwipeRefreshLayout = findViewById(R.id.swipeTodo);
        mRecyclerViewTodo = findViewById(R.id.rv_todo);
        mTodoModelList = new ArrayList<>();
        mAdapter = new TodoAdapter(this, mTodoModelList);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerViewTodo.setHasFixedSize(true);
        mRecyclerViewTodo.setLayoutManager(mLinearLayoutManager);

        mRecyclerViewTodo.setAdapter(mAdapter);


        if (mNetwork.isConnected(this)) {
            getTodoList();
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (mNetwork.isConnected(MainActivity.this)) {
                    getTodoList();
                } else {
                    mSwipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getTodoList() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        mTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        mTodoViewModel.getTodoListObserver().observe(this, new Observer<List<TodoModel>>() {
            @Override
            public void onChanged(List<TodoModel> todoModels) {
                if (todoModels != null) {
                    mTodoModelList = todoModels;
                    mAdapter.setTodoModelList(todoModels);
                    mSwipeRefreshLayout.setRefreshing(false);
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Todo list is empty", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
        mTodoViewModel.getTodoList();
    }
}